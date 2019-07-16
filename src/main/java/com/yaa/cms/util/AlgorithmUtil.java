package com.yaa.cms.util;

import com.yaa.cms.system.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AlgorithmUtil {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
    public static final int HASH_ITERATIONS = 2; // 自定义散列次数

    public static void encrypt(SysUser user) {
        // 随机字符串作为salt因子，实际参与运算的salt我们还引入其它干扰因子
//        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), HASH_ITERATIONS).toHex();
        user.setPassword(newPassword);
    }

    public static String encrypt(String password,String salt){
        String newPassword = new SimpleHash(ALGORITHM_NAME, password,
                ByteSource.Util.bytes(salt), HASH_ITERATIONS).toHex();
        return newPassword;

    }

    public static String encryptAESToString(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(source.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes2hex(bytes);
    }

    public static String decryptAESToString(String source, String key) {
        byte[] bytes = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, loadKeyAES(key.getBytes("utf-8")), new IvParameterSpec(key.getBytes("utf-8")));
            bytes = cipher.doFinal(hex2byte(source));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes == null ? "" : new String(bytes);
    }

    private static SecretKey loadKeyAES(byte[] bytes) {
        return new SecretKeySpec(bytes, "AES");
    }

    private static String bytes2hex(byte[] bytes) {
        if (bytes == null) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            byte[] arr$ = bytes;
            int len$ = bytes.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                byte b = arr$[i$];
                String temp = Integer.toHexString(b & 255);
                if (temp.length() == 1) {
                    sb.append("0");
                }

                sb.append(temp);
            }

            return sb.toString();
        }
    }

    private static byte[] hex2byte(String hex) {
        byte[] digest = new byte[hex.length() / 2];

        for(int i = 0; i < digest.length; ++i) {
            String byteString = hex.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte)byteValue;
        }

        return digest;
    }

    /**
     * md5加密
     *
     * @param source 数据源
     * @return 加密字符串
     */
    public static String MD5encode(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
        }
        byte[] encode = messageDigest.digest(source.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte anEncode : encode) {
            String hex = Integer.toHexString(0xff & anEncode);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
