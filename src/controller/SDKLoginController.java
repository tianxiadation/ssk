package controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bamboocloud.util.JsonMethod;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import interceptor.TokenInterceptor;
import model.Msg;
//import model.XcUser;
import model.ssk.XcUser;
import model.test.Kuser;
import org.apache.log4j.Logger;

import com.bamboocloud.api.ApphubSdk;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import token.Jwt;
import util.Base64Util;
import util.HttpUtil;
import util.JsonUtil;
import util.MsgUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

//iter
public class SDKLoginController extends Controller {
	private String tokenid ;//token
	private String app_id  = "3dataplatform";//注册在综合信息系统中的app的username
	private String app_key = "3dataplatform";//注册在综合信息系统中的app的password
	private String targetsystemId = "20181210110837983-A72F-9013A18F4";//注册在综合信息系统中的app的ID
	private String login = "/error";//错误页面  若没有可为空，按情况而定
	private String seccuss = "/testSDK";//成功跳转的地址，按情况而定

	private ApphubSdk sdk = new ApphubSdk();//new sdk jar

	public void sdkLogin() {
		/* 返回值  Invalid stack frame 则为错误，使用接口方式获取*/
		String accType = getPara("accType");//获取登录方式   值：sdk 为SDK单点登录  值：login 为统一登录页登陆
		String appName = getPara("appName");//获取应用账户名
		String userName = getPara("userName");//获取系统用户名
		String appRole = getPara("appRole");//获取应用角色 base64
		String userRole = getPara("userRole");//获取系统角色 base64
		String accountName = getPara("accountName");//获取应用账户名 若是统一登录页形式 改参数为空
		this.tokenid = getPara("BBCloudBAMSession");        /*获取token*/


		/*if (StrKit.isBlank(this.tokenid)||StrKit.isBlank(appName)) {//校验token是否获取到

			redirect("http://zh.hzxc.gov.cn/");//跳转统一的登录页面，重新获取token
			return;
		}
		Map<String, List<String>> mapper = this.sdk.informal_userInfo_getAttributes(this.tokenid, this.app_id, this.app_key);//获取用户信息
		if (mapper.containsKey("notToken")) {//未获取到用户信息
			if (this.sdk.informal_isAlive()) {//检测 统一用户  是否开启
				redirect("http://zh.hzxc.gov.cn/");//跳转统一的登录页面
				return;
			}

		}*/
		XcUser user=XcUser.getUser(appName);
		if(user==null){
			redirect("http://zh.hzxc.gov.cn/");//跳转统一的登录页面
			return;
		}else{
			//redirect("http://59.202.68.28:8001/#/?appName=" + appName+"&token=?"+getToke("ssk",user.getAppName(),user.getUserName()));
			setSessionAttr("user", user);
			renderJson(getToke("ssk",user.getAppName(),user.getUserName()));
		}
	}
		public static String getToke(String cCode,String userCode,String userName){
			Map<String , Object> payload=new HashMap<String, Object>();
			Date date=new Date();
			payload.put("cCode", cCode);//用户ID
			payload.put("userCode", userCode);//用户ID
			payload.put("userName", userName);//用户ID
			payload.put("iat", date.getTime());//生成时间
			payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
			return Jwt.createToken(payload);
		}

	/**
	 * 注销
	 * @param req
	 * @param res
	 * @throws IOException
	 */

	public void closeUser(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String gotoUrl = this.sdk.informal_logout(this.targetsystemId);//注销token
		req.getSession().setAttribute("tokenid", null);//失效 存放在session token
		//return getLogin(res);//跳转登录界面重新获取token
		res.sendRedirect(gotoUrl);//跳转统一的登录页面
	}

	/**
	 * base64角色信息处理
	 * @param base64
	 * @return
	 */
	private List<Map<String, String>> analysisRole(String base64){
		List<Map<String, String>> rolesMap = new ArrayList<Map<String,String>>();
		if(base64==null || "".equals(base64)) return rolesMap;
		String roleStr = Base64Util.getFromBASE64(base64);
		rolesMap =  JsonMethod.readValue(roleStr, List.class);
		return rolesMap;
	}
	public void saveUser(){
		try {
			JSONObject json=JsonUtil.getJSONObject(getRequest());
			XcUser xcUser=json.toJavaObject(XcUser.class);
			xcUser.setCrateTime(new Date()).save();

			JSONObject json1=new JSONObject();
			json1.put("success",true);
			json1.put("data",xcUser.getId());
			renderJson(json1);

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json1=new JSONObject();
			json1.put("success",false);
			json1.put("errorMessage","系统错误，请联系管理员");
			json1.put("errorName","SYS_ERROR");
			json1.put("errorCode","S01001");
			JSONObject json2=new JSONObject();
			json2.put("name",e.getClass().getName());
			json2.put("message",e.getMessage());
			json2.put("trace",e.getStackTrace());

			json1.put("errorException",json2);
			renderJson(json1);

		}
	}

