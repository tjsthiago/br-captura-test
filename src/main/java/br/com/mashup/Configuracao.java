package br.com.mashup;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.mashup.properties.LoadProperties;
import br.com.mashup.twitter.oauth.TwitterOauth;
import br.com.mashup.twitter.oauth.TwitterOauthKeys;

@SpringBootApplication
@ComponentScan("br.com.mashup")
public class Configuracao implements ApplicationRunner{

	@Autowired
	LoadProperties loadProperties;
	
	@Autowired
	TwitterOauth twitterOauth;
	
	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);
	}
	
	@Override
    public void run(ApplicationArguments args) throws Exception {
        
		System.out.println("Application started with command-line arguments: {}" + Arrays.toString(args.getSourceArgs()));
		TwitterOauthKeys twitterOauthKeys = loadProperties.load(args.getOptionValues("properties.path").get(0));
    	
    	twitterOauth.connect(twitterOauthKeys);
  
	}

}
