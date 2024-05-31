package com.nanGuoMM.reggie.front.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailUtils {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("zhuyuqinss@qq.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setSentDate(new Date());
        emailSender.send(message);
    }
}
