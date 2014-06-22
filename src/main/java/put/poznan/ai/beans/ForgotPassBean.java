package put.poznan.ai.beans;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import put.poznan.ai.common.MailUtil;

public class ForgotPassBean implements Serializable {

	private static final long serialVersionUID = -8328594471058899889L;

	public void reset() {
		FacesContext context = FacesContext.getCurrentInstance();
		MailUtil mm = (MailUtil) context.getApplication().evaluateExpressionGet(context, "#{mailUtil}", MailUtil.class);

        mm.sendMail("ai.contest.masters@gmail.com", "sz.weihs@gmail.com", "test", "message");
	}

}
