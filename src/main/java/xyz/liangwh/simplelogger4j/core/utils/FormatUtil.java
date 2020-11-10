package xyz.liangwh.simplelogger4j.core.utils;

import org.apache.commons.lang.StringUtils;

public class FormatUtil {


    public static String format(String format,Object...args){
        for (Object o : args){
            if(o instanceof String){
                format= StringUtils.replaceOnce(format, "%s", (String) o);
            }else if(o instanceof Integer){
                format= StringUtils.replaceOnce(format,"%d",o.toString());
            }else if(o instanceof Float){
                format=StringUtils.replaceOnce(format,"%f",o.toString());
            }else if(o instanceof Double){
                format=StringUtils.replaceOnce(format,"%f",o.toString());
            }
        }
        return format;
    }

}
