package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BusinessEmail;
import com.example.demo.service.MailService;
import com.example.demo.service.impl.MailService163Impl;
import com.example.demo.service.impl.MailServiceGmailImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SendEmailController {

	@Autowired
	private MailService163Impl a;

	@Autowired
	private MailServiceGmailImpl b;

	@RequestMapping("/send")
	public String send() {
		// 我要发邮件
		MailService mailS = null;
		if (true) {

			mailS = a;
		} else {
			mailS = b;
		}

		log.info(a.toString());
		log.info(b.toString());

		return "success";
	}

	@RequestMapping("/sendV1")
	public String sendV1() {
		// 我要发邮件
		BusinessEmail man = new BusinessEmail(a);
		man.sendTextMailText("测试springbootimail-主题", "测试spring boot imail - 内容", "835215587@qq.com");

		log.info(a.toString());
		log.info(b.toString());

		return "success";
	}
	@RequestMapping("/sendV2")
	public String sendV2() {
		// 我要发邮件
		BusinessEmail man = new BusinessEmail(b);
		man.sendTextMailText("测试springbootimail-主题", "测试spring boot imail - 内容", "dorosdebby@gmail.com");

		log.info(a.toString());
		log.info(b.toString());

		return "success";
	}
}
