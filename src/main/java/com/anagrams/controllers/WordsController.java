package com.anagrams.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anagrams.json.Words;
import com.anagrams.services.WordService;

@RestController

public class WordsController {
	
	@Autowired
	private WordService wordService;
	
	/**
	 * Adds words to the word library
	 * @param response
	 * @param words
	 */
	@PostMapping("/words.json")
	public void postWordsJson(
			HttpServletResponse response, 
			@RequestBody Words words) {
		
		wordService.addToWordLibrary(words.getWords());
		response.setStatus(201); // The test expects a response of 201
	}
	
	/**
	 * Returns the entire word library
	 * @return
	 */
	@GetMapping("/words")
	public Words getWordsJson() {
		return new Words(wordService.getWordLibrary());
	}
	
	/**
	 * Deletes the entire word library
	 * @param response
	 */
	@DeleteMapping("/words.json")
	public void deleteWords(HttpServletResponse response) {
		wordService.deleteWordLibrary();
		response.setStatus(204); // The test expects a response of 204
	}
	
	/**
	 * Deletes a single word from the library
	 * @param response
	 * @param word
	 */
	@DeleteMapping("/words/{word}.json")
	public void deleteWord(
			HttpServletResponse response, 
			@PathVariable String word) {
		
		wordService.deleteWordFromLibrary(word);
		response.setStatus(204); // The test expects a response of 204
	}
	
	/**
	 * Loads the entire word library from file
	 */
	@GetMapping("/words/load")
	public void reloadWords() {
		wordService.loadWordLibraryFromFile();
	}
}