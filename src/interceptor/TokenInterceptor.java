package interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import model.ssk.XcUser;
import net.minidev.json.JSONObject;
import token.Jwt;
import token.TokenState;
import util.JsonUtil;
import util.MsgUtil;

import java.util.Map;

//import model.XcUser;

public class TokenInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		//HttpSession session = inv.getController().getSession();
		//System.out.println(session.getId());
	    /*    XcUser user = inv.getController().getSessionAttr("user");
	        String action=inv.getActionKey();
	      if (user != null||action.indexOf("login")>=0||action.indexOf("sdkLogin")>=0
				  ||action.indexOf("saveUser")>=0||action.indexOf("updateUser")>=0
				  ||action.indexOf("deleteUser")>=0||action.indexOf("approval")>=0) {
	         inv.invoke();
	      }else {*/
			  com.alibaba.fastjson.JSONObject json= JsonUtil.getJSONObject(inv.getController().getRequest());
			  //其他API接口一律校验token
		     if(json.getInteger("validate")==0){
			  System.out.println("开始校验token");
			  //从请求头中获取token
			  String token=json.getString("token");
			  Map<String, Object> resultMap= Jwt.validToken(token);
			  TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
			  switch (state) {
				  case VALID:
				  	inv.getController().setSessionAttr("request",json);
				  	inv.invoke();
				  	//inv.getController().renderJson(json);
				  	break;
				  case EXPIRED:
				  case INVALID:
					  inv.getController().renderJson(MsgUtil.errorMsg("您的token不合法或者过期了，请重新登陆"));
					  break;
			  /*}

	      }*/
			  }
		     }else{
				 inv.getController().renderJson(MsgUtil.successMsg("active"));
			 }
	}
}
