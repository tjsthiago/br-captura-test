package br.com.mashup.github;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.mashup.github.model.GitHubRepository;
import br.com.mashup.github.model.GitHubResponse;

@Component
public class GitHubClient {

	public List<GitHubRepository> getGitHubRepositories(){
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.github.com/search/repositories?q=Internet+of+things";
		
		GitHubResponse response = restTemplate.getForObject(url, GitHubResponse.class);
		List<GitHubRepository> repositories = response.getItems().subList(0, 10);
		
		return repositories;
	
	}
	
}
