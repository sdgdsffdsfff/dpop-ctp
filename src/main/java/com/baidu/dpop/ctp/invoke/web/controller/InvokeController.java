package com.baidu.dpop.ctp.invoke.web.controller;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.dpop.ctp.common.utils.MD5Util;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;
import com.baidu.dpop.ctp.invoke.service.DataLoadInfoService;
import com.baidu.dpop.ctp.invoke.service.InvokeService;
import com.baidu.dpop.ctp.invoke.service.impl.DownloadTagFileThread;
import com.baidu.dpop.ctp.invoke.service.impl.LoadDataThread;
import com.baidu.dpop.ctp.invoke.vo.DataLoadInfoVo;
import com.baidu.dpop.ctp.invoke.vo.DownloadTagFileVo;
import com.baidu.dpop.ctp.invoke.vo.UpLoadInfo;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.frame.core.base.web.JsonBaseController;
import com.baidu.dpop.frame.core.base.web.JsonResult;

@Controller
@RequestMapping(value = "invoke")
public class InvokeController extends JsonBaseController {

    private static final Logger LOG = Logger.getLogger(InvokeController.class);

    @Value("${dpop.ctp.invoke.taskInsertNumber}")
    private Integer INSERTION;

    @Autowired
    private InvokeService invokeService;

    @Autowired
    private DataLoadInfoService dataLoadInfoService;

    @Autowired
    private GeneralTaskService taskService;

    @Value("${dpop.ctp.invoke.token}")
    private String uploadToken;

