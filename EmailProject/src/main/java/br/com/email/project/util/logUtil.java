package br.com.email.project.util;

import org.apache.log4j.Logger;

public class logUtil {
	
	private logUtil() {
		
	}
		
		public static Logger getlogger(Object object) {
			return Logger.getLogger(object.getClass());	
			
	}

		
}
