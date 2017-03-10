package com.github.evgdim.oauthwrapper;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAuthRestTemplate {
	Logger logger = LoggerFactory.getLogger(OAuthRestTemplate.class);
	
	private static final int NUMBER_OF_RETRIES = 3;

	private TokenHolder tokenHolder;
	
	private TokenRequester tokenRequester;
	
	public OAuthRestTemplate(TokenRequester tokenRequester) {
		if(tokenRequester == null){ throw new IllegalArgumentException("tokenRequester can't be null"); }
		this.tokenRequester = tokenRequester;
	}

	public <T> T makeCall(ServiceCaller caller) throws Exception {
		if(tokenHolder == null || tokenHolder.getValidTo().before(new Date())) { // need new token
			logger.debug("[makeCall] tokenHolder missing or expired."+this.tokenHolder);
			TokenHolder token = tokenRequester.requestToken();
			this.tokenHolder = token;
			logger.debug("[makeCall] requested new token."+this.tokenHolder);
		}
		byte retryCount = 0;
		while(retryCount <= NUMBER_OF_RETRIES) {
			try {
				logger.debug("[makeCall] calling service with:"+this.tokenHolder);
				return caller.call(this.tokenHolder);		
			} catch(Exception e) { //TO DO check for authentication exception
				logger.warn("[makeCall] error during service call.", e);
				retryCount++;
				this.tokenRequester.requestToken();
				logger.debug("[makeCall] retry with new token"+this.tokenHolder);
			}
		}
		logger.error("[makeCall] was unable to make a service call.");
		throw new RuntimeException("Unable to call service with token.");
	}
}
