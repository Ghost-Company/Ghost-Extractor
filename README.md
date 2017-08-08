# Ghost-Extractor

Sistema Backend Java Eclipse, o qual atualmente obtém dados das empresas licitadas, da base de dados do SAGRES contidas no arquivo.
No entanto, existe a possibilidade de se trabalhar com diversas bases exportadas para arquivos CSV.

## Funcionamento
O sistema realiza 4 operacoes:
- Obtém o cnpj das empresas da base do Sagres no arquivo EmpresasLicitadasNew.CSV; 
- Obtém o endereço através da API(https://github.com/alexsandrospecht/Cnpj4J) do webservice(https://www.receitaws.com.br/);      
- Com endereço realiza operação de geofancing para obter latitude e longitude;
- Envia informação para base de dados na cloud service do APP.