package cn.edu.swpu.info.college_website.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Datetool {
    /**
     * 返回格式化的日期
     * @return
     */
    public static String format(){
        return format(new Date(),"yyyy-mm-dd");
    }
    public static String format(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 格式化显示当前日期
     *
     * @param format
     * @return
     */
//   // public static String format(String format) {
//        return format(new Date(), format);
//    }
}
