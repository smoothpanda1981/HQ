package com.wang.yan.mvc.utils;

import com.wang.yan.mvc.MoneyController;
import org.apache.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BitstampUtils {
    private SecretKeySpec keyspec;
    private Mac mac;
    private String key;
    private String clientid;
    private long nonce;

    private static final Logger logger = Logger.getLogger(BitstampUtils.class);

    public StringBuffer getPostData(String httpLink, String requestName) throws ParseException, IOException {
        Map<String,String> args = new HashMap<String,String>() ;

//		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        //Date d = f.parse(f.format(new Date()));
        //long milliseconds = d.getTime();
        this.nonce = System.currentTimeMillis();

        args.put("nonce", Long.toString(this.nonce)) ;
        args.put("key", this.key) ;

        if (requestName.equals("user_transaction")) {
            args.put("limit", Integer.toString(1000));
        }
        // create url form encoded post data
        String postData = "" ;
        for (Iterator<String> iter = args.keySet().iterator(); iter.hasNext();) {
            String arg = iter.next() ;
            if (postData.length() > 0) postData += "&" ;
            postData += arg + "=" + URLEncoder.encode(args.get(arg)) ;
        }


        //URL url = new URL("https://www.bitstamp.net/api/v2/balance/");
        URL url = new URL(httpLink);
        URLConnection conn = url.openConnection() ;
        conn.setUseCaches(false) ;
        conn.setDoOutput(true) ;

        mac.update(Long.toString(this.nonce).getBytes()) ;
        mac.update(this.clientid.getBytes()) ;
        mac.update(this.key.getBytes()) ;

        postData += "&signature="+String.format("%064x", new BigInteger(1, mac.doFinal())).toUpperCase() ;
        logger.info("postData : " + postData);
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded") ;
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36") ;

        // write post data
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
        out.write(postData) ;
        out.close() ;

        // read response
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        StringBuffer response = new StringBuffer() ;
        while ((line = in.readLine()) != null)
            response.append(line) ;
        in.close() ;


        return response;
    }



    public void setAuthKeys(String key,String secret,String clientid) {
        try {
            this.keyspec = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256") ;
            this.mac = Mac.getInstance("HmacSHA256") ;
            this.key = key;
            this.clientid = clientid;
            mac.init(keyspec) ;
            logger.info(mac.getMacLength());
            logger.info(mac.getAlgorithm());
            logger.info(mac.getProvider());
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
