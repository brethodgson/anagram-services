package com.anagrams.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anagrams.json.Anagrams;
import com.anagrams.services.WordService;

@RestController
public class AnagramsController {

	@Autowired
	private WordService wordService;
	
	/**
	 * Returns anagrams that are compatible with the provided word
	 * 
	 * @param word
	 * @param limit
	 * @return
	 */
	@GetMapping("/anagrams/{word}.json")
	public Anagrams getAnagrams(
			@PathVariable String word, 
			@RequestParam(required=false, defaultValue = "0") int limit){
		
		return new Anagrams(wordService.getAnagramsForWord(word, limit)
				.stream()
				.collect(Collectors.toSet()));
	}
}