# Ghost-Extractor

Sistema Backend Java Eclipse, o qual atualmente obt�m dados das empresas licitadas, da base de dados do SAGRES contidas no arquivo.
No entanto, existe a possibilidade de se trabalhar com diversas bases exportadas para arquivos CSV.

## Funcionamento
O sistema realiza 4 operacoes:
- Obt�m o cnpj das empresas da base do Sagres no arquivo EmpresasLicitadasNew.CSV; 
- Obt�m o endere�o atrav�s da API(https://github.com/alexsandrospecht/Cnpj4J) do webservice(https://www.receitaws.com.br/);      
- Com endere�o realiza opera��o de geofancing para obter latitude e longitude;
- Envia informa��o para base de dados na cloud service do APP.