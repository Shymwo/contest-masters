package put.poznan.ai.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import put.poznan.ai.common.MailUtil;

public class ForgotPassBean implements Serializable {

	private static final long serialVersionUID = -8328594471058899889L;

	@Autowired
	private transient MailUtil mailUtil;

	public void reset() {
        mailUtil.sendMail("ai.contest.masters@gmail.com", "sz.weihs@gmail.com", "test", "message2");
	}

}
