/**
 * 
 */
package com.athene.sybxjr;

import com.alibaba.fastjson.JSON;
import com.athene.sybxjr.domain.yskp.Prefile;

/**
 * @author zhaochf
 *
 */
public class MessageFormatTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Prefile prefile = new Prefile();
		prefile.setMedicalNum("1234");
		prefile.setBOD("121212131212");
		prefile.setIDCard("adfdadfsd");
		
		String message = JSON.toJSONString(prefile);
		System.out.println(message);

	}

}
