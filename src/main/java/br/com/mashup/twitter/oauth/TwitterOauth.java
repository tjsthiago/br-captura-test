package br.com.mashup.twitter.oauth;

import java.util.List;

import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterOauth {

	public void connect(TwitterOauthKeys twitterOauthKeys) {

		try {
			AccessToken accessToken = loadAccessToken(twitterOauthKeys);
			Twitter twitter = TwitterFactory.getSingleton();
			
			twitter.setOAuthConsumer(twitterOauthKeys.getConsumerKey(), twitterOauthKeys.getConsumerSecret());
			twitter.setOAuthAccessToken(accessToken);

			List<Status> statuses = twitter.getHomeTimeline();
			System.out.println("Home Timeline:");
			
			for (Status status : statuses) {
				System.out.println("Usuário: " + status.getUser().getName() + ":" + "Tweet:" + status.getText());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static AccessToken loadAccessToken(TwitterOauthKeys twitterOauthKeys) {
		String token = twitterOauthKeys.getAccessToken();
		String tokenSecret = twitterOauthKeys.getAccessTokenSecret();
		return new AccessToken(token, tokenSecret);
	}

}
