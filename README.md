# XPTO API
### API Rest que irá gerenciar uma lista de cidades do Brasil.

[![N|Solid](https://i.dlpng.com/static/png/7264214_preview.png)
](https://spring.io/projects/spring-boot)


### Tecnologias Utilizadas
- Spring Boot
- H2 Database
- Hibernate
- Maven
- Java 8
- MySQL (Banco de Dados)

### Descrição

- Importa um arquivo CSV contendo as informações das cidades
- Salva essas informações no banco de dados
- Realiza os requests conforme solicitado
- Todos os retornos são realizados no formato JSON

### Instalação

- 1: Clone o repositório na sua máquina
- 2: Execute o comando: mvn clean install
- 3: Execute o script "startXPTO.sh" e escolha em qual ambiente quer que a aplicação seja exeuctada
- 4: Acesse através da url: http://localhost:8080/api/xpto/
- 5: Você pode usar o POSTMAN para acessar o serviço também
- 6: Debug na porta 5005


### Funcionalidades

1. Ler o arquivo CSV das cidades para a base de dados;
   - http://localhost:8080/api/xpto/lerArquivoCSV

2. Retornar somente as cidades que são capitais ordenadas por nome;
   - http://localhost:8080/api/xpto/cidadesQueSaoCapitais

3. Retornar o nome do estado com a maior e menor quantidade de cidades e a
   quantidade de cidades;
   - http://localhost:8080/api/xpto/estadoMaiorEMenor

4. Retornar a quantidade de cidades por estado;
   - http://localhost:8080/api/xpto/cidadesPorEstado

5. Obter os dados da cidade informando o id do IBGE;
   - http://localhost:8080/api/xpto/dadosCidadeById/{idIBGE}

6. Retornar o nome das cidades baseado em um estado selecionado;
   - http://localhost:8080/api/xpto/dadosCidadeByUF/{uf}

7. Permitir adicionar uma nova Cidade; (Cole o JSON no body e preencha de acordo com o exemplo)
   - http://localhost:8080/api/xpto/novaCidade
   ```
    {
       "ibge_id": 1,
       "uf": "SP",
       "name": "Exemplo",
       "capital": false,
       "lon": -72.9165010261,
       "lat": -7.5932225939,
       "no_accents": "Exemplo",
       "alternative_name": "",
       "microregion": "Exemplo",
       "mesoregion": "Exemplo"
   }
    ```
8. Permitir deletar uma cidade;
   - http://localhost:8080/api/xpto/deletarCidade/{idIBGE}
    
9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para
   filtrar. retornar assim todos os objetos que contenham tal string;
   - http://localhost:8080/api/xpto/stringPorColuna/{a}/{b}

10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens
    iguais;
    - http://localhost:8080/api/xpto/registroPorColuna/{a}

11. Retornar a quantidade de registros total;
    - http://localhost:8080/api/xpto/quantidadeDeRegistros

12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base
    na localização (distância em KM em linha reta);
    - http://localhost:8080/api/xpto/distanciaEntreCidades
