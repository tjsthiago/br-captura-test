package br.com.mashup;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mashupbr.properties.LoadProperties;

@SpringBootApplication
public class Configuracao implements ApplicationRunner{

	@Autowired
	LoadProperties loadProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(Configuracao.class, args);
	}
	
	@Override
    public void run(ApplicationArguments args) throws Exception {
        
		System.out.println("Application started with command-line arguments: {}" + Arrays.toString(args.getSourceArgs()));
    	String result = loadProperties.load(args.getOptionValues("properties.path").get(0));
    	System.out.println("loadProperties: " + result);
  
	}

}
