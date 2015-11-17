package com.test.stringTokenizer;

import java.util.StringTokenizer;

/**
 * Created by shaolei on 2015/11/16 23:46.
 */
public class StringTokenizerTest {

    public static void main(String[] args) {
        String line = "20 21 22";
        StringTokenizer tokenizer = new StringTokenizer(line);
        int left = 0;
        int right = 0;
        int last = 0;
        if (tokenizer.hasMoreTokens()) {
            left = Integer.parseInt(tokenizer.nextToken());
            if (tokenizer.hasMoreTokens()) {
                right = Integer.parseInt(tokenizer.nextToken());
                if(tokenizer.hasMoreTokens()){
                    last = Integer.parseInt(tokenizer.nextToken());
                }
            }
            System.out.println("left = " + left + ", right = " + right + ", last = " + last);
        }
    }
}