    /**
     * 查看上传文件的进度
     * */
    @RequestMapping(value = "/getLoadDataProcess.do", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getLoadDataProcess(@RequestParam("token") String token, 
            @RequestParam("md5value") String md5value) {
        Assert.notNull(md5value);
        DataLoadInfoVo vo = new DataLoadInfoVo();

        try {
            DataLoadInfo ret = dataLoadInfoService.getDataLoadInfoByMd5(md5value);
            vo.setFileName("/nfs/" + ret.getFileName());
            vo.setDataType(ret.getDataType());
            vo.setFileSize(ret.getFileSize());
            vo.setStatus(ret.getStatus());
            vo.setInsertRecord(ret.getInsertRecord());
            vo.setScanRecord(ret.getScanRecord());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            vo.setAddTime(sdf.format(ret.getAddTime()));
        } catch (Exception e) {
            LOG.error("getLoadDataProcess exception: ", e);
            return this.markErrorResult("getLoadDataProcess exception: " + e.getMessage());
        }

        return this.markSuccessResult(vo, "success");
    }

    /**
     * 上传数据文件（起线程做数据导入 loadDataByThread）
     * 
     * @param file 需要上传的文件，必选参数
     * @return JsonResult格式的结果，data项中包含添加成功的条目数与添加失败的adid列表
     */
    @RequestMapping(value = "/loadDataByThread.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult loadDataByThread(@RequestParam("token") String token,
            @RequestParam("upLoadFile") MultipartFile file, @RequestParam("dataType") Byte dataType) {

        // 传入参数有误
        if (token == null || file == null || dataType == null) {
            return this.markErrorResult("upload params is wrong.");
        }

        // http invok对应的token 错误
        if (!token.equals(uploadToken)) {
            return this.markErrorResult("TOKEN is Wrong.");
        }

        // MD5 生成
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date now = new Date();
        StringBuffer md5key = new StringBuffer();
        md5key.append(file.getName());
        md5key.append(sdf.format(now));
        String md5value = MD5Util.getMD5Value(md5key.toString());

        try {

            UpLoadInfo uploadInfo = new UpLoadInfo();
            uploadInfo.setFilePath("loadData/" + file.getName() + sdf.format(now));
            uploadInfo.setUpLoadFile(file);
            uploadInfo.setDataType(dataType);
            uploadInfo.setFileName(file.getName() + sdf.format(now));
            uploadInfo.setFileSize(file.getSize());
            uploadInfo.setMd5value(md5value);

            // 导入文件写入nfs
            File nfsUploadFile = NfsUtils.getWriteFile(uploadInfo.getFilePath());
            file.transferTo(nfsUploadFile);

            // 起线程做数据导入
            LoadDataThread loadDataThread = new LoadDataThread(uploadInfo);
            loadDataThread.start();

        } catch (Exception e) {
            LOG.error("loadDataByThread Exception", e);
            return this.markErrorResult("Error in loadDataByThread: " + e.getMessage());
        }

        return this.markSuccessResult(md5value, "success");
    }

    /**
     * 获取所有已标注数据（开线程方式避免http长连接问题）
     * 
     * @param dataType 需要获取的数据类型，即数据库类型（GroupDataType）
     * @param taskId 需要获取的taskId，如为空，则是所有已标注数据
     * @param startTimeString 需要获取的时间段起点，可以为空
     * @param endTimeString 需要获取的时间段终点，可以为空
     * @return 下载文件的生成url
     */
    @RequestMapping(value = "/getTagedFileByThread.do")
    @ResponseBody
    public JsonResult getTagedFileByThread(Byte dataType, String taskIdListStr, String startTime, String endTime,
            String token) {

        // 传入参数有误
        if (dataType == null || token == null) {
            return this.markErrorResult("getTagedFile params is wrong.");
        }

        // http invok对应的token 错误
        if (!token.equals(uploadToken)) {
            return this.markErrorResult("TOKEN is Wrong.");
        }

        try {

            // 自定义fileName
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            StringBuilder fileName = new StringBuilder();
            fileName.append("tagedTask_dataType_");
            fileName.append(dataType);
            fileName.append("_time_");
            fileName.append(sdf.format(new Date()));

            // file 对应 md5 Value
            String md5Value = MD5Util.getMD5Value(fileName.toString());

            // 查询参数初始化
            DownloadTagFileVo vo = new DownloadTagFileVo();
            vo.setFileName(fileName.toString());
            vo.setMd5Value(md5Value);
            vo.setDataType(dataType);
            vo.setStartTimeString(startTime);
            vo.setEndTimeString(endTime);

            if (!StringUtils.isEmpty(taskIdListStr)) {
                String[] taskIdStrArr = taskIdListStr.split(",");
                List<Integer> taskIdList = new ArrayList<Integer>();
                for (String item : taskIdStrArr) {
                    taskIdList.add(Integer.valueOf(item));
                }
                vo.setTaskIdList(taskIdList);
            }

            // 起线程做数据导入
            DownloadTagFileThread downloadThread = new DownloadTagFileThread(vo);
            downloadThread.start();

            return this.markSuccessResult(md5Value, "success");

        } catch (Exception e) {
            LOG.error("getTagedFileByThread Exception", e);
            return this.markErrorResult("Error in getTagedFileByThread: " + e.getMessage());
        }

    }

    /**
     * 获取所有已标注数据
     * 
     * @param dataType 需要获取的数据类型，即数据库类型（GroupDataType）
     * @param taskId 需要获取的taskId，如为空，则是所有已标注数据
     * @param startTimeString 需要获取的时间段起点，可以为空
     * @param endTimeString 需要获取的时间段终点，可以为空
     * @return 下载文件的生成url
     */
    @RequestMapping(value = "/getTagedFile.do")
    @ResponseBody
    public JsonResult getTagedFile(Byte dataType, String taskIdListStr, String startTime, String endTime, String token) {

        // 传入参数有误
        if (dataType == null || token == null) {
            return this.markErrorResult("getTagedFile params is wrong.");
        }

        // http invok对应的token 错误
        if (!token.equals(uploadToken)) {
            return this.markErrorResult("TOKEN is Wrong.");
        }

        try {
            // 查询参数初始化
            GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
            vo.setDataType(dataType);
            vo.setStartTimeString(startTime);
            vo.setEndTimeString(endTime);

            if (!StringUtils.isEmpty(taskIdListStr)) {
                String[] taskIdStrArr = taskIdListStr.split(",");
                List<Integer> taskIdList = new ArrayList<Integer>();
                for (String item : taskIdStrArr) {
                    taskIdList.add(Integer.valueOf(item));
                }
                vo.setTaskIdList(taskIdList);
            }

            // 返回文件的HTTP请求地址
            String fileName = URLEncoder.encode(invokeService.getTagedFile(vo), "utf-8");
            String filePath = "/nfs/" + fileName;

            return this.markSuccessResult(filePath, "success");
        } catch (Exception e) {
            LOG.error("获取下载文件失败", e);
            return this.markErrorResult("Exception in GetTagedFile: " + e.getMessage());
        }
    }
}
