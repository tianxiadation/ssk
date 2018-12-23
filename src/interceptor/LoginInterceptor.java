package interceptor;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

//import model.XcUser;
import model.ssk.XcUser;
import util.MsgUtil;

public class LoginInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		//HttpSession session = inv.getController().getSession();
		//System.out.println(session.getId());
	        XcUser user = inv.getController().getSessionAttr("user");
	        String action=inv.getActionKey();
	      if (user != null||action.indexOf("login")>=0||action.indexOf("sdkLogin")>=0
				  ||action.indexOf("saveUser")>=0||action.indexOf("updateUser")>=0
				  ||action.indexOf("deleteUser")>=0||action.indexOf("approval")>=0) {
	         inv.invoke();
	      } else {
	         inv.getController().renderJson(MsgUtil.errorMsg("nologin"));
	      }
	
	}
}
