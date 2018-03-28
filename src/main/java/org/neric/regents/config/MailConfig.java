package org.neric.regents.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration 
public class MailConfig {

    @Value("${email.host}")
    private String host;

    @Value("${email.from}")
    private String from;

    @Value("${email.subject}")
    private String subject;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
         
        mailSender.setUsername("dwriconeapi@gmail.com");
        mailSender.setPassword("wh1tehouse");
         
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
         
        
        
        /*mailSender.setHost("NEX1.CRB.LOCAL");
        mailSender.setPort(25); //25/993/465
        mailSender.setUsername("daniel.whitehouse@neric.org");
        mailSender.setPassword("d@nWh1t3h0us3456");*/
        
        return mailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage() {
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setFrom(from);
       simpleMailMessage.setSubject(subject);
       return simpleMailMessage;
    }
}