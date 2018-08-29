package com.anagrams.json;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Words extends AbstractJsonObject{

	@JsonProperty("words")
	private Set<String> wordsList;

	public Words() {}
	
	public Words(Set<String> words) {
		this.wordsList = words;
	}
	
	public Set<String> getWords() {
		return wordsList;
	}

	public void setWords(Set<String> words) {
		this.wordsList = words;
	}
}