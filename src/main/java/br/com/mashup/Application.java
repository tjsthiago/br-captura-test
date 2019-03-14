package br.com.mashup;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import br.com.mashup.facade.InternetOfThingsFacade;
import br.com.mashup.github.model.Response;

@SpringBootApplication
@ComponentScan("br.com.mashup")
public class Application implements ApplicationRunner {

	@Autowired
	InternetOfThingsFacade facade;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		System.out.println("##### Application started with command-line arguments: {}" + Arrays.toString(args.getSourceArgs()));
		Response response = facade.getGitHubProjectsWithTweets(args.getOptionValues("properties.path").get(0));
		
		System.out.println("##### Response \n");
		System.out.println(response);
		
	}

}
