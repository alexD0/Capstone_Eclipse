package com.twowire.web.client.service;

import com.google.gwt.core.client.GWT;
import com.twowire.web.client.service.twowire.TwowireService;
import com.twowire.web.client.service.twowire.TwowireServiceAsync;

public class ServiceManager {
	
	private static TwowireServiceAsync twowireService;
	
	public static TwowireServiceAsync getTwowireServiceInstance() {
		  if (twowireService == null) {
			  twowireService = (TwowireServiceAsync) GWT.create(TwowireService.class);
//			  System.out.println(GWT.getModuleBaseURL());
		   }
		   return twowireService;
		}
}
