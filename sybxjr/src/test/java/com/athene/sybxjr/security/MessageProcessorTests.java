/**
 * 
 */
package com.athene.sybxjr.security;

/**
 * @author zhaochf
 *
 */
public class MessageProcessorTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MessageProcessor processor = new MessageProcessor("2fMVbe6Nvtyy6CsV");
		
		String messages = "{\"medicalNum\":\"\",\"hospitalizedNum\":\"\", \"credentialType\":\"01\",\"credentialNum\":\"130419199201182110\", \"treatBeginDate\":\"20170201\", \"treatEndDate\":\"20170203\"}";
		String encript = processor.encrypt(messages);
		String decript = processor.decrypt(encript);
		System.out.println(String.format("The encript messages is: %s", encript));
		System.out.println(String.format("The decript messages is: %s", decript));
	}

}
