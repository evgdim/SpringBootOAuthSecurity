package com.github.evgdim.oauthwrapper;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenHolder {
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("expires_in")
	private long expiresIn;
	private String scope;
	private Date validTo;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		long validToMillis = System.currentTimeMillis() + ((expiresIn - 5*60)*1000);//5 min tolerance
		this.validTo = new Date(validToMillis);
	}
	public Date getValidTo() {
		return validTo;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "TokenHolder [accessToken=" + accessToken + ", tokenType=" + tokenType + ", refreshToken=" + refreshToken
				+ ", expiresIn=" + expiresIn + ", scope=" + scope + "]";
	}
	
}
