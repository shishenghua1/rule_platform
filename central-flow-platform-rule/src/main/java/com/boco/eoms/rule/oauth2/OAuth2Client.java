package com.boco.eoms.rule.oauth2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.config.Oauth2ServerProps;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.oauth2.model.PrincipalUser;
import com.boco.eoms.rule.oauth2.model.UserAuthentication;

@Component
public class OAuth2Client {
	
	private static Oauth2ServerProps oauth2ServerProps;
	
	@Autowired
    public OAuth2Client(Oauth2ServerProps oauth2ServerProps) {
		OAuth2Client.oauth2ServerProps=oauth2ServerProps;
	    	
    }

	private static final Logger logger = Logger.getLogger(OAuth2Client.class);
	
	public static String getRedirectUrl(HttpServletRequest request) {
		String szReturn = "";
		Properties config = OAuthUtils
				.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);

		// Generate the OAuthDetails bean from the config properties file
		OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
		String code = StaticMethod.nullObject2String(request
				.getParameter("code"));
		if (code.equals("")
				&& ((HttpServletRequest) request).getSession().getAttribute(
						"sessionform") == null) {

			String urlStr = ((HttpServletRequest) request).getRequestURL()
					.toString();
			String queryStr = StaticMethod
					.nullObject2String(((HttpServletRequest) request)
							.getQueryString());
			if (!queryStr.equals("")) {
				queryStr = "?" + queryStr;
			}
			szReturn = oauthDetails.getUserAuthorizationUri() + "?"
					+ oauthDetails.getClientIdName() + "="
					+ oauthDetails.getClientIdValue() + "&"
					+ oauthDetails.getRedirectUriName() + "=" + urlStr
					+ queryStr + "&response_type=code&state="
					+ testRandomString(8).get(0);
		}
		return szReturn;
	}

//	public static String getAccessToken(HttpServletRequest request,
//			HttpServletResponse response) {
//		String szReturn = "";
//		Properties config = OAuthUtils
//				.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);
//		// Generate the OAuthDetails bean from the config properties file
//		OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
//		String code = StaticMethod.nullObject2String(request
//				.getParameter("code"));
//		String state = StaticMethod.nullObject2String(request
//				.getParameter("state"));
//		if (!code.equals("")
//				&& request.getSession().getAttribute("sessionform") == null) {
//			HttpClient client = new HttpClient();
//			PostMethod post = new PostMethod(oauthDetails.getAccessTokenUri());
//			String urlStr = ((HttpServletRequest) request).getRequestURL()
//					.toString();
//			String queryStr = StaticMethod
//					.nullObject2String(((HttpServletRequest) request)
//							.getQueryString());
//			if (!queryStr.equals("")) {
//				queryStr = "?" + queryStr;
//			}
//			// String encoding =
//			// DatatypeConverter.printBase64Binary("SampleClientId:secret".getBytes("UTF-8"));
//
//			// post.setHeader("Authorization", "Basic " +encoding);
//
//			List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
//			// nameValuePairList.add(new
//			// NameValuePair("response_type","code"));
//			nameValuePairList.add(new NameValuePair("grant_type",
//					"authorization_code"));
//			nameValuePairList.add(new NameValuePair("code", code));
//			// nameValuePairList.add(new NameValuePair("state",state));
//			String redirect_uri = urlStr + queryStr;
//			redirect_uri = redirect_uri.replace("?code=" + code, "");
//			redirect_uri = redirect_uri.replace("&code=" + code, "");
//			redirect_uri = redirect_uri.replace("?state=" + state, "");
//			redirect_uri = redirect_uri.replace("&state=" + state, "");
//			logger.info("redirect_uri=" + redirect_uri);
//			nameValuePairList.add(new NameValuePair(oauthDetails
//					.getRedirectUriName(), redirect_uri));
//
//			NameValuePair[] nameValuePairArray = new NameValuePair[nameValuePairList
//					.size()];
//			nameValuePairList.toArray(nameValuePairArray);
//
//			post.setRequestBody(nameValuePairArray);
//			post.getParams().setParameter(
//					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//			String coding = oauthDetails.getClientIdValue() + ":"
//					+ oauthDetails.getClientSecretValue();
//			String encoding = "";
//			try {
//				encoding = new BASE64Encoder().encode(coding.getBytes("UTF-8"));
//
//				List<Header> headers = new ArrayList<Header>();
//				headers.add(new Header("Authorization", OAuthConstants.BASIC
//						+ " " + encoding));
//				headers.add(new Header("Accept", OAuthConstants.JSON_CONTENT
//						+ ", " + OAuthConstants.URL_ENCODED_CONTENT));
//				headers.add(new Header("Cache-Control", "no-cache"));
//				headers.add(new Header("Pragma", "no-cache"));
//
//				client.getHostConfiguration().getParams().setParameter(
//						"http.default-headers", headers);
//				int status = client.executeMethod(post);
//				logger.info(status);
//				String result = post.getResponseBodyAsString() != null ? post
//						.getResponseBodyAsString().trim() : "";
//				if (status >= 400) {
//					throw new RuntimeException(
//							"Could not access protected resource. Server returned http code: "
//									+ status);
//				}
//				Gson gson = new Gson();
//				Map<String, String> retMap = gson.fromJson(result,
//						new TypeToken<Map<String, String>>() {
//						}.getType());
//				szReturn = StaticMethod.nullObject2String(retMap
//						.get("access_token"));
//				logger.info("access_token=" + szReturn);
//				Cookie cookie = new Cookie("Authenticate", szReturn);
//				response.addCookie(cookie);
//				return szReturn;
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw new RuntimeException(e.getMessage());
//			} finally {
//				try {
//					post.releaseConnection();
//				} catch (Exception e) {
//				}
//			}
//		}
//		return szReturn;
//	}

