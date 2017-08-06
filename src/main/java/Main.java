import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.codehaus.jackson.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.alexsandrospecht.util.ConsultaCnpj;
import com.github.alexsandrospecht.wrapper.AtividadeWrapper;
import com.github.alexsandrospecht.wrapper.RetornoWrapper;

public class Main {
	   //private static final String GOOGLE_CNPJ = "06990590000204";

	    public static void main(String[] args) throws Exception {
	    	
	    	ArrayList<String> list = new ArrayList<String>();
	    	ArrayList<Empresa> listEmpresa = new ArrayList<Empresa>();
	    	
			try{
				FileReader fr = new FileReader("EmpresasLicitadasNew.CSV");
				BufferedReader br = new BufferedReader(fr);
								
				br.readLine(); //Ignore First Line
				while(true){
					String data = br.readLine();
					if (data == null) break;
					String[] value = data.split(";");
					
					if(checkOnlyNumbers(value[0]))
						list.add(String.format ("%014d", Long.parseLong(value[0])));
				}
				br.close();
			}
			catch (IOException e){
				e.printStackTrace();
				System.out.println("Impossivel ler no arquivo");
			}
			
			for(String CNPJ : list){
				Empresa empresa = new Empresa();
				//Thread.sleep(1000);
				if ((empresa = companyData(CNPJ)) != null)
					listEmpresa.add(empresa);
			}
	
	    }
	    
	    
	    
	    public static JSONObject getGeo(String args) throws JsonProcessingException, IOException, JSONException {
	    	
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?" + args);
			//System.out.println("URL: " + url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
		
			conn.setRequestProperty("Accept", "application/json");
			
			/*if (conn.getResponseCode() != 1000) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}*/


			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder responseStrBuilder = new StringBuilder();
			String output;
			System.out.println("\nOutput from Server .... \n");
			while ((output = br.readLine()) != null) {
				responseStrBuilder.append(output);
			}
		
			JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
					 
			return jsonObject;
	    }
	    
	    public static boolean checkOnlyNumbers(String data){
	    	
 			for (char num: data.toCharArray()) { //Check Only Numbers
 				if (!Character.isDigit(num))
 					return false;
 			}
	    	return true;
	    }
	    
	    public static Empresa companyData(String data) throws JsonProcessingException, IOException, JSONException{
	    	
	    	RetornoWrapper wp = ConsultaCnpj.consultaCnpj(data);
	        Empresa empresa = new Empresa(wp.getNome(), wp.getCnpj(), String.valueOf(wp.getAbertura()), wp.getStatus(), wp.getBairro(), wp.getLogradouro());
	        String arg = "address=" + URLEncoder.encode(wp.getBairro()+wp.getLogradouro(), "UTF-8");//wp.getBairro()+wp.getLogradouro()
	        
			if (wp.getBairro().isEmpty() || wp.getLogradouro().isEmpty())
				return null;
				
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
	        	System.out.println("Err extrac geo: " + e);
	        }
	        
	        AtividadeWrapper aw = wp.getAtividade_principal().iterator().next();
	        System.out.println(aw.getCode());
	        System.out.println(aw.getText());
	    	
	    	return empresa;
	    }
	    
}
