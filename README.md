# Cloud Parking

Cloud Parking é uma aplicação Spring Boot desenvolvida para fins de estudo, focada no gerenciamento de um estacionamento. A aplicação permite o registro de entradas e saídas de veículos, cálculo de tarifas com base no tempo de permanência e gerenciamento de dados de estacionamento.

## Tecnologias Utilizadas

- **Spring Boot 3.5.3**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para persistência de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Spring Security**: Para autenticação e autorização.
- **ModelMapper 3.0.0**: Para mapeamento de objetos.
- **Springdoc OpenAPI 2.6.0**: Para documentação da API com Swagger UI.
- **Maven**: Gerenciador de dependências e build.

## Pré-requisitos

- **Java 17**: Versão mínima requerida.
- **Maven 3.6+**: Para build e gerenciamento de dependências.
- **PostgreSQL 12+**: Banco de dados configurado com as credenciais especificadas no `application.properties`.

## Configuração do Ambiente

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/cloud-parking.git
   cd cloud-parking
   ```

2. **Configure o banco de dados**:
   - Crie um banco de dados PostgreSQL chamado `park`.
   - Atualize as credenciais no arquivo `src/main/resources/application.properties`, se necessário:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/park
     spring.datasource.username=postgres
     spring.datasource.password=root
     ```

3. **Instale as dependências**:
   ```bash
   mvn clean install
   ```

## Executando o Projeto

1. **Inicie a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

2. **Acesse a aplicação**:
   - A API estará disponível em `http://localhost:8080/parking`.
   - O Swagger UI pode ser acessado em `http://localhost:8080/swagger-ui.html`.

## Estrutura do Projeto

- **`/src/main/java/com/armtech/parking`**:
  - **`config`**: Configurações de segurança e OpenAPI.
  - **`controller`**: Controladores REST para gerenciamento de estacionamento.
  - **`controller/dto`**: Data Transfer Objects (DTOs) para entrada e saída de dados.
  - **`controller/mapper`**: Mapeadores para conversão entre entidades e DTOs.
  - **`exception`**: Exceções personalizadas.
  - **`model`**: Entidades JPA, como a classe `Park`.
  - **`repository`**: Repositórios para acesso a dados.
  - **`service`**: Serviços de negócio, incluindo lógica de cálculo de tarifas.

- **`/src/main/resources`**:
  - **`application.properties`**: Configurações da aplicação.

## Documentação da API

A documentação da API é gerada automaticamente usando Springdoc OpenAPI e pode ser acessada via Swagger UI em `http://localhost:8080/swagger-ui.html`. Ela fornece detalhes sobre todos os endpoints, como:

- `GET /parking`: Lista todos os veículos estacionados.
- `POST /parking`: Registra a entrada de um veículo.
- `POST /parking/{id}`: Registra a saída e calcula a tarifa.

## Contribuição

Contribuições são bem-vindas! Siga as diretrizes abaixo:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## Contato

Para suporte ou perguntas adicionais, entre em contato com [seu-email@exemplo.com](mailto:seu-email@exemplo.com).