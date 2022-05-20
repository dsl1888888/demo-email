package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.service.impl.MailServiceGmailImpl;
import com.example.demo.util.ResourceFileUtil;

@SpringBootTest
public class MailServiceUtilsGmailTest {

	@Autowired
	private MailServiceGmailImpl mailService;

	@Resource
	private TemplateEngine templateEngine;

//    纯文本方式
	@Test
	public void sendTextMailText() {
		mailService.sendTextMailText("测试springbootimail-主题", "测试spring boot imail - 内容", "835215587@qq.com");
	}

	// 文本附件的方式
	@Test
	public void sendTextMailText1() throws FileNotFoundException {
		String filePath = ResourceFileUtil.getAbsolutePath("templates") + File.separator + "test.doc";
		List<String> list = new ArrayList<>();
		list.add(filePath);
		list.add(filePath);
		mailService.sendTextMailText("测试springbootimail-主题", "测试spring boot imail - 内容", list, "835215587@qq.com");
	}

	// HTML发送邮件 可以带附件 + 图片

	@Test
	public void sendHtmlMail() throws FileNotFoundException {

		// 添加图片
		Map<String, String> map = new HashMap();
		map.put("img1", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");
		map.put("img2", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");
		// 附件方式
		List<String> list = new ArrayList<>();
		String filePath = ResourceFileUtil.getAbsolutePath("templates") + File.separator + "test.doc";
		list.add(filePath);
		list.add(filePath);

		String content = "<html>" + "<body>" + "<h3>hello world</h3>" + "<h1>html</h1>" + "<h1>图片-附件-邮件</h1>"
				+ "<img src='cid:img1'></img>" + // 此img1 对应上面 map的img1 否则图片找不到
				"<img src='cid:img2'></img>" + "<body>" + "</html>";

		mailService.sendHtmlMail("这是一封图片邮件", content, list, map, "835215587@qq.com");
	}

	// HTML发送邮件 只带附件

	@Test
	public void sendHtmlMail1() throws FileNotFoundException {

		// 附件方式
		List<String> list = new ArrayList<>();
		String filePath = ResourceFileUtil.getAbsolutePath("templates") + File.separator + "test.doc";
		list.add(filePath);
		list.add(filePath);
		String content = "<html>" + "<body>" + "<h3>hello world</h3>" + "<h1>html</h1>" + "<h1>附件邮件</h1>" + "<body>"
				+ "</html>";
		mailService.sendHtmlMail("这是一封图片邮件", content, list, "835215587@qq.com");
	}

	// HTML发送邮件 只带图片
	@Test
	public void sendHtmlMail2() throws FileNotFoundException {
		// 添加图片
		Map<String, String> map = new HashMap();
		map.put("img1", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");
		map.put("img2", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");
		String content = "<html>" + "<body>" + "<h3>hello world</h3>" + "<h1>html</h1>" + "<h1>图片邮件</h1>"
				+ "<img src='cid:img1'></img>" + // 此img1 对应上面 map的img1 否则图片找不到
				"<img src='cid:img2'></img>" + "<body>" + "</html>";
		mailService.sendHtmlMail("这是一封图片邮件", content, map, "835215587@qq.com");
	}

	// 使用Template模板方式 带附件 带图片
	@Test
	public void testTemplateMailTest1() throws FileNotFoundException {
		// 添加图片
		Map<String, String> map = new HashMap();
		map.put("img1", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");
		map.put("img2", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");
		// 附件方式
		List<String> list = new ArrayList<>();
		String filePath = ResourceFileUtil.getAbsolutePath("templates") + File.separator + "test.doc";
		list.add(filePath);
		list.add(filePath);

		Context context = new Context();
		// 模本内容填充
		context.setVariable("name", "huanmin123xx");
		// 模板名称
		String emailContent = templateEngine.process("emailTeplate", context);
		mailService.sendHtmlMailTemplate("这是一封HTML模板邮件", emailContent, list, map, "835215587@qq.com");

	}

	// 使用Template模板方式 带图片
	@Test
	public void testTemplateMailTest2() throws FileNotFoundException {
		// 添加图片
		Map<String, String> map = new HashMap();
		map.put("img1", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");
		map.put("img2", ResourceFileUtil.getAbsolutePath("templates") + File.separator + "timg.jpg");

		Context context = new Context();
		// 模本内容填充
		context.setVariable("name", "huanmin123xx");
		context.setVariable("idImage1", "img1"); // 此img1 对应上面 map的img1 否则图片找不到
		context.setVariable("idImage2", "img2");
		// 模板名称
		String emailContent = templateEngine.process("emailTeplate", context);
		mailService.sendHtmlMailTemplate("这是一封HTML模板邮件", emailContent, map, "835215587@qq.com");

	}

	// 使用Template模板方式 带附件
	@Test
	public void testTemplateMailTest3() throws FileNotFoundException {

		// 附件方式
		List<String> list = new ArrayList<>();
		String filePath = ResourceFileUtil.getAbsolutePath("templates") + File.separator + "test.doc";
		list.add(filePath);
		list.add(filePath);

		Context context = new Context();
		// 模本内容填充
		context.setVariable("name", "huanmin123xx");
		// 模板名称
		String emailContent = templateEngine.process("emailTeplate", context);
		mailService.sendHtmlMailTemplate("这是一封HTML模板邮件", emailContent, list, "835215587@qq.com");

	}

//uanmin123xx@163.com");

	// 使用Template模板方式 带附件
	@Test
	public void testTemplateMailTest31() throws FileNotFoundException {

		// 附件方式
		List<String> list = new ArrayList<>();
		String filePath = ResourceFileUtil.getAbsolutePath("templates") + File.separator + "test.doc";
		list.add(filePath);
		list.add(filePath);

		Context context = new Context();
		// 模本内容填充
		context.setVariable("name", "huanmin123xx");
		// 模板名称
		String emailContent = templateEngine.process("emailTeplate", context);
		mailService.sendHtmlMailTemplate("这是一封HTML模板邮件", emailContent, list, "835215587@qq.com");

	}

}
