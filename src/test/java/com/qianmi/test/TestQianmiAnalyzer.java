package com.qianmi.test;

import org.junit.Test;

/**
 * @author hourui 2020/5/7 11:06 AM
 */
public class TestQianmiAnalyzer {

    /**
     * 获取emoji的char数组和byte数组
     */
    @Test
    public void testEmoji() {
        String s = "😁😢😂😝😢😶🐶🐱🐴";
        char[] chars = s.toCharArray();
        for (char c : chars) {
            System.out.println(Integer.toHexString(c));
        }
        System.out.println();

        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            System.out.println(Integer.toHexString(b));
        }
    }

    /**
     * 将一个Unicode转化为对应的utf-16编码
     * 编码规则参见：https://en.wikipedia.org/wiki/UTF-16
     *
     * @param unicode Unicode信息
     * @return utf-16编码
     */
    private byte[] unicode2Utf16Bytes(int unicode) {
        if (unicode <= 0xffff) {
            byte[] bytes = new byte[2];
            bytes[0] = (byte) ((unicode & 0xff00) >> 8);
            bytes[1] = (byte) (unicode & 0x00ff);
            return bytes;
        }

        byte[] bytes = new byte[4];
        unicode = unicode - 0x10000;
        int high = ((unicode & 0b11111111110000000000) >> 10) + 0xD800;
        int low = (unicode & 0b1111111111) + 0xDC00;

        bytes[0] = (byte) ((high & 0xff00) >> 8);
        bytes[1] = (byte) (high & 0xff);

        bytes[2] = (byte) ((low & 0xff00) >> 8);
        bytes[3] = (byte) (low & 0xff);

        return bytes;
    }

    /**
     * 获取emoji的utf-16编码范围
     */
    @Test
    public void testUtf16() {
        // 参考：https://segmentfault.com/a/1190000007594620
        int[][] ranges = new int[][]{{0x1F300, 0x1F5FF}, {0x1F600, 0x1F64F}, {0x1F680, 0x1F6FF}};

        for (int[] range : ranges) {
            int start = range[0];
            int end = range[1];

            System.out.print("0x");
            for (byte b : unicode2Utf16Bytes(start)) {
                System.out.printf("%02x", b);
            }
            System.out.print(" - ");
            System.out.print("0x");
            for (byte b : unicode2Utf16Bytes(end)) {
                System.out.printf("%02x", b);
            }
            System.out.println();
        }
        // 执行得到结果
        // 0xd83cdf00 - 0xd83dddff
        // 0xd83dde00 - 0xd83dde4f
        // 0xd83dde80 - 0xd83ddeff
    }

}
