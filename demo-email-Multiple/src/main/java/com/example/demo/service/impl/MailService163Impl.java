package com.example.demo.service.impl;


import java.io.File;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.service.MailService;

@Service
public class MailService163Impl implements MailService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${email163Account}")
	private String from;
    
    @Autowired
    @Qualifier("getJavaMailSender163")
    private JavaMailSender mailSender;


    /**
     *  简单文本邮件
     * @param to 接收者邮件 (可以多个 也就是群发)
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param filePaths  邮件附件  (可以发送多个附件)
     */
    @Override
	public void sendTextMailText( String subject, String contnet, List<String> filePaths,String... to){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet);
            helper.setFrom(from.trim());
            if (filePaths!=null&&filePaths.size()>0){
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = file.getFilename();
                    helper.addAttachment(fileName, file);
                }
            }


            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("发送静态邮件成功!");
    }


    /**
     * 简单文本邮件
     * @param to (可以多个 也就是群发)
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     */
    @Override
	public void sendTextMailText( String subject, String contnet,String... to){
        this.sendTextMailText( subject,  contnet,null,to);
    }


    /**
     *  HTML 邮件  可以添加附件  和图片
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param filePaths   邮件附件  (可以发送多个附件)
     * @param images HTML图片 (可以多个 但是要合HTML内的id一 一对应)
     * @param to (可以多个 也就是群发)
     */
    @Override
	public void sendHtmlMail(String subject, String contnet,List<String> filePaths,Map<String,String> images,String... to) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet, true);
            helper.setFrom(from.trim());

            ///添加图片

            if (images!=null&&images.size()>0) {

                for (Map.Entry<String, String> stringStringEntry : images.entrySet()) {
                    FileSystemResource res = new FileSystemResource(new File(stringStringEntry.getValue())); //value 就是路径
                    helper.addInline(stringStringEntry.getKey(), res);  //key 就是对应 html 图片里设置的占位符的 位置 id
                }


            }

            // 添加附件
            if (filePaths!=null&&filePaths.size()>0){
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = file.getFilename();
                    helper.addAttachment(fileName, file);
                }
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("发送静态邮件成功!");
    }

    /**
     *  HTML 邮件 可以添加附件
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param filePaths 邮件附件  (可以发送多个附件)
     * @param to  (可以多个 也就是群发)
     */
    @Override
	public void sendHtmlMail(String subject, String contnet,List<String> filePaths,String... to) {
        this.sendHtmlMail(subject,contnet,filePaths,null,to);

    }

    /**
     * HTML 邮件  可以添加 图片
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param images HTML图片 (可以多个 但是要合HTML内的id一 一对应)
     * @param to  (可以多个 也就是群发)
     */
    @Override
	public void sendHtmlMail(String subject, String contnet,Map<String,String> images,String... to) {
        this.sendHtmlMail(subject,contnet,null,images,to);

    }



//    /**
//     * HTML 文本邮件
//     * @param to 接收者邮件
//     * @param subject 邮件主题
//     * @param contnet HTML内容
//     * @throws MessagingException
//     */
//    public void sendHtmlMailTemplate(String to, String subject, String contnet, String rscPath, String rscId) {
//        MimeMessage message = mailSender.createMimeMessage();
//
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(contnet, true);
//            helper.setFrom(from);
//            FileSystemResource res = new FileSystemResource(new File(rscPath));
//            helper.addInline(rscId, res);
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        logger.info("发送静态邮件成功!");
//    }


    /**
     * HTML  Template  邮件  附件+图片
     * @param to 接收者邮件
     * @param subject 邮件主题
     * @param contnet HTML内容
     * @throws MessagingException
     */
    @Override
	public void sendHtmlMailTemplate( String subject, String contnet, List<String> filePaths,Map<String,String> images,String... to) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet, true);
            helper.setFrom(from.trim());
            ///添加图片

            if (images!=null&&images.size()>0) {

                for (Map.Entry<String, String> stringStringEntry : images.entrySet()) {
                    FileSystemResource res = new FileSystemResource(new File(stringStringEntry.getValue())); //value 就是路径
                    helper.addInline(stringStringEntry.getKey(), res);  //key 就是对应 html 图片里设置的占位符的 位置 id
                }

            }
            // 添加附件
            if (filePaths!=null&&filePaths.size()>0){
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = file.getFilename();
                    helper.addAttachment(fileName, file);
                }
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        logger.info("发送静态邮件成功!");
    }


    @Override
	public void sendHtmlMailTemplate( String subject, String contnet,Map<String,String> images,String... to) {
        this.sendHtmlMailTemplate(subject,contnet,null,images,to);
    }

    @Override
	public void sendHtmlMailTemplate( String subject, String contnet, List<String> filePaths,String... to) {
        this.sendHtmlMailTemplate(subject,contnet,filePaths,null,to);
    }

}

