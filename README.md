# Ghost-Extractor

Sistema Backend Java Eclipse, o qual atualmente obtém dados das empresas licitadas, da base de dados do SAGRES contidas no arquivo.
No entanto, existe a possibilidade de se trabalhar com diversas bases exportadas para arquivos CSV.

## Funcionamento
O sistema realiza 4 operacoes:
1 - Obtém o cnpj das empresas da base do Sagres e no arquivo EmpresasLicitadasNew.CSV; 
2 - Obtém o endereço através da API(https://github.com/alexsandrospecht/Cnpj4J) do webservice(https://www.receitaws.com.br/)      
3 - Com endereço realiza operação de geofancing para obter latitude e longitude.
4 - Envia informação para base de dados na cloud service do APP.