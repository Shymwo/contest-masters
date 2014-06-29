package put.poznan.ai.common;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.DigestUtils;

public class BaseHelper {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(
			"messages", new Locale("pl"));
	
	public static final String HOST = "http://localhost:8080/contest-masters";
	
	public static final String DEFAULT_ROLE = "ROLE_USER";

	public static String getLocaleString(String code) {
		return BUNDLE.getString(code);
	}

	public static String getRandomString() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public static void showInfoMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				BUNDLE.getString("notify.info"), message));
	}

	public static void showWarningMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
				BUNDLE.getString("notify.warn"), message));
	}

	public static void showErrorMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				BUNDLE.getString("notify.error"), message));
	}

	public static String convertAsMd5(String text){
    	return DigestUtils.md5DigestAsHex(text.getBytes());
	}
	
	public static String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return username;
	}

}
