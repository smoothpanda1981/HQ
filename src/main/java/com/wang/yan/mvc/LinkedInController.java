package com.wang.yan.mvc;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/linkedin")
public class LinkedInController {
	private static final Logger logger = Logger.getLogger(LinkedInController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String mapsPage(ModelMap model) {
		model.addAttribute("message", "Linked In");

//		String linkedinKey = "777a8w7o71jjxb";    //add your LinkedIn key
//		String linkedinSecret = "t6sO6iNeLerf7d9S"; //add your LinkedIn Secret
//
//		LinkedInOAuthService oauthService;
//		LinkedInRequestToken requestToken;
//
//		logger.info("Fetching request token from LinkedIn...");
//		String authUrl = null;
//		String authToken,authTokenSecret;
//
//		oauthService= LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(linkedinKey,linkedinSecret);
//		requestToken= oauthService.getOAuthRequestToken();
//		authToken= requestToken.getToken();
//		authTokenSecret = requestToken.getTokenSecret();
//
//		logger.info("Request token " +requestToken);
//		logger.info("Auth token : " +authToken);
//		logger.info("Auth token secret : " +authTokenSecret);
//
//		authUrl = requestToken.getAuthorizationUrl();
//
//		logger.info("Copy below link in web browser to authorize. Copy the PIN obtained\n" + authUrl);
//		logger.info(authUrl.substring(authUrl.indexOf("=")+1));


//		try
//		{
//
//
//			logger.info("Fetching access token from LinkedIn...");
//
//			LinkedInAccessToken accessToken =  oauthService.getOAuthAccessToken(requestToken, pin);
//			logger.info("Access token : " +  accessToken.getToken());
//			logger.info("Token secret : " +  accessToken.getTokenSecret());
//			final LinkedInApiClientFactory factory =  LinkedInApiClientFactory.newInstance(linkedinKey,linkedinSecret);
//			final LinkedInApiClient client =  factory.createLinkedInApiClient(accessToken);
//
//			//posting status to profile
//			client.updateCurrentStatus("LinkedIN API is cool!");
//
//		} finally {
//			logger.info("Updated status!");
//		}

		return "linkedin";
	}
}