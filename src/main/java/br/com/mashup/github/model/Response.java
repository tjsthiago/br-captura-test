package br.com.mashup.github.model;

import java.util.List;

import com.google.gson.Gson;

public class Response {

	List<ResponseItem> responseList;
	
	public Response() {
		
	}

	public List<ResponseItem> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<ResponseItem> responseList) {
		this.responseList = responseList;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
