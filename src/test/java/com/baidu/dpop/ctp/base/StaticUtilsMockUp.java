package com.baidu.dpop.ctp.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.baidu.dpop.ctp.common.constants.FileTypeEnum;
import com.baidu.dpop.ctp.invoke.service.impl.InvokeServiceImpl;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;

import mockit.Mock;
import mockit.MockUp;

public class StaticUtilsMockUp {

    public static MockUp<DpopPropertyUtils> getDpopPropertyUtilsMockUp(){
        return  new MockUp<DpopPropertyUtils>() { 
            @Mock
            public  String getProperty(String key) {
                if("dpop.ctp.invoke.taskInsertNumber".equals(key)){
                    return "1000";
                }
                if("dpop.ctp.nfs.root".equals(key)){								  
                    File file = FileUtils.toFile(InvokeServiceImpl.class.getResource("InvokeServiceImpl.class"));
                    System.out.println(String.format("mock NFS root:%s", file.getParent()));
                    return file.getParent();
                }
                return key;
            }
        };
    }
    
    
    public static MockUp<FileTypeEnum> getFileTypeEnumMockUp(final FileTypeEnum fileTypeEnum){
        return  new MockUp<FileTypeEnum>() { 
            @Mock
            public  FileTypeEnum getFileType(byte[] fileData) {
                return fileTypeEnum;
            }
            
        };
    }

//    public static MockUp<CacheUtils> getCacheUtilsMockUp(final Object value){
//        return  new MockUp<CacheUtils>() { 
//            @Mock
//            public  <E> E get(String key){
//                return (E)value;
//            }
//            
//        };
//    }

//    public static MockUp<NavigationUtils> getNavigationUtilsMockUp(){
//        return  new MockUp<NavigationUtils>() { 
//            @Mock
//            public List<MaterialTagSearchVO> getPublicSearchTagInfos() {
//                List<MaterialTagSearchVO> publicCTagList = new ArrayList<MaterialTagSearchVO>();
//                MaterialTagSearchVO vo = new MaterialTagSearchVO();
//                vo.setCount(10L);
//                vo.setId(1L);
//                vo.setName("tagA");
//                publicCTagList.add(vo);
//                return publicCTagList;
//            }
//            
//            @Mock
//            public  List<MaterialTagSearchVO> getPrivateSearchTagInfos() {
//                List<MaterialTagSearchVO> privateCTagList = new ArrayList<MaterialTagSearchVO>();
//                MaterialTagSearchVO vo2 = new MaterialTagSearchVO();
//                vo2.setCount(20L);
//                vo2.setId(1L);
//                vo2.setName("tagB");
//                privateCTagList.add(vo2);
//                return privateCTagList;
//            }
//        };
//    }
//    
//    
//    /**
//     * 
//     * @return
//     */
//    public static MockUp<NavigationUtils> getNavigationUtilsMockUpNull(){
//        return  new MockUp<NavigationUtils>() { 
//            @Mock
//            public List<MaterialTagSearchVO> getPublicSearchTagInfos() {
//                return null;
//            }
//            
//            @Mock
//            public  List<MaterialTagSearchVO> getPrivateSearchTagInfos() {
//                return null;
//            }
//        };
//    }
//    
//    
//    public static MockUp<UserInfoHelper> getUserInfoHelperMockUp(){
//        return   new MockUp<UserInfoHelper>() { 
//            @Mock
//            public String getCurrentUserUUID() {
//                return "session-user-id";
//            }
//            
//            @Mock
//            public  String getCurrentUserName() {
//                return  "cgd";
//            }
//        };
//    }
//    
//    public static MockUp<UserInfoHelper> getUserInfoHelperMockUpNull(){
//        return   new MockUp<UserInfoHelper>() { 
//            @Mock
//            public String getCurrentUserUUID() {
//                return null;
//            }
//            
//            @Mock
//            public  String getCurrentUserName() {
//                return null;
//            }
//        };
//    }
//    
    public static MockUp<NfsUtils> getNfsUtilsMockUp(final boolean isThrowException){
        return   new MockUp<NfsUtils>() { 
            @Mock
            public String getNfsHttpVisitURL(String fileRelativePath) {
                return "HttpVisitURL";
            }
            
            @Mock
            public  byte[] readFullyBytes(String fileRelativePath) throws IOException {
                if(isThrowException){
                    throw new IOException("");
                }
                return new byte[]{};
            }
            @Mock
            public  byte[] readFullyBytes(File file) throws IOException {
                if(isThrowException){
                    throw new IOException("");
                }
                return new byte[]{};
            }           
        };
    }
}

