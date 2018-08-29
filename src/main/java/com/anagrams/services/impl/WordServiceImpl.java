package com.anagrams.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.anagrams.services.WordService;

/**
 * This class handles all word functions such as deleting a word
 * from the library, deleting all words from the library, getting
 * 
 * @author bret.hodgson
 */
@Service
public class WordServiceImpl implements WordService{
	
	Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);
	
	private Map<Map<String, Integer>, Set<String>> wordLibrary = new HashMap<>();

	/**
	 * Adds a single word to the library
	 */
	@Override
	public void addToWordLibrary(String word) {
		Map<String, Integer> wordSignature = getWordSignature(word);
		
		if(wordLibrary.containsKey(wordSignature)) {
			Set<String> wordList = wordLibrary.get(wordSignature);
			wordList.add(word);
			wordLibrary.put(wordSignature, wordList);
		}else {
			Set<String> wordList = new HashSet<>();
			wordList.add(word);
			wordLibrary.put(wordSignature, wordList);
		}
	}

	/**
	 * Adds a collection of words to the library
	 */
	@Override
	public void addToWordLibrary(Set<String> words) {
		words.stream().forEach(word -> addToWordLibrary(word));
	}

	/**
	 * Returns the entire word library
	 */
	@Override
	public Set<String> getWordLibrary() {
		return wordLibrary.values().stream()
			        .flatMap(Set::stream)
			        .collect(Collectors.toSet());
	}

	/**
	 * Deletes all the words from the library
	 */
	@Override
	public void deleteWordLibrary() {
		this.wordLibrary = new HashMap<>();
	}

	/**
	 * Deletes a word from the library
	 */
	@Override
	public void deleteWordFromLibrary(String word) {
		Map<String, Integer> letterCount = getWordSignature(word);
		
		Set<String> words = wordLibrary.get(letterCount);
		if(words != null && words.contains(word)) {
			words.remove(word);
		}
	}

	/**
	 * Returns a list of anagrams that are compatible with
	 * the provided word
	 */
	@Override
	public Set<String> getAnagramsForWord(String word, int limit) {
		
		Set<String> allAnagrams = new HashSet<>();
		
		allAnagrams.addAll(getSingleWordAnagrams(word));
		allAnagrams.addAll(getMultiWordAnagrams(word));
		
		return allAnagrams
				.stream()
				.limit(limit > 0 ? limit : Long.MAX_VALUE)
				.collect(Collectors.toSet());
	}
	
	/**
	 * Returns a set of single words that are a compatible 
	 * anagram to the provided word
	 * @param word
	 * @return
	 */
	private Set<String> getSingleWordAnagrams(String word){
		Map<String, Integer> wordSignature = getWordSignature(word);
		
		if(wordLibrary.containsKey(wordSignature)) {
			return wordLibrary.get(wordSignature).stream()
					.filter(wordFilter -> !word.equalsIgnoreCase(wordFilter))
					.collect(Collectors.toSet());
		}else {
			return Collections.emptySet();
		}
	}
	
	/**
	 * TODO: Implement to return multiple words that are a compatible
	 * anagram to the provided word.
	 * 
	 * @param word
	 * @return
	 */
	private Set<String> getMultiWordAnagrams(String word){
		return Collections.emptySet();
	}

	/**
	 * Loads up the word dictionary with an external file
	 */
	@Override
	public void loadWordLibraryFromFile() {
		Resource resource = new ClassPathResource("words/dictionary.txt");

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(resource.getFile().getPath()))) {
			
			logger.info("Starting to process the dictionary...");
			
			Set<String> words = reader.lines().collect(Collectors.toSet());
			addToWordLibrary(words);
			
			logger.info("Done processing the dictionary with {} words loaded...", words.size());
			
		} catch (IOException e) {
			logger.error("An exception occurred while loading the dictionary.", e);
		}
	}
	
	/**
	 * Returns a signature that is unique to the word itself. This
	 * value represents the count of each letter in the word, which
	 * can then be used to match against other words with the same
	 * letter counting
	 * 
	 * @param word
	 * @return
	 */
	private Map<String, Integer> getWordSignature(String word) {
		
		Map<String, Integer> wordSignature = new HashMap<>();
		
		for(String letter : sortString(word).split("")) {
			
			String lowerCaseLetter = letter.toLowerCase();
			if(wordSignature.containsKey(lowerCaseLetter)) {
				wordSignature.put(lowerCaseLetter, wordSignature.get(lowerCaseLetter) + 1);
			}else {
				wordSignature.put(lowerCaseLetter, 1);
			}
		}
		
		return wordSignature;
	}
	
	/**
	 * Returns a string that is rearranged by natural sort
	 * 
	 * @param inputString
	 * @return
	 */
	private static String sortString(String inputString) {
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
}