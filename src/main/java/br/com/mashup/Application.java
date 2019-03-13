package br.com.mashup;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.mashup.github.GitHubClient;
import br.com.mashup.github.model.GitHubRepository;
import br.com.mashup.properties.PropertiesUtil;
import br.com.mashup.twitter.oauth.TwitterClient;
import br.com.mashup.twitter.oauth.TwitterOauth;
import br.com.mashup.twitter.oauth.TwitterOauthKeys;
import twitter4j.Status;
import twitter4j.Twitter;

@SpringBootApplication
@ComponentScan("br.com.mashup")
public class Application implements ApplicationRunner {

	@Autowired
	PropertiesUtil propertiesUtil;

	@Autowired
	TwitterOauth twitterOauth;

	@Autowired
	TwitterClient twitterClient;

	@Autowired
	GitHubClient gitHubClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		System.out.println("##### Application started with command-line arguments: {}" + Arrays.toString(args.getSourceArgs()));
		TwitterOauthKeys twitterOauthKeys = propertiesUtil.getTwitterOauthKeys(args.getOptionValues("properties.path").get(0));

		System.out.println("");
		System.out.println("##### Connecting to Twitter API");
		Twitter twitterConnect = twitterOauth.connect(twitterOauthKeys);

		System.out.println("");
		System.out.println("##### Getting GitHub reposotories");
		List<GitHubRepository> repositories = gitHubClient.getGitHubRepositories();
		
		for (GitHubRepository repository : repositories) {

			System.out.println("");
			System.out.println("##### repository: " + repository.getFull_name());
			System.out.println("##### Getting tweets relationship with " + repository.getFull_name() + " repository filtering by " + repository.getName());

			List<Status> tweets = twitterClient.searchTweets(twitterConnect, repository.getName());
			
			if(tweets.size() > 0) {
				printTweets(tweets);
			}else {
				System.out.println("##### Tweets not found");
				System.out.println("");
			}
			
		}

	}

	public static void printTweets(List<Status> tweets) {
		System.out.println("");
		for (Status status : tweets) {
			System.out.println("");
			System.out.println("\t # user account: " + status.getUser().getName());
			System.out.println("\t # Tweet: " + status.getText());
			System.out.println("");
		}
		System.out.println("");
	}

}
