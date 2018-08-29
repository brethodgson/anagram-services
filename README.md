Ibotta Dev Project (Anagram ReST Services)
=========

This is my take on the anagrams API project that performs a host of functions. It's a simple application
that manages a word library and provides anagrams that match a word.

# The Project

The API has a few endpoints to consider:

## Word Library Endpoints
@PostMapping("/words.json") - Adds words to the word library for anagram lookup
	Request Body = { "words": ["read", "dear", "dare"] }

@GetMapping("/words") - Returns the entire word library in JSON format

@DeleteMapping("/words.json") - Removes all words stored in the word library

@DeleteMapping("/words/{word}.json") - Removes the specified word from the word library
	{word} = The word to delete

@GetMapping("/words/load") - Loads the word library from the included dictionary.txt

## Anagrams Endpoints
@GetMapping("/anagrams/{word}.json")