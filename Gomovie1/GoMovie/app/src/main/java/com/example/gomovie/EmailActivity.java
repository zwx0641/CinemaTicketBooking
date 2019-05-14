package com.example.gomovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.os.Handler;
import javax.mail.BodyPart;
//import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class EmailActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

	private RadioGroup radioGroup_gender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		this.radioGroup_gender = (RadioGroup) this.findViewById(R.id.radioGroup_gender);
		this.radioGroup_gender.setOnCheckedChangeListener(this);

	}
	public boolean sendEmailMessagesWithFiles(String fromEmailAddress, String fromEmailPassword, String toEmailAddress, String FrompdfPath, String pdfPath) {
		Ticket ticket = new Ticket();
		ticket.main(FrompdfPath, pdfPath);
		Properties props = new Properties();
		//props.put("mail.smtp.protocol", "smtp");
		props.put("mail.smtp.auth", "true");//设置要验证
		props.put("mail.smtp.host", "smtp.exmail.qq.com");//设置host
		props.put("mail.smtp.port", "465");  //设置端口
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.ssl.enable", true);
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		PassAuthenticator pass = new PassAuthenticator(fromEmailAddress, fromEmailPassword);   //获取帐号密码
		//Session session = Session.getInstance(props, pass); //获取验证会话
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		try {
			//配置发送及接收邮箱
			InternetAddress fromAddress, toAddress;
			// 发件邮箱
			fromAddress = new InternetAddress(fromEmailAddress, fromEmailAddress);
			// 收件邮箱
			toAddress = new InternetAddress(toEmailAddress, toEmailAddress);
			message.setSubject("Your Ticket");
			message.setFrom(fromAddress);
			message.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			//contentPart.setText("Dear user:");
			contentPart.setText("    Here is your ticket!!");
			multipart.addBodyPart(contentPart);
			// 添加附件
			BodyPart messageBodyPart = new MimeBodyPart();
			File file = new File(Environment.getExternalStorageDirectory(), pdfPath);
			DataSource source = new FileDataSource(file);
			// 添加附件的内容
			messageBodyPart.setDataHandler(new DataHandler(source));
			// 添加附件的标题
			// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//	              sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			messageBodyPart.setFileName("ticket.pdf");
//	              messageBodyPart.setFileName("=?GBK?B?"
//	                      + enc.encode(affixName.getBytes()) + "?=");
			multipart.addBodyPart(messageBodyPart);

			// 将multipart对象放到message中
			message.setContent(multipart);

			message.saveChanges();
			// 连接邮箱并发送
			Transport transport = session.getTransport("smtp");
			// 发送邮件的账号和密码
			transport.connect("smtp.qq.com", fromEmailAddress, fromEmailPassword);
			transport.sendMessage(message, message.getAllRecipients());
			//transport.send(message);
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	class PassAuthenticator extends Authenticator {
		String username;
		String password;

		public PassAuthenticator(String username, String password) {
			this.username = username;
			this.password = password;
		}
	}
	/**
	 * 当单选按钮的状态发生变化时自动调用的方法
	 *
	 * @param group     单选按钮所在的按钮组的对象
	 * @param checkedId 用户选中的单选按钮的id值
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		//得到用户选中的 RadioButton 对象
		int requestCode;
		RadioButton radioButton_checked = (RadioButton) group.findViewById(checkedId);
		String gender = radioButton_checked.getText().toString();
		Toast.makeText(this, gender, Toast.LENGTH_LONG).show();
		switch (checkedId) {
			case R.id.radioButton_visa:
				//当用户点击男性按钮时执行的代码
				System.out.println("===女qian===");
				System.out.println("===女hou===");
				break;
			case R.id.radioButton_master:
				//当用户点击女性按钮时执行的代码
				System.out.println("===女性===");
				break;
		}
		System.out.println("===onCheckedChanged(RadioGroup group=" + group + ", int checkedId=" + checkedId + ")==");
	}

}
