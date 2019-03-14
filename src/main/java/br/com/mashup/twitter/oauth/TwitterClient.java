package br.com.mashup.twitter.oauth;

import java.util.List;

import org.springframework.stereotype.Component;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Component
public class TwitterClient {

	public List<Status> getHomeTmeLineTweets(Twitter twitter) throws TwitterException{
		return twitter.getHomeTimeline();
	}
	
	public List<Status> searchTweets(Twitter twitter, String filter) throws TwitterException{
		
		Query query = new Query(filter);
		QueryResult result;

		try {
		
			result = twitter.search(query);
	        return result.getTweets();
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
}
