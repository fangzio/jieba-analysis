/**
 *
 */
package com.huaban.analysis.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.qianxinyao.analysis.jieba.keyword.Keyword;
import com.qianxinyao.analysis.jieba.keyword.TFIDFAnalyzer;
import junit.framework.TestCase;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * @author matrix
 */
public class JiebaAnalysisTest extends TestCase {
    @Test
    public void testSpace() {
        TFIDFAnalyzer idfOjb = new TFIDFAnalyzer();
        String[] testStr = new String[]{"测试Model Y是大法师的"};
        for (String sentence : testStr) {
            ArrayList<String> kwList = new ArrayList<String>();
            List<Keyword> keywords = idfOjb.analyze(sentence, 200);
            boolean foundModelY = false;
            for (Keyword kw : keywords) {
                kwList.add(String.format("%s:%s", kw.getName(), kw.getTfidfvalue()));
                if ("model y".equals(kw.getName())) {
                    foundModelY = true;
                    assertEquals(0.0403d, kw.getTfidfvalue());
                }
            }
            assertTrue("should calculate tf-idf for model y", foundModelY);
            System.out.println(String.format("sentence:%s, kws: [%s]", sentence, String.join(",", kwList)));
        }
    }
}
