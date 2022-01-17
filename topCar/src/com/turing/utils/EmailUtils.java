package com.turing.utils;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

/**
 * @desc   发送email的工具类
 */
public class EmailUtils {
	 //自己的邮箱
    public static String sendEmailAccount;
    //邮箱授权码
    public static String sendEmailPwd;
	static {
		try {
			Map<String, Object> map=PropertyUtils.getyInfoOfPropert("mail.properties");	
			sendEmailAccount=(String)map.get("emailFrom");
			sendEmailPwd=(String)map.get("emialFromAuthorization");
		} catch (IOException e) {
		System.out.println("mail.properties获取属性配置文件的异常，且异常信息为:"+e.getMessage());
		
		}
	}
    //收件人邮箱
    public  static String receiveMailAccount="980472475@qq.com";
    
    public static void main(String[] args) throws Exception {
		
//     createMimeMessage( sendEmailAccount, receiveMailAccount);
    	
	}
    
    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static void createMimeMessage(HttpServletRequest request,String ucode, String receiveMail) throws Exception {
       //0.获取项目的路径
    	String path=request.getContextPath();
    	String  basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    	
    	
    	 Session session = getSession();
    	
    	// 3. 创建一封邮件
        MimeMessage message=new MimeMessage(session);
 
        // 4. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendEmailAccount,"本人","utf-8"));
 
        // 5. To: 收件人（可以增加多个收件人、抄送、密送）
        //    CC:抄送人，BCC:密送
        message.setRecipient(MimeMessage.RecipientType.TO, new  InternetAddress(receiveMail,"接受的用户","utf-8"));
        //message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("xxxx@qq.com", "XX用户", "UTF-8"));
 
        // 6. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject("邮件的标题", "utf-8");
 
        // 7. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        String context = "<div>激活验证码:<a href='"+basepath+"jsps/manage/user/valadition.jsp'>"+basepath+"jsps/manage/user/valadition.jsp</a>，且验证码为："+ucode+"</div>"+"我是谁我在那?";
    	System.out.println("contex"+context);
        
        message.setContent(context, "text/html;charset=utf-8");
    	 saveAndSendEmail(message, session);
     
    }
    /**
     * @throws MessagingException 
     * @throws UnsupportedEncodingException 
     * 
     */
  public static void   createMimeMessage(String receiver, String emailSubject, String emailText, String imageUrl) throws UnsupportedEncodingException, MessagingException {
	  Session session=EmailUtils.getSession();
	  //创建一份电子邮件
	  MimeMessage message=new MimeMessage(session);
	  message.setFrom(new InternetAddress(sendEmailAccount,"发件人","utf-8"));
	  message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiver,"用户","utf-8"));
	  
	  message.setSubject(emailSubject,"utf-8");
	  MimeBodyPart txtBodyPart=new MimeBodyPart();
	  txtBodyPart.setContent(""+emailText+"这是一张图片\n<img src='cid:c.png'/>","text/html;charset=utf-8");
	  MimeBodyPart img=new MimeBodyPart();
	  DataHandler dHandler=new DataHandler(new FileDataSource(imageUrl));
	  img.setDataHandler(dHandler);
	  img.setContentID("c.png");
	  MimeMultipart multipart=new MimeMultipart();
	  multipart.addBodyPart(txtBodyPart);
	  multipart.addBodyPart(img);
	  multipart.setSubType("related");
	  message.setContent(multipart);
	  saveAndSendEmail(message, session);
	  
	  
	  
  }

	private static void saveAndSendEmail(MimeMessage message, Session session) throws MessagingException {
		   // 8. 设置发件时间
        
		message.setSentDate(new Date());
 
        // 9. 保存设置
        message.saveChanges();
        // 10. 根据 Session 获取邮件传输对象
      Transport transport=  session.getTransport();
       // 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        // 5. 使用
        //
        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        //11.连接SMTP服务器
        transport.connect(sendEmailAccount, sendEmailPwd);
 
        // 12. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message,message.getAllRecipients());
        // 13. 关闭连接
        transport.close();
    	
	}

	private static Session getSession() {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol","smtp");// 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host","smtp.163.com"); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");    // 需要请求认证
 
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session =Session.getInstance(props);
     // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
		return session;
	}

	public static void createMimeMessage2(String receiver, String emailSubject, String emailText, String fileUrl) throws UnsupportedEncodingException, MessagingException {
		  Session session=EmailUtils.getSession();
		  //创建一份电子邮件
		  MimeMessage message=new MimeMessage(session);
		  message.setFrom(new InternetAddress(sendEmailAccount,"发件人","utf-8"));
		  message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiver,"用户","utf-8"));
		  
		  message.setSubject(emailSubject,"utf-8");
		  MimeBodyPart txtBodyPart=new MimeBodyPart();
		  txtBodyPart.setContent(emailText,"text/html;charset=utf-8");
		  MimeBodyPart attachment=new MimeBodyPart();
		  DataHandler dh2=new DataHandler(new FileDataSource(fileUrl));
		 attachment.setDataHandler(dh2);
		 attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
		  
		  
		  MimeMultipart mm=new MimeMultipart();
		  mm.addBodyPart(txtBodyPart);
		  mm.addBodyPart(attachment);
		  mm.setSubType("mixed");
		  message.setContent(mm);
		  saveAndSendEmail(message, session);
	}
    
}