//	public static String getAccessTokenPwdMod(HttpServletRequest request,
//			HttpServletResponse response, String username, String password) {
//		String szReturn = "";
//		Properties config = OAuthUtils
//				.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);
//
//		// Generate the OAuthDetails bean from the config properties file
//		OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
//		if (request.getSession().getAttribute("sessionform") != null) {
//			request.getSession().invalidate();
//		}
//		if (request.getSession().getAttribute("sessionform") == null) {
//			HttpClient client = new HttpClient();
//			PostMethod post = new PostMethod(oauthDetails.getAccessTokenUri());
//
//			List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
//			nameValuePairList.add(new NameValuePair("grant_type",
//					OAuthConstants.GRANT_TYPE_PASSWORD));
//			nameValuePairList.add(new NameValuePair("username", username));
//			nameValuePairList.add(new NameValuePair("password", password));
//			// nameValuePairList.add(new NameValuePair("state",state));
//
//			NameValuePair[] nameValuePairArray = new NameValuePair[nameValuePairList
//					.size()];
//			nameValuePairList.toArray(nameValuePairArray);
//
//			post.setRequestBody(nameValuePairArray);
//			post.getParams().setParameter(
//					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//			String coding = oauthDetails.getClientIdValuePwdMod() + ":"
//					+ oauthDetails.getClientSecretValuePwdMod();
//			String encoding = "";
//			try {
//				encoding = new BASE64Encoder().encode(coding.getBytes("UTF-8"));
//
//				List<Header> headers = new ArrayList<Header>();
//				headers.add(new Header("Authorization", OAuthConstants.BASIC
//						+ " " + encoding));
//				headers.add(new Header("Accept", OAuthConstants.JSON_CONTENT
//						+ ", " + OAuthConstants.URL_ENCODED_CONTENT));
//				headers.add(new Header("Cache-Control", "no-cache"));
//				headers.add(new Header("Pragma", "no-cache"));
//
//				client.getHostConfiguration().getParams().setParameter(
//						"http.default-headers", headers);
//				int status = client.executeMethod(post);
//				if (status >= 400) {
//					throw new RuntimeException(
//							"Could not access protected resource. Server returned http code: "
//									+ status);
//				}
//				String result = post.getResponseBodyAsString() != null ? post
//						.getResponseBodyAsString().trim() : "";
//				logger.info(result);
//				Gson gson = new Gson();
//				Map<String, String> retMap = gson.fromJson(result,
//						new TypeToken<Map<String, String>>() {
//						}.getType());
//				szReturn = StaticMethod.nullObject2String(retMap
//						.get("access_token"));
//				logger.info("access_token=" + szReturn);
//				Cookie cookie = new Cookie("Authenticate", szReturn);
//				response.addCookie(cookie);
//				return szReturn;
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//				throw new RuntimeException(e.getMessage());
//			} finally {
//				try {
//					post.releaseConnection();
//				} catch (Exception e) {
//				}
//			}
//		}
//		return szReturn;
//	}

	public static String getUserId(String token) {
		String szReturn = "";
//		Properties config = OAuthUtils
//				.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);
//
//		// Generate the OAuthDetails bean from the config properties file
//		OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
		if (!token.equals("")) {
			HttpClient client = new HttpClient();
			String url = "http://"+oauth2ServerProps.getIp()+":"+oauth2ServerProps.getPort()+"/v1/user/me";
			GetMethod get = new GetMethod(url);
			List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
			// nameValuePairList.add(new NameValuePair("state",state));

			NameValuePair[] nameValuePairArray = new NameValuePair[nameValuePairList
					.size()];
			nameValuePairList.toArray(nameValuePairArray);

			get.setQueryString(nameValuePairArray);
			get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
					"UTF-8");
			try {
				List<Header> headers = new ArrayList<Header>();
				headers.add(new Header("Authorization", OAuthConstants.BEARER
						+ " " + token));
				headers.add(new Header("Accept", OAuthConstants.JSON_CONTENT));
				client.getHostConfiguration().getParams().setParameter(
						"http.default-headers", headers);
				int status = client.executeMethod(get);
				if (status >= 400) {
					throw new RuntimeException(
							"Could not access protected resource. Server returned http code: "
									+ status);
				}
				
				logger.debug(status);
				String result = get.getResponseBodyAsString() != null ? get
						.getResponseBodyAsString().trim() : "";
				logger.debug(result);
				
				PrincipalUser user = JSONObject.toJavaObject(JSONObject.parseObject(result), PrincipalUser.class);
				UserAuthentication userAuthentication = JSONObject.toJavaObject(JSONObject.parseObject(result), UserAuthentication.class);
				szReturn = user.getName();
				SecurityContextHolder.getContext().setAuthentication(userAuthentication);
				return szReturn;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} catch (Throwable t) {
			}
		}
		return szReturn;
	}

	public static List<String> testRandomString(int order) {
		// 创建List<String>
		List<String> ls = new ArrayList<String>();
		String ku = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		/*
		 * 定义一个StringBuilder用以保存生成的字符串，这里不选用String和StringBuffer（String长度
		 * 不可变，StringBuffer没有StringBuilder快）
		 */
		StringBuilder newStr = new StringBuilder();
		// 创建一个Random用以生成伪随机数，也可采用Math.random()来实现
		Random r = new Random();
		for (int j = 0; j < order; j++) {
			do {
				// 将newStr置空
				newStr.delete(0, newStr.length());
				// 得到字符串长度的随机数
				int r1 = r.nextInt(10) + 1;
				for (int i = 0; i < r1; i++) {
					// 得到随机字符
					int r2 = r.nextInt(ku.length());
					newStr.append(ku.charAt(r2));
				}
			} while (ls.contains(newStr.toString()));
			ls.add(newStr.toString());
		}
		return ls;
	}
}
