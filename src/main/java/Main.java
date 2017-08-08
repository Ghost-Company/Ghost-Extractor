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
	    	ServiceApp serviceApp = new ServiceApp();
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
					
					if(serviceApp.checkOnlyNumbers(value[0]))
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
				Thread.sleep(1000);
				if ((empresa = serviceApp.companyData(CNPJ)) != null){					
					listEmpresa.add(empresa);				   
					serviceApp.postData(empresa);
				    
				}	
			}
	
	    }
	        
	    
}
