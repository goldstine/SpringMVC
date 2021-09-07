package com.goldstine.servlet.cookie;

import javax.servlet.http.Cookie;

public class CookieUtils {
    //通常会提供一个查找指定的cookie的工具类
    public static Cookie findCookie(String name, Cookie[] cookies){

        //对于特殊情况进行处理
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }

        //根据对应的cookie名称，在对应的cookie数组中进行查找
        for (Cookie cookie : cookies) {
            if(name.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }
}
