package config;

import java.util.TimerTask;

import controller.BaseCountController;
import controller.MsgTreeController;
import model.ssk.XcBug;
import org.apache.log4j.Logger;

import service.DayService;

public class DayTimerTask extends TimerTask{
	@Override
	public void run() {
		try {
			//MsgTreeController.calculation();
			BaseCountController.changeXcCount();
		} catch (Exception e) {
           System.out.println("-------------Day解析信息发生异常--------------");
			XcBug.saveBug(e.getMessage());
			e.printStackTrace();
       }  
		
	}


}
