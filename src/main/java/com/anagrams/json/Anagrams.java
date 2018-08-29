package com.anagrams.json;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Anagrams extends AbstractJsonObject{
	
	@JsonProperty(value="anagrams")
	private Set<String> anagramsList;
	
	public Anagrams() {}
	
	public Anagrams(Set<String> anagramsList) {
		this.setAnagramsList(anagramsList);
	}

	public Set<String> getAnagramsList() {
		return anagramsList;
	}

	public void setAnagramsList(Set<String> anagramsList) {
		this.anagramsList = anagramsList;
	}
}