	public void updateUser(){
		try {
			JSONObject json=JsonUtil.getJSONObject(getRequest());
			XcUser xcUser=json.toJavaObject(XcUser.class);
			JSONObject json1=new JSONObject();
			xcUser.update();
			json1.put("success",true);
			json1.put("data",xcUser.getId());
			renderJson(json1);

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json1=new JSONObject();
			json1.put("success",false);
			json1.put("errorMessage","系统错误，请联系管理员");
			json1.put("errorName","SYS_ERROR");
			json1.put("errorCode","S01002");
			JSONObject json2=new JSONObject();
			json2.put("name",e.getClass().getName());
			json2.put("message",e.getMessage());
			json2.put("trace",e.getStackTrace());
			json1.put("errorException",json2);
			renderJson(json1);

		}
	}
	public void deleteUser(){
		try {
			JSONObject json=JsonUtil.getJSONObject(getRequest());
			XcUser xcUser=json.toJavaObject(XcUser.class);
			JSONObject json1=new JSONObject();
			json1.put("success",true);
			json1.put("data",xcUser.delete());
			renderJson(json1);

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json1=new JSONObject();
			json1.put("success",false);
			json1.put("errorMessage","系统错误，请联系管理员");
			json1.put("errorName","SYS_ERROR");
			json1.put("errorCode","S01003");
			JSONObject json2=new JSONObject();
			json2.put("name",e.getClass().getName());
			json2.put("message",e.getMessage());
			json2.put("trace",e.getStackTrace());

			json1.put("errorException",json2);
			renderJson(json1);

		}
	}
	public void saveUsers(){
		JSONArray json=JsonUtil.getJSONArray(getRequest());
		List<Integer> result =new ArrayList<Integer>();
		for (int i = 0; i < json.size(); i++) {
			XcUser xcUser = json.getJSONObject(i).toJavaObject(XcUser.class);
			xcUser.save();
			result.add(xcUser.getId());
		}
		renderJson(MsgUtil.successMsg(result));
	}

	@Before(TokenInterceptor.class)
	public void login() {
		String appName = getPara("appName");//获取应用账户名
		XcUser user=XcUser.getUser(appName);

		if(user==null){
			renderJson(MsgUtil.errorMsg("用户不存在"));
		}else{
			setSessionAttr("user", user);
			renderJson(MsgUtil.successMsg(user));
		}


	}
	@Before(TokenInterceptor.class)
	public void test() {
		JSONObject json=getSessionAttr("request");
		renderJson(MsgUtil.successMsg(json));
	}
	@Before(TokenInterceptor.class)
	public void test1() {
		JSONObject json=getSessionAttr("request");
		//JSONObject json=JsonUtil.getJSONObject(getRequest());
		renderJson(MsgUtil.successMsg(json));
	}

    /*public void action() {
        List<XcUser> users=XcUser.getList();
        for (XcUser user : users) {
            Kuser k=Kuser.getKuser(user.getUserId());
            if(k!=null){
                user.setDept(k.getDName()).setDeptId(k.getUDeptId()).use("ssk").update();
                System.out.println("1");
            }
        }
        renderJson(MsgUtil.successMsg("补齐部门成功"));
    }
    protected void index() {
		render("index.html");
	}

	@ActionKey("/sdkLogin")
	public void sdkLogin() {
	
		String appName = getPara("appName");//获取应用账户名

		if(StrKit.notBlank(appName)) {
			XcUser user=XcUser.getUser(appName);
			if(user==null){

					JSONObject data=HttpUtil.loginIm(appName);
		             //renderJson(MsgUtil.successMsg(data));
					XcUser xcUser=data.toJavaObject(XcUser.class);
					xcUser.save();

			}
			//redirect("http://tyhy.hzxc.gov.cn:8003?appName="+appName);
			renderJson(MsgUtil.successMsg("登入成功"));
		
		}else{
			redirect("http://zh.hzxc.gov.cn/"); 
		}
		
	}


	//

	*/
	//退出
	public void loginOut() {
		removeSessionAttr("user");
		renderJson(MsgUtil.successMsg("成功退出"));

	}

}
