Anagram ReST Services
=========

This is my take on the anagrams API project that performs a host of functions. It's a simple application
that manages a word library and provides anagrams that match a word.

# The Project
To get started, clone the source to a location on your local system. Then, either run `gradle clean build` to generate the JAR or use the JAR provided in the /build/libs directory. Start the appplication by running the java command `java -jar anagrams-1.0.0.jar` (mind the version if it's been changed). The server is up and running on port 3000.

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
