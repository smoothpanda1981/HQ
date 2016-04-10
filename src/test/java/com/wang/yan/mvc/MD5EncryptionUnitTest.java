package com.wang.yan.mvc;

import com.wang.yan.mvc.utils.MD5Encryption;
import org.junit.Test;

/**
 * Created by ywang on 10.04.16.
 */
public class MD5EncryptionUnitTest {
    @Test
    public void testMD5Encryption() {
        System.out.println(MD5Encryption.encryptPasswordByMD5("ouafahwafa79"));
    }
}
