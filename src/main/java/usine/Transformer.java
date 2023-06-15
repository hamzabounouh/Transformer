package usine;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Transformer {
	
	private static Logger logger = LoggerFactory.getLogger(Transformer.class);
	
	private HashMap <String,String> tokens= new HashMap <>();  
/**
 * transforms a text by repacing a set of tokens by pre-dfined values.
 * 
 * example :
 * 
 * I m {name} => I am Robert
 * 
 * A token should be a name between brackets : {token}.
 */
	
    public Transformer () {
    	
    
    	/**
    	 * Adds a new token which will used when transforming 
    	 * 
    	 * @param token The token, without the brackets : "name".
    	 * @param value  the value 
    	 */
		
	}
    
    
	public void addToken(String token, String value) 
	{
		
		tokens.put(token,value);
	
	}
	
	
	/*
	 * Transforme a text by replacing the token with their corresponding value.
	 * Each token should be between brackets, for example:
	 * hello {name}, how are tou doing?
	 * 
	 * @param text The take which 
	 * @param value  Transformed text.
	 * 
	 */
	public String transform( String text) {
		
		String transformedText = text;
		
		for(Entry<String,String> entry : tokens.entrySet())
		{
			transformedText = transformedText.replace(entry.getKey(), entry.getValue());
		}
        return transformedText ;
    }
	
	
	public String transform1( String text) 
	{
		
		StringBuilder stringBuilder = new StringBuilder(); 
		
		Pattern pattern = Pattern.compile("\\{(.*?)\\}");
		Matcher matcher = pattern.matcher(text);
		
		while(matcher.find())
		{
			String token = matcher.group(1);
			
			String value = tokens.get(token);
			
			if( value != null)
			
				matcher.appendReplacement(stringBuilder, value);
			else

				matcher.appendReplacement(stringBuilder, "{" + token + "}");			
		}
		return stringBuilder.toString() ;
	}
	
	public static void main(String[] args) {
		
        String message = "This is just a message to {name} who lives at {address} and works at {company}, you can call on {phone}";

        Transformer transformer = new Transformer();
        // Transformer georgeTransformer = new Transformer("name", "address", "phone", "company");

        transformer.addToken("name", "George");
        transformer.addToken("address", "78, rue Will Smith");
        transformer.addToken("phone", "03 54 87 69 88");
        transformer.addToken("company", "Amazon");
        logger.info(transformer.toString());
        
        logger.info(transformer.transform1(message));
       
    }

	  
}
