package com.yaa.cms.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    private static String title = "验证码";

    public static String senderAddress = "yanghbwork@163.com";
    public static String senderPassword = "a13684433576";


    public static boolean send(String email, String content) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderAddress, senderPassword);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderAddress));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSentDate(new java.util.Date());
            message.setSubject(title);
            message.setContent(content, "text/html;charset=gb2312");
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        send("310632730@qq.com", "123456");
    }
}
