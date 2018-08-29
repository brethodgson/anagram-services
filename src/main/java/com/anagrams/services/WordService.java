package com.anagrams.services;

import java.util.Set;

public interface WordService {
	
	public void addToWordLibrary(String word);
	
	public void addToWordLibrary(Set<String> words);
	
	public Set<String> getWordLibrary();
	
	public void deleteWordLibrary();
	
	public void deleteWordFromLibrary(String word);
	
	public Set<String> getAnagramsForWord(String word, int limit);
	
	public void loadWordLibraryFromFile();
}