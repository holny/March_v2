package com.hly.march2.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

public class MD5 {
    public static void main(String[] args) {
        MD5("1234",getRandomSalt());
    }
    public static String getRandomSalt(){
        // UUID组成是8+4+4+4+12，"-"分割
        UUID id=UUID.randomUUID();
        String uid=id.toString().replace("-","");
        System.out.println("uid="+uid);
        return uid;
    }

    /**
     * credentials是明文密码，uid是getRandomSalt()生成的Salt
     * hashIteration=5，加密次数。这里必须与shiro配置文件里配置的一样
     * @param credentials
     * @param uid
     * @return
     */
    public static Object MD5(String credentials,String uid){
        String hashAlgorithmName = "MD5";
        int hashIteration = 5;  //
        ByteSource credentialSalt = ByteSource.Util.bytes(uid);
        Object result = new SimpleHash(hashAlgorithmName,credentials,credentialSalt,hashIteration);
        System.out.println("MD5 result="+result);
        return result;
    }
}
