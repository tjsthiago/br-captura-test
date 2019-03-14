package br.com.mashup.twitter.oauth;

import org.springframework.stereotype.Component;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterOauth {

	public Twitter connect(TwitterOauthKeys twitterOauthKeys) throws Exception {

		try {
			
			Twitter twitter = TwitterFactory.getSingleton();
			AccessToken accessToken = loadAccessToken(twitterOauthKeys);
			
			twitter.setOAuthConsumer(twitterOauthKeys.getConsumerKey(), twitterOauthKeys.getConsumerSecret());
			twitter.setOAuthAccessToken(accessToken);
			
			return twitter;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	private AccessToken loadAccessToken(TwitterOauthKeys twitterOauthKeys) {
		String token = twitterOauthKeys.getAccessToken();
		String tokenSecret = twitterOauthKeys.getAccessTokenSecret();
		return new AccessToken(token, tokenSecret);
	}

}
