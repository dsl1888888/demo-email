package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

public interface MailService {

    /**
     *  简单文本邮件
     * @param to 接收者邮件 (可以多个 也就是群发)
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param filePaths  邮件附件  (可以发送多个附件)
     */
    public void sendTextMailText( String subject, String contnet, List<String> filePaths,String... to);


    /**
     * 简单文本邮件
     * @param to (可以多个 也就是群发)
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     */
    public void sendTextMailText( String subject, String contnet,String... to);


    /**
     *  HTML 邮件  可以添加附件  和图片
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param filePaths   邮件附件  (可以发送多个附件)
     * @param images HTML图片 (可以多个 但是要合HTML内的id一 一对应)
     * @param to (可以多个 也就是群发)
     */
    public void sendHtmlMail(String subject, String contnet,List<String> filePaths,Map<String,String> images,String... to);

    /**
     *  HTML 邮件 可以添加附件
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param filePaths 邮件附件  (可以发送多个附件)
     * @param to  (可以多个 也就是群发)
     */
    public void sendHtmlMail(String subject, String contnet,List<String> filePaths,String... to) ;
    /**
     * HTML 邮件  可以添加 图片
     * @param subject 邮件主题名称
     * @param contnet 邮件内容
     * @param images HTML图片 (可以多个 但是要合HTML内的id一 一对应)
     * @param to  (可以多个 也就是群发)
     */
    public void sendHtmlMail(String subject, String contnet,Map<String,String> images,String... to);



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
    public void sendHtmlMailTemplate( String subject, String contnet, List<String> filePaths,Map<String,String> images,String... to);


    public void sendHtmlMailTemplate( String subject, String contnet,Map<String,String> images,String... to);

    public void sendHtmlMailTemplate( String subject, String contnet, List<String> filePaths,String... to);
}
