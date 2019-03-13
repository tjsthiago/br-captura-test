package br.com.mashup.twitter.oauth;

import java.util.List;

import org.springframework.stereotype.Component;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Component
public class TwitterClient {

	public List<Status> getTweets(Twitter twitter) throws TwitterException{
		List<Status> tweets = twitter.getHomeTimeline();
		return tweets;
	}
	
}
