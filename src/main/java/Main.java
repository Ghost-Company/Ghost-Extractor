import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.codehaus.jackson.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.alexsandrospecht.util.ConsultaCnpj;
import com.github.alexsandrospecht.wrapper.AtividadeWrapper;
import com.github.alexsandrospecht.wrapper.RetornoWrapper;

public class Main {
	   private static final String GOOGLE_CNPJ = "06990590000204";

	    public static void main(String[] args) throws Exception {

	        RetornoWrapper wp = ConsultaCnpj.consultaCnpj(GOOGLE_CNPJ);
	        Empresa empresa = new Empresa(wp.getNome(), wp.getCnpj(), String.valueOf(wp.getAbertura()), wp.getStatus(), wp.getBairro(), wp.getLogradouro());
	        String arg = "address="+ URLEncoder.encode(wp.getBairro()+wp.getLogradouro(), "UTF-8");
	        
	        JSONObject geo = getGeo(arg);	        
	        
	        try{
		        geo = (((geo.getJSONArray("results")).getJSONObject(0)).getJSONObject("geometry")).getJSONObject("location");
		
		        empresa.setLatitude(String.valueOf(geo.getDouble("lat")));
		        empresa.setLongitude(String.valueOf(geo.getDouble("lng")));
		        
		        System.out.print("================ DADOS ================"
		        + "\nNOME: " + wp.getNome()
		        + "\nCNPJ: " + wp.getCnpj()
		        + "\nABERTURA: " + wp.getAbertura()
		        + "\nSTATUS: " + wp.getStatus()
		        + "\nBAIRRO: " + wp.getBairro()
		        + "\nLOGRADOURO: " + wp.getLogradouro()
		        + "\nLONGITUDE: " + empresa.getLongitude()
		        + "\nLATITUDE: " + empresa.getLatitude()
		        + "\n=======================================\n"
		        );
		        
		    }
	        catch(Exception e){
	        	System.out.println("Err extrac g");
	        	System.out.println("Err: " + e);
	        }
	        
	        
	      	     
	        AtividadeWrapper aw = wp.getAtividade_principal().iterator().next();
	        System.out.println(aw.getCode());
	        System.out.println(aw.getText());
	    }
	    
	    
	    
	    public static JSONObject getGeo(String args) throws JsonProcessingException, IOException, JSONException {
	    	
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?"+args);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
		
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}


				
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			  StringBuilder responseStrBuilder = new StringBuilder();
					String output;
					System.out.println("\nOutput from Server .... \n");
					while ((output = br.readLine()) != null) {
						responseStrBuilder.append(output);
						//System.out.println(output);
			}
		
					 JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
					 
					return jsonObject;
	    }
	    
}
