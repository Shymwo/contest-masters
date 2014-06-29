package put.poznan.ai.common;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailUtil {

	private MailSender mailSender;

	private static final String PORTAL_MAIL = "ai.contest.masters@gmail.com";

	public static final String HOST = "http://localhost:8080/contest-masters";

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(PORTAL_MAIL);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}
}