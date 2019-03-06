package com.yaa.cms.util;

import com.yaa.cms.model.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5Utils {

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

    public static void main(String[] args){
//        System.out.println(randomNumberGenerator.nextBytes().toHex());
        SysUser user = new SysUser();
        user.setName("张三");
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setSalt("238618edccb4395e7a2bcd852ad06b95");
        Md5Utils.encrypt(user);
        System.out.println(user.getPassword());
    }
}
