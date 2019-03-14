package br.com.mashup.github.model;

import java.util.List;

import com.google.gson.Gson;

import twitter4j.Status;

public class ResponseItem {

	private String name;
	private String full_name;
	private String html_url;
	private String description;
	
	private List<Status> tweets;
	
	public ResponseItem() {
		
	}

	public ResponseItem(String name, String full_name, String html_url, String description, List<Status> tweets) {
		this.name = name;
		this.full_name = full_name;
		this.html_url = html_url;
		this.description = description;
		this.tweets = tweets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Status> getTweets() {
		return tweets;
	}

	public void setTweets(List<Status> tweets) {
		this.tweets = tweets;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((full_name == null) ? 0 : full_name.hashCode());
		result = prime * result + ((html_url == null) ? 0 : html_url.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseItem other = (ResponseItem) obj;
		if (full_name == null) {
			if (other.full_name != null)
				return false;
		} else if (!full_name.equals(other.full_name))
			return false;
		if (html_url == null) {
			if (other.html_url != null)
				return false;
		} else if (!html_url.equals(other.html_url))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
