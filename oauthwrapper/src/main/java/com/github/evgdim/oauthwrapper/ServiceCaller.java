package com.github.evgdim.oauthwrapper;

public interface ServiceCaller {
	public <T> T call(TokenHolder token) throws Exception;
}
