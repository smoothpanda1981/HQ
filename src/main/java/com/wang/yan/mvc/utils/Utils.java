package com.wang.yan.mvc.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ywang on 08.04.16.
 */
public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class);

    public static String computePath(HttpServletRequest request) {
        String result = "";
        String s = request.getRequestURL().toString();
        logger.info("s : " + s);
        if (s.contains("/HQ")) {
            result = "/HQ";
        } else {
            result = "/";
        }
        logger.info("result : " + result);
        return result;
    }

}
