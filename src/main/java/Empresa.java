import java.util.Date;

public class Empresa {
	
	String nome;
	String cnpj;
	String abertura;
	String status;
	String bairro;
	String logradouro;
	String longitude;
	String latitude;
	
	public Empresa(String nome, String cnpj, String abertura, String status, String bairro, String logradouro/*, String longitude, String latitude*/){
		this.nome = nome;
		this.cnpj = cnpj;
		this.abertura = abertura;
		this.status = status;
		this.bairro = bairro;
		this.logradouro = logradouro;
		//this.longitude = longitude;
		//this.latitude = latitude;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getAbertura() {
		return abertura;
	}
	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
