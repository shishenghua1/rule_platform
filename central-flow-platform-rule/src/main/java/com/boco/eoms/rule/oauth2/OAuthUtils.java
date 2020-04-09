package com.boco.eoms.rule.oauth2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OAuthUtils {
	public static OAuth2Details createOAuthDetails(Properties config) {
		OAuth2Details oauthDetails = new OAuth2Details();
		oauthDetails.setGrantType((String) config
				.get(OAuthConstants.GRANT_TYPE));
		oauthDetails.setClientIdName((String) config.get(OAuthConstants.CLIENT_ID_NAME));
		oauthDetails.setClientSecretName((String) config
				.get(OAuthConstants.CLIENT_SECRET_NAME));
		oauthDetails.setClientIdValue((String) config.get(OAuthConstants.CLIENT_ID_VALUE));
		oauthDetails.setClientSecretValue((String) config
				.get(OAuthConstants.CLIENT_SECRET_VALUE));
		oauthDetails.setClientIdValuePwdMod((String) config.get(OAuthConstants.CLIENT_ID_VALUE_PWD_MOD));
		oauthDetails.setClientSecretValuePwdMod((String) config
				.get(OAuthConstants.CLIENT_SECRET_VALUE_PWD_MOD));
		oauthDetails.setRedirectUriName((String) config.get(OAuthConstants.REDIRECT_URI_NAME));
		oauthDetails.setUserAuthorizationUri((String) config
				.get(OAuthConstants.USER_AUTHORIZATION_URI));
		oauthDetails.setAccessTokenUri((String) config
				.get(OAuthConstants.ACCESS_TOKEN_URI));
		oauthDetails.setUserInfoUri((String) config
				.get(OAuthConstants.USER_INFO_URI));
		return oauthDetails;
	}

	public static Properties getClientConfigProps(String path) {
		InputStream is = OAuthUtils.class.getClassLoader().getResourceAsStream(
				path);
		Properties config = new Properties();
		try {
			config.load(is);
		} catch (IOException e) {
			System.out.println("Could not load properties from " + path);
			e.printStackTrace();
			return null;
		}
		return config;
	}
	
	
}
