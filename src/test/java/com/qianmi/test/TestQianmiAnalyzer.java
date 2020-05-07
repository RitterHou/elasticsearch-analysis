package com.qianmi.test;

import org.junit.Test;

/**
 * @author hourui 2020/5/7 11:06 AM
 */
public class TestQianmiAnalyzer {

    @Test
    public void testEmoji() {
        String s = "😁😁😝😊😂😶😢";
        int length = (int) s.codePoints().count();
        System.out.println(length);

        for (int i = 0; i < length; i++) {
            System.out.println(s.codePointAt(i));
        }
    }

}
