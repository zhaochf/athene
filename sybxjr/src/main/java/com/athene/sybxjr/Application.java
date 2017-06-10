/**
 * 
 */
package com.athene.sybxjr;

import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author zhaochf
 *
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SpringApplicationBuilder()
			.sources(ApplicationConfiguration.class)
			.bannerMode(Banner.Mode.OFF)
			.run(args);
	}

}
