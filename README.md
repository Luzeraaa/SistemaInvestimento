<h1>Sistema de Investimento</h1>

Olá, sejam bem-vindos

<h1>Nesse sistema apresento meu conhecimento tecnico em relação ao desenvolvimento back-end JAVA na criação de APIs REST<h1>

<h2>Primeira versão API Rest para investidores <h2>

<h2>Para acessar a página de testes do swagger caso teste localmente utilize: <b>http://localhost:8080/swagger-ui/index.html</b>
  
<ul>
  <li>Java 17</li>
  <li>Spring Boot 3.0.6</li>
  <li>Spring Data JPA</li>
  <li>Spring Dev Tools</li>
  <li>Spring Security</li>
  <li>Spring Doc</li>
  <li>Token JWT</li>
  <li>Auth0</li>
  <li>Migrations/Flyway</li>
  <li>Banco de dados H2</li>
  <li>Lombok</li>
  <li>JPA/Hibernate</li>
  <li>SQL</li>
  <li>Swagger</li>
</ul>


<h2> Segue abaixo testes de requisições utilizando o Postman <h2>

<h3>POST</h3>
/investidor

{
   "nome": "Fulano de Tal",
   "login": "c@gmail.com",
   "dataNascimento": "2022-05-01T12:00:00",
   "senha": "123456",
   "cpf": "22233344222"
}

/login
{
    "login": "c@gmail.com",
    "senha": "123456"
}

/investidor/depositar
{
    "numeroConta": 8376,
    "valor": 1000
}

/investidor/sacar
{
    "numeroConta": 8376,
    "valor": 1000
}

/investidor/transferir
{
    "numeroContaOrigem" : 628,
    "numeroContaDestino" : 628,
    "valor" : 200
}

Endpoint administrativo para ativar ou inativar a conta no sistema
/admin/{numeroConta}/{senha}/{boolean} - senha default 1234


Agradeço pelo tempo e oportunidade de demonstrar minhas habilidades!
