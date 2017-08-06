import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.alexsandrospecht.util.ConsultaCnpj;
import com.github.alexsandrospecht.wrapper.AtividadeWrapper;
import com.github.alexsandrospecht.wrapper.RetornoWrapper;
import com.google.gson.Gson;

public class Teste {
	   private static final String GOOGLE_CNPJ = "06990590000204";

	    public static void main(String[] args) throws JsonProcessingException, IOException, JSONException {
	        System.out.println(ConsultaCnpj.consultaData(GOOGLE_CNPJ));

	        RetornoWrapper wp = ConsultaCnpj.consultaCnpj(GOOGLE_CNPJ);
	        System.out.println(wp.getCnpj());
	        System.out.println(wp.getNome());
	        System.out.println(wp.getAbertura());
	        System.out.println(wp.getStatus());
	        String arg = "address="+wp.getBairro()+wp.getLogradouro();
	        System.out.println(arg);
	        //JSONObject jo = getGeo(arg);
	     
	        
	        
	        /*AtividadeWrapper aw = wp.getAtividade_principal().iterator().next();
	        System.out.println(aw.getCode());
	        System.out.println(aw.getText());*/
	    }
	    
	    
	    
	    public static JSONObject getGeo(String args) throws JsonProcessingException, IOException, JSONException {
	   	 
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?"+args);//address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
		
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}


				
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			  StringBuilder responseStrBuilder = new StringBuilder();
					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						responseStrBuilder.append(output);
						//System.out.println(output);
			}
		
					 JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
					 
					return jsonObject;
	    }
}
