package com.yaa.cms.util;

import org.apache.commons.lang3.StringUtils;

public class HeadUtils {

    /**
     * 返回头像地址
     *  https://avatars0.githubusercontent.com/u/25025324?s=400&u=cf02830d6b8164ce0cd1c57b659cec1ab89cf4d0&v=4
     * @param email
     * @return
     */
    public static String gravatar(String email) {
        String avatarUrl = "https://secure.gravatar.com/avatar/";
//        String avatarUrl = "https://github.com/identicons/";
        if (StringUtils.isBlank(email)) {
            email = "yanghbwork@163.com";
        }
        String hash = AlgorithmUtil.MD5encode(email.trim().toLowerCase());
        return avatarUrl + hash + ".png";
    }

}
