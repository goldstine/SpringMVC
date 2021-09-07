package com.goldstine.servlet.fileupload;

import java.util.Base64;

public class Base64Test {
    public static void main(String[] args) {
        String str="我是中国人";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(str.getBytes());
        System.out.println(new String(encode));
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(new String(decode));
    }
}
