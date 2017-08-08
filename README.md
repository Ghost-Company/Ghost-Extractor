# Ghost-Extractor

Sistema Backend Java Eclipse, o qual atualmente obt�m dados das empresas licitadas, da base de dados do SAGRES contidas no arquivo.
No entanto, existe a possibilidade de se trabalhar com diversas bases exportadas para arquivos CSV.

## Funcionamento
O sistema realiza 4 operacoes:
1 - Obt�m o cnpj das empresas da base do Sagres e no arquivo EmpresasLicitadasNew.CSV; 
2 - Obt�m o endere�o atrav�s da API(https://github.com/alexsandrospecht/Cnpj4J) do webservice(https://www.receitaws.com.br/)      
3 - Com endere�o realiza opera��o de geofancing para obter latitude e longitude.
4 - Envia informa��o para base de dados na cloud service do APP.