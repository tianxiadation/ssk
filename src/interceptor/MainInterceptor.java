package interceptor;

import java.util.LinkedHashMap;

import model.ssk.XcBug;
import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import util.MsgUtil;

public class MainInterceptor implements Interceptor {

	private Logger log=Logger.getLogger(MainInterceptor.class);
	
	public void intercept(Invocation inv) {


		try {
			inv.invoke();
		}catch(Exception e) {
			e.printStackTrace();
			XcBug.saveBug(e.getMessage());
			inv.getController().renderJson(MsgUtil.errorMsg(e.getMessage()));
			
		}
		

	}

}
