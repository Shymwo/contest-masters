package put.poznan.ai.common;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseHelper {

	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(
			"messages", new Locale("pl"));

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
}
