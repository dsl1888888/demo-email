/**
 * MIT License
 * <p>
 * Copyright (c) 2017 James
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.example.demo.service;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

/**
 * 
 * @author 11061
 *
 */
public class BusinessEmail {

	private MailService strategy;

	public BusinessEmail(MailService strategy) {
		this.strategy = strategy;
	}

	public void changetStrategy(MailService strategy) {
		this.strategy = strategy;
	}

//  public void transport() {
//    this.strategy.go();
//  }

	/**
	 * 简单文本邮件
	 * 
	 * @param to        接收者邮件 (可以多个 也就是群发)
	 * @param subject   邮件主题名称
	 * @param contnet   邮件内容
	 * @param filePaths 邮件附件 (可以发送多个附件)
	 */
	public void sendTextMailText(String subject, String contnet, List<String> filePaths, String... to) {
		this.strategy.sendTextMailText(subject, contnet, filePaths, to);
	}

	/**
	 * 简单文本邮件
	 * 
	 * @param to      (可以多个 也就是群发)
	 * @param subject 邮件主题名称
	 * @param contnet 邮件内容
	 */
	public void sendTextMailText(String subject, String contnet, String... to) {
		this.strategy.sendTextMailText(subject, contnet, to);
	}

	/**
	 * HTML 邮件 可以添加附件 和图片
	 * 
	 * @param subject   邮件主题名称
	 * @param contnet   邮件内容
	 * @param filePaths 邮件附件 (可以发送多个附件)
	 * @param images    HTML图片 (可以多个 但是要合HTML内的id一 一对应)
	 * @param to        (可以多个 也就是群发)
	 */
	public void sendHtmlMail(String subject, String contnet, List<String> filePaths, Map<String, String> images,
			String... to) {
		this.strategy.sendHtmlMail(subject, contnet, filePaths, images, to);
	}

	/**
	 * HTML 邮件 可以添加附件
	 * 
	 * @param subject   邮件主题名称
	 * @param contnet   邮件内容
	 * @param filePaths 邮件附件 (可以发送多个附件)
	 * @param to        (可以多个 也就是群发)
	 */
	public void sendHtmlMail(String subject, String contnet, List<String> filePaths, String... to) {
		this.strategy.sendHtmlMail(subject, contnet, filePaths, to);
	}

	/**
	 * HTML 邮件 可以添加 图片
	 * 
	 * @param subject 邮件主题名称
	 * @param contnet 邮件内容
	 * @param images  HTML图片 (可以多个 但是要合HTML内的id一 一对应)
	 * @param to      (可以多个 也就是群发)
	 */
	public void sendHtmlMail(String subject, String contnet, Map<String, String> images, String... to) {
		this.strategy.sendHtmlMail(subject, contnet, images, to);
	}

//  /**
//   * HTML 文本邮件
//   * @param to 接收者邮件
//   * @param subject 邮件主题
//   * @param contnet HTML内容
//   * @throws MessagingException
//   */
//  public void sendHtmlMailTemplate(String to, String subject, String contnet, String rscPath, String rscId) {
//      MimeMessage message = mailSender.createMimeMessage();
//
//      try {
//          MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//          helper.setTo(to);
//          helper.setSubject(subject);
//          helper.setText(contnet, true);
//          helper.setFrom(from);
//          FileSystemResource res = new FileSystemResource(new File(rscPath));
//          helper.addInline(rscId, res);
//          mailSender.send(message);
//      } catch (MessagingException e) {
//          e.printStackTrace();
//      }
//      logger.info("发送静态邮件成功!");
//  }

	/**
	 * HTML Template 邮件 附件+图片
	 * 
	 * @param to      接收者邮件
	 * @param subject 邮件主题
	 * @param contnet HTML内容
	 * @throws MessagingException
	 */
	public void sendHtmlMailTemplate(String subject, String contnet, List<String> filePaths, Map<String, String> images,
			String... to) {
		this.strategy.sendHtmlMailTemplate(subject, contnet, filePaths, images, to);
	}

	public void sendHtmlMailTemplate(String subject, String contnet, Map<String, String> images, String... to) {
		this.strategy.sendHtmlMailTemplate(subject, contnet, images, to);
	}

	public void sendHtmlMailTemplate(String subject, String contnet, List<String> filePaths, String... to) {
		this.strategy.sendHtmlMailTemplate(subject, contnet, filePaths, to);
	}

}
