package br.com.mashup.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

import br.com.mashup.twitter.oauth.TwitterOauthKeys;

@Component
public class LoadProperties {

	public TwitterOauthKeys load(String path) {
		
		InputStream is = null;
        Properties prop = null;
        TwitterOauthKeys twitterOauthKeys = new TwitterOauthKeys();
        
        try {
            
        	prop = new Properties();
            is = new FileInputStream(new File(path));
            prop.load(is);
            
            twitterOauthKeys.setConsumerKey(prop.getProperty("oauth.consumerKey"));
            twitterOauthKeys.setConsumerSecret(prop.getProperty("oauth.consumerSecret"));
            twitterOauthKeys.setAccessToken(prop.getProperty("oauth.accessToken"));
            twitterOauthKeys.setAccessTokenSecret(prop.getProperty("oauth.accessTokenSecret"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return twitterOauthKeys;
		
	}
	
}
