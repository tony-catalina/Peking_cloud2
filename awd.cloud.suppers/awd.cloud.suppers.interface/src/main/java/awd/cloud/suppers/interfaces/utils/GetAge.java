package awd.cloud.suppers.interfaces.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetAge {

    //根据日期换算年龄
    public static int getAge(String strDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD"); //定义日期格式
        Date parse = sdf.parse(strDate);

        //java提供的日期类，因为是抽象类并且构造方法是protected，所以api提供了getInstance()来创建对象
        Calendar cal = Calendar.getInstance();

        if (cal.before(parse)) { // before()日期是否在比较的日期之前，是true，否false
            throw new IllegalArgumentException("现在是你的生日!");
        }
        //当前的年月日
        int yearNow = cal.get(Calendar.YEAR);  //年
        int monthNow = cal.get(Calendar.MONTH);  //月
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //日

        cal.setTime(parse);
        //传入的年月日
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年-传入的年份
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }
}
