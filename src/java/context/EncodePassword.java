/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author Admin
 */
public class EncodePassword {

    public static String toSHA1(String pass) {
        String salt = "ioafh:vhgs;5hfuafdf#@dv&njfyh*mfhd";
        String result = null;
        pass += salt;
        try {
            byte[] dataBytes = pass.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
        }
        return result;
    }
    
}
