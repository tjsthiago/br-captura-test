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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((total_count == null) ? 0 : total_count.hashCode());
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
		GitHubResponse other = (GitHubResponse) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (total_count == null) {
			if (other.total_count != null)
				return false;
		} else if (!total_count.equals(other.total_count))
			return false;
		return true;
	}
	
}
