package com.baidu.dpop.ctp.review.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.mainTask.constant.GeneralMcType;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.vo.AccuracyResultVo;
import com.baidu.dpop.ctp.review.vo.ReviewTaskAccuracyItem;

public class ReviewCountUtils {

    public static AccuracyResultVo countAccuracy(List<ReviewAdTask> adList) {

        AccuracyResultVo result = new AccuracyResultVo();
        Map<String, ReviewTaskAccuracyItem> tagsResult = new HashMap<String, ReviewTaskAccuracyItem>();
        List<ReviewTaskAccuracyItem> generalResult = new ArrayList<ReviewTaskAccuracyItem>();
        int[] counts = new int[AdTagUtils.TAGLIST.size()];
        int[] diff = new int[AdTagUtils.TAGLIST.size()];

        int textRight = 0;
        int textTotal = 0;
        int imgRight = 0;
        int imgTotal = 0;
        int tradeTypeRight = 0;

        Arrays.fill(counts, 0);
        Arrays.fill(diff, 0);

        for (ReviewAdTask reviewAdTask : adList) {
            boolean reviewedFalse = false;

            // TODO 计算行业是否正确，如果行业错误，则算作标注错误
            if (reviewAdTask.getAdTradeIdLevel3().equals(reviewAdTask.getAdTradeIdLevel3Review())) {
                tradeTypeRight++;
            } else {
                reviewedFalse = true;
            }

            // TODO 计算每一个标签是否正确
            String tag = reviewAdTask.getAdTag();
            String tagReview = reviewAdTask.getAdTagReview();
            for (int i = 0; i < counts.length; i++) {
                char tagChar = tag.charAt(i);
                char reviewTagChar = tagReview.charAt(i);

                if (!(tagChar == '-') || !(reviewTagChar == '-')) {
                    counts[i]++;
                }

                if (tagChar != reviewTagChar) {
                    diff[i]++;
                    reviewedFalse = true;
                }

                if (GeneralMcType.isTXT(reviewAdTask.getWuliaoType())) {
                    textRight += reviewedFalse ? 0 : 1;
                    textTotal++;
                } else {
                    imgRight += reviewedFalse ? 0 : 1;
                    imgTotal++;
                }
            }
        }

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                double size = counts[i];
                String name = AdTagUtils.TAGLIST.get(i).getName();
                String text = AdTagUtils.TAGLIST.get(i).getNameCh();
                tagsResult.put(name, new ReviewTaskAccuracyItem(name, text, (size - diff[i]) / size * 100));
            }
        }

        generalResult.add(new ReviewTaskAccuracyItem("total", "整体准确率", (0.0 + textRight + imgRight) / adList.size()));
        generalResult.add(new ReviewTaskAccuracyItem("tradeTotal", "行业准确率", (0.0 + tradeTypeRight) / adList.size()));
        generalResult.add(new ReviewTaskAccuracyItem("textTotal", "文字类准确率", textTotal == 0 ? 0 : (0.0 + textRight)
                / textTotal));
        generalResult.add(new ReviewTaskAccuracyItem("imgTotal", "非文字类准确率", imgTotal == 0 ? 0 : (0.0 + imgRight)
                / imgTotal));
        result.setDefaultValue(Arrays.asList("cao"));
        result.setGeneral(generalResult);
        result.setTags(tagsResult);
        return result;
    }
}
