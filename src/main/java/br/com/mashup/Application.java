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
import br.com.mashup.properties.LoadProperties;
import br.com.mashup.twitter.oauth.TwitterClient;
import br.com.mashup.twitter.oauth.TwitterOauth;
import br.com.mashup.twitter.oauth.TwitterOauthKeys;
import twitter4j.Status;
import twitter4j.Twitter;

@SpringBootApplication
@ComponentScan("br.com.mashup")
public class Application implements ApplicationRunner {

	@Autowired
	LoadProperties loadProperties;

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
		TwitterOauthKeys twitterOauthKeys = loadProperties.load(args.getOptionValues("properties.path").get(0));

		System.out.println("##### Connecting to Twitter API");
		Twitter twitter = twitterOauth.connect(twitterOauthKeys);
		
		System.out.println("##### Getting tweets");
		List<Status> tweets = twitterClient.getTweets(twitter);

		for (Status status : tweets) {
			System.out.println("\t Usuário: " + status.getUser().getName() + ":" + "Tweet:" + status.getText());
		}
		
		System.out.println("##### Getting GitHub reposotories");
		List<GitHubRepository> repositories = gitHubClient.getGitHubRepositories("");
		
		for (GitHubRepository repository : repositories) {
			System.out.println("\t repository: " + repository.getFull_name());
		}
		
	}

}
