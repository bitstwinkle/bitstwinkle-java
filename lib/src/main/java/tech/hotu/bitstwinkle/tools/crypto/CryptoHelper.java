/*
 *
 *  *  * Copyright (C) 2023 The Developer bitstwinkle
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *
 */

package tech.hotu.bitstwinkle.tools.crypto;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.hotu.bitstwinkle.tools.sys.SysHelper;

public final class CryptoHelper {
  private static final Logger logger = LoggerFactory.getLogger(CryptoHelper.class);
  private static final String AES_ALGORITHM = "AES";
  private static final int AES_BLOCK_SIZE = 16;
  public static String SHA256(String data, String token) throws Exception {
    byte[] key = token.getBytes(StandardCharsets.UTF_8);
    SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
    Mac mac = null;
    try {
      mac = Mac.getInstance("HmacSHA256");
    } catch (NoSuchAlgorithmException e) {
      throw new Exception("no such algorithm: HmacSHA256");
    }
    try {
      mac.init(secretKeySpec);
    } catch (InvalidKeyException e) {
      throw new Exception("invalid token");
    }
    byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
    return bytesToHex(hmacBytes);
  }

  public static String AESEncrypt(String plainText, String secretKey) {
    if(SysHelper.RunMode().isRd()){
      logger.info("DO AESEncrypt encryptedText= >>{}<< | secretKey= >>{}<<", plainText, secretKey);
    }
    if(secretKey==null || plainText==null){
      return "";
    }
    if( secretKey.isBlank() || plainText.isBlank()){
      return "";
    }
    if (secretKey.startsWith("0x")) {
      secretKey = secretKey.substring(2);
    }
    byte[] plaintext = plainText.getBytes(StandardCharsets.UTF_8);
    SecretKeySpec secretKeySpec = new SecretKeySpec(hexStringToBytes(secretKey), AES_ALGORITHM);
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

      byte[] iv = new byte[AES_BLOCK_SIZE];
      SecureRandom secureRandom = new SecureRandom();
      secureRandom.nextBytes(iv);
      IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
      cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
      byte[] paddedPlaintext = padPlaintext(plaintext);
      byte[] ciphertext = cipher.doFinal(paddedPlaintext);
      byte[] result = new byte[AES_BLOCK_SIZE + ciphertext.length];
      System.arraycopy(iv, 0, result, 0, AES_BLOCK_SIZE);
      System.arraycopy(ciphertext, 0, result, AES_BLOCK_SIZE, ciphertext.length);
      return bytesToHexString(result);
    }catch (Exception e) {
      logger.error("AESEncrypt err", e);
      return "";
    }
  }

  public static String AESDecrypt(String encryptedText, String secretKey) {
    if(SysHelper.RunMode().isRd()){
      logger.info("DO AESDecrypt encryptedText= >>{}<< | secretKey= >>{}<<", encryptedText, secretKey);
    }
    if(encryptedText==null || secretKey==null ){
      return "";
    }
    if(secretKey.isBlank() || encryptedText.isBlank()){
      return "";
    }
    if (secretKey.startsWith("0x")) {
      secretKey = secretKey.substring(2);
    }
    byte[] ciphertext = hexStringToBytes(encryptedText);
    SecretKeySpec secretKeySpec = new SecretKeySpec(hexStringToBytes(secretKey), AES_ALGORITHM);
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      byte[] iv = Arrays.copyOfRange(ciphertext, 0, AES_BLOCK_SIZE);
      IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
      cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
      byte[] decrypted = cipher.doFinal(ciphertext, AES_BLOCK_SIZE, ciphertext.length - AES_BLOCK_SIZE);
      return new String(decrypted, StandardCharsets.UTF_8);
    }catch (Exception e) {
      logger.error("AESDecrypt err", e);
      return "";
    }
  }

  private static String bytesToHex(byte[] bytes) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : bytes) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  private static String bytesToHexString(byte[] data) {
    StringBuilder hexString = new StringBuilder();
    for (byte b : data) {
      hexString.append(String.format("%02X", b));
    }
    return hexString.toString();
  }

  private static byte[] hexStringToBytes(String hexString) {
    int length = hexString.length();
    byte[] data = new byte[length / 2];
    for (int i = 0; i < length; i += 2) {
      data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
          + Character.digit(hexString.charAt(i + 1), 16));
    }
    return data;
  }

  private static byte[] padPlaintext(byte[] plaintext) {
    int padding = AES_BLOCK_SIZE - (plaintext.length % AES_BLOCK_SIZE);
    byte[] paddedPlaintext = Arrays.copyOf(plaintext, plaintext.length + padding);
    Arrays.fill(paddedPlaintext, plaintext.length, paddedPlaintext.length, (byte) padding);
    return paddedPlaintext;
  }
}
