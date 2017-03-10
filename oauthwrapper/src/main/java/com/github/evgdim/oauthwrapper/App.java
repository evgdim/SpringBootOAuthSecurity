package com.github.evgdim.oauthwrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Hello world!
 *
 */
public class App {
	public static OkHttpClient http = new OkHttpClient();

	public static void main(String[] args) throws Exception {

		OAuthRestTemplate restTmpl = new OAuthRestTemplate(() -> {
			RequestBody requestBody = new FormEncodingBuilder().add("username", "user").add("password", "pass")
					.add("scope", "read").add("client_secret", "123456").add("client_id", "clientapp")
					.add("grant_type", "password").build();

			Request request = new Request.Builder().url("http://localhost:8080/oauth/token")
					.addHeader("content-type", "multipart/form-data; boundary=---011000010111000001101001")
					.addHeader("accept", "application/json")
					.addHeader("authorization", "Basic Y2xpZW50YXBwOjEyMzQ1Ng==")
					.post(requestBody).build();
			Response response = http.newCall(request).execute();
			String jsonToken = response.body().string();
			System.out.println("Response: " + jsonToken);

			ObjectMapper mapper = new ObjectMapper();
			TokenHolder tokenHolder = mapper.readValue(jsonToken, TokenHolder.class);
			return tokenHolder;
		});
		
		String userData = restTmpl.makeCall(new ServiceCaller() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <String> String call(TokenHolder token) throws Exception {
				Request request = new Request.Builder()
						.url("http://localhost:8080/user")
						.addHeader("Authorization", "Bearer " + token.getAccessToken())
						.get()
						.build();
				Response response = http.newCall(request).execute();
				
				return (String) response.body().string();
			}
		});
		System.out.println(userData);
	}
}
