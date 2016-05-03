package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/11.
 */
public class DataUtils {
    private static String date;
    private static Calendar calendar;

    public static String getDate(){
        if (calendar == null){
            calendar = Calendar.getInstance();
        }
        date = calendar.get(Calendar.YEAR) + calendar.get(Calendar.MONTH) + calendar.get(Calendar.DATE) + calendar.get(Calendar.HOUR_OF_DAY)+ calendar.get(Calendar.MINUTE)+ calendar.get(Calendar.SECOND) +"";
        return date;
    }

    public static String getDateNow(){
        String temp_str="";
        Date dt = new Date();
        //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        temp_str=sdf.format(dt);
        return temp_str;
    }
}
