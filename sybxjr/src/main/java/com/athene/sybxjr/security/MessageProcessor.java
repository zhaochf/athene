/**
 * 
 */
package com.athene.sybxjr.security;

import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.base.Charsets;

/**
 * The request messages processor
 * 
 * @author zhaochf
 *
 */
public final class MessageProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessor.class);

	private Cipher encryptCipher;
	
	private Cipher decryptCipher;

	public MessageProcessor(String secretKey) {
		Assert.hasText(secretKey, "The secret key can not be empty.");
		Assert.isTrue(secretKey.length() == 16, "The secret key length must be 16.");
		
		LOGGER.info(String.format("The secret key is: %s", secretKey));
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(Charsets.UTF_8), "AES");
			
			this.encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			this.encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			
			this.decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			this.decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public String encrypt(String messages) {
		try {
			byte[] bytes = this.encryptCipher.doFinal(messages.getBytes(Charsets.UTF_8));
			return  Base64.getEncoder().encodeToString(bytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			LOGGER.error(e.getMessage());
		}
		return "";
	}
	
	public String decrypt(String messages) {
		try {
			byte[] bytes = Base64.getDecoder().decode(messages);
			return  new String(this.decryptCipher.doFinal(bytes), Charsets.UTF_8);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			LOGGER.error(e.getMessage());
		}
		return "";
	}
}
