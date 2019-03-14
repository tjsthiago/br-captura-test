package br.com.mashup.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mashup.github.GitHubClient;
import br.com.mashup.github.model.GitHubRepository;
import br.com.mashup.github.model.Response;
import br.com.mashup.github.model.ResponseItem;
import br.com.mashup.properties.PropertiesUtil;
import br.com.mashup.twitter.oauth.TwitterClient;
import br.com.mashup.twitter.oauth.TwitterOauth;
import br.com.mashup.twitter.oauth.TwitterOauthKeys;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;

@Component
public class InternetOfThingsFacade {

	@Autowired
	PropertiesUtil propertiesUtil;

	@Autowired
	TwitterOauth twitterOauth;

	@Autowired
	TwitterClient twitterClient;

	@Autowired
	GitHubClient gitHubClient;
	
	public Response getGitHubProjectsWithTweets(String propertiesPath) throws Exception {
		
		if(propertiesPath.equals("")) {
			throw new Exception("Properties path cannot be null");
		}

		Response response = new Response();
	
		try {
			
			Twitter twitterConnect = getTwitterConnection(propertiesPath);
			User user = twitterConnect.verifyCredentials(); 
			
			if(user != null) {
				
				System.out.println("##### Getting GitHub repositories");
				List<GitHubRepository> gitHubRepositories = gitHubClient.getGitHubRepositories();
				
				response.setResponseList(buildResponseList(mapTweetsToRepositories(gitHubRepositories, twitterConnect)));

			}
			
		} catch (Exception e) {
			throw new Exception("##### Error: Could not connect to Twitter API. " + e.getMessage());
		}
		
		return response;
		
	}
	
	private Map<GitHubRepository, List<Status>> mapTweetsToRepositories(List<GitHubRepository> repositories, Twitter twitterConnect) throws Exception{
		
		System.out.println("##### Mappint tweets with repositories");
		Map<GitHubRepository, List<Status>> repositoriesWithTweets = new HashMap<GitHubRepository, List<Status>>();
		
		for (GitHubRepository repository : repositories) {
			List<Status> tweets = twitterClient.searchTweets(twitterConnect, repository.getName());
			repositoriesWithTweets.put(repository, tweets);
		}
		
		return repositoriesWithTweets;
		
	}
	
	private Twitter getTwitterConnection(String propertiesPath) throws Exception {
		
		System.out.println("##### Connecting to Twitter API");
		
		try {
			
			TwitterOauthKeys twitterOauthKeys = propertiesUtil.getTwitterOauthKeys(propertiesPath);
			Twitter twitterConnect = twitterOauth.connect(twitterOauthKeys);
			
			return twitterConnect;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	
	}
	
	private List<ResponseItem> buildResponseList(Map<GitHubRepository, List<Status>> repositoriesWithTweets){
		
		List<ResponseItem> responseList = new ArrayList<ResponseItem>();
		
		repositoriesWithTweets.forEach((repository, tweets) -> {

			responseList.add(new ResponseItem(repository.getName(), 
										  repository.getFull_name(), 
										  repository.getHtml_url(), 
										  repository.getDescription(), 
										  tweets));
		
		});
		
		return responseList;
		
	}

	
}
