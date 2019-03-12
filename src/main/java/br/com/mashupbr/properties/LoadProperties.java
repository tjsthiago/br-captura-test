package br.com.mashupbr.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class LoadProperties {

	public String load(String path) {
		
		InputStream is = null;
        Properties prop = null;
        String result = "";
        
        try {
            
        	prop = new Properties();
            is = new FileInputStream(new File(path));
            prop.load(is);
            
            result = prop.getProperty("teste");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
		
	}
	
}
