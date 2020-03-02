package awd.cloud.platform.servers.analyse.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    //获取本周开始和结束时间
    public  static String getBzkssjAndBzjssj(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        /*
        判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        （此处还是按照国内习惯：即每周周一为开始；
        如果不-1则是按照外国习惯：以周日为开始，计算的区间为下一周的，可以验证下）
         */
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        // System.out.println("所在周星期一的日期：" + imptimeBegin);
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        // System.out.println("所在周星期日的日期：" + imptimeEnd);
        return imptimeBegin + "," + imptimeEnd;
    }
}
