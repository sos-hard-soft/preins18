/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author mab.salhi
 */
public class Hash {
    
    public static String hash(String string){
        try {
            //Create MessageDigest and encoding for input String
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //byte[] hash = digest.digest(string.getBytes("UTF-8"));
            
            final String hash = DigestUtils.sha256Hex(string);
            //Hash the Input String
            /*StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                }*/
                return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } /*
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        */
 
        return null;
    }
    
}
