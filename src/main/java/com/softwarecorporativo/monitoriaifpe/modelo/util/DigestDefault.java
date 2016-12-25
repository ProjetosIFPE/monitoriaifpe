/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author EdmilsonS
 */
public class DigestDefault {

    private static final Logger LOG = Logger.getLogger(DigestDefault.class.getName());
    private static final BouncyCastleProvider DEFAULT_PROVIDER = new BouncyCastleProvider();
    private static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DEFAULT_ALGORITHM = "SHA-256";

    public static byte[] hash(byte[] data, String algorithm) {
        byte[] hash = null;
        try {
            if (StringUtils.isEmpty(algorithm)) {
                algorithm = DEFAULT_ALGORITHM;
            }
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm, DEFAULT_PROVIDER);
            hash = messageDigest.digest(data);
        } catch (NoSuchAlgorithmException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return hash;
    }

    public static String hashAsHexString(byte[] data, String algorithm) {
        return Hex.encodeHexString(hash(data, algorithm));
    }

    public static byte[] hash(String data, String algorithm) {
        return hash(data.getBytes(Charset.forName(DEFAULT_CHARSET)), algorithm);
    }

    public static String getDigestAsString(byte[] data, String algorithm) {
        return Base64.getEncoder().encodeToString(hash(data, algorithm));
    }

}
