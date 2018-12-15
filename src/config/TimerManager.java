package config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerManager {

	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

	public static void dayTimerManager(Boolean flag) {
		Calendar calendar = Calendar.getInstance();

		/*** 定制每日9:00执行方法 ***/

		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		// 如果第一次执行定时任务的时间 小于 当前的时间
		// 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
		if (date.before(new Date()) && !flag) {
			date = TimerManager.addDay(date, 1);
			// System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		}
		System.out.println("第一次执行早上1点的程序的时间是：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		Timer timer = new Timer();

		DayTimerTask task = new DayTimerTask();

		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.scheduleAtFixedRate(task, date, PERIOD_DAY);
	}

	// 增加或减少天数
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	

}
