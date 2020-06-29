package org.simpleframework.util;

import javax.print.attribute.standard.JobKOctets;
import java.util.Collection;
import java.util.Map;

/**
 * @Desc:
 * @authonr: LeeSongsheng
 * @create: 2020/06/27
 **/
public class ValidationUtil {

    /**
    * @Description: 对 String 判空
    * @Param: [obj]
    * @return: boolean
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public static boolean isEmpty(String obj) {
        return (obj == null || "".equals(obj));
    }

    /**
    * @Description: 对数组判空
    * @Param: [obj]
    * @return: boolean
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public static boolean isEmpty(Object[] obj) {
        return obj == null || obj.length == 0;
    }

    /**
    * @Description: 对 Map 判空
    * @Param: [obj]
    * @return: boolean
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public static boolean isEmpty(Map<?,?> obj) {
        return obj == null || obj.isEmpty();
    }

    /**
    * @Description: 对集合判空
    * @Param: [obj]
    * @return: boolean
    * @Author: LeeSongs
    * @Date: 2020/6/27
    */
    public static boolean isEmpty(Collection<?> obj) {
        return obj == null || obj.isEmpty();
    }
}
