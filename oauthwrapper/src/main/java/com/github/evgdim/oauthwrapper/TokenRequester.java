package com.github.evgdim.oauthwrapper;

public interface TokenRequester {
	public TokenHolder requestToken() throws Exception;
	public TokenHolder refreshToken() throws Exception;
}
