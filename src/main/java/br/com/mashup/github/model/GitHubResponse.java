package br.com.mashup.github.model;

import java.util.List;

public class GitHubResponse {

	private String total_count;
	private List<GitHubRepository> items;
	
	public GitHubResponse() {
		
	}

	public String getTotal_count() {
		return total_count;
	}

	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}

	public List<GitHubRepository> getItems() {
		return items;
	}

	public void setItems(List<GitHubRepository> items) {
		this.items = items;
	}
	
}
