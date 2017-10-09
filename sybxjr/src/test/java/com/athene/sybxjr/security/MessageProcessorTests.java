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

		MessageProcessor processor = new MessageProcessor("djH4YdDuKrTpqvmA");
		
		String messages = "{\"medicalNum\":\"1338571\",\"hospitalizedNum\":\"\", \"credentialType\":\"01\",\"credentialNum\":\"\", \"treatBeginDate\":\"20170301\", \"treatEndDate\":\"20170331\"}";
		String encript = processor.encrypt(messages);
		
		encript = "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";
		String decript = processor.decrypt(encript);
		System.out.println(String.format("The encript messages is: %s", encript));
		System.out.println(String.format("The decript messages is: %s", decript));
		
		
	}

}
