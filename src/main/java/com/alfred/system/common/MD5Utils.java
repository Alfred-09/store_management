package com.alfred.system.common;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * @author Alfred
 * @date 2020/5/20 17:17
 */
public class MD5Utils {

    /**
     * 生成uuid
     * @return
     */
    public static String createUUID(){
        //会生成出- 把-替换成空
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 散列次数,对密码进行加密
     * @param source
     * @param slat
     * @param hashIterations
     * @return
     */
    public static String md5(String source,String slat,Integer hashIterations){
        Md5Hash hash = new Md5Hash(source,slat,hashIterations);
        return hash.toString();
    }
}
