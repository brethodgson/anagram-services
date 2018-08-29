package com.anagrams.configs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

import com.anagrams.services.WordService;

/**
 * Configuration class for the application
 * 
 * @author bret.hodgson
 */
@SpringBootConfiguration
public class AppConfig {

	Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Autowired
	private WordService wordService;
	
	@Value("${load.word.library.from.file}") 
	private Boolean loadWordLibraryFromFile;
	
	@PostConstruct
	private void loadWordLibrary() {
		if(loadWordLibraryFromFile)
			wordService.loadWordLibraryFromFile();
	}
}