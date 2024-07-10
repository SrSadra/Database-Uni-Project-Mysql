package helper;

import java.util.Date;
// import java.sql.Date;
import java.util.GregorianCalendar;

public class Helper {
    public static  Date birthDateCalculator(String birth){
        String[] tmp = birth.split("/");
        return (Date) new GregorianCalendar(Integer.valueOf(tmp[0]),Integer.valueOf(tmp[1]) - 1,Integer.valueOf(tmp[2])).getTime();
    }
}
