# Bancosil 🏦
API REST bancária construída com **Spring Boot**, que oferece funcionalidades básicas para gerenciamento de contas bancárias.

---

## 📋 Status do Projeto
✅ **Desenvolvido**: A API está funcional, com endpoints para autenticação, gerenciamento de contas e operações bancárias. É considerada "suficientemente concluída" para um uso comum.

➡️ **Passível de melhorias**: O projeto pode receber novas funcionalidades, como um sistema de autenticação mais robusto (JWT), otimizações e mais endpoints.

---

## ✨ Funcionalidades Implementadas
### Sistema de Autenticação
Endpoints REST para login e logout de usuários.

### Endpoints da API
- `POST /auth/login` - Autentica um usuário.
- `POST /auth/logout` - Desloga o usuário atual.
- `POST /accounts/create` - Cria uma nova conta.
- `GET /accounts/{id}` - Busca uma conta por ID.
- `GET /accounts/search?username=...` - Busca contas por nome de usuário.
- `DELETE /accounts/delete/{id}` - Deleta uma conta.
- `GET /accounts` - Lista todas as contas.
- `POST /operation/deposit` - Realiza um depósito na conta do usuário logado.
- `POST /operation/withdraw` - Realiza um saque na conta do usuário logado.
- `POST /operation/transfer` - Realiza uma transferência PIX entre contas.

### Tipos de Conta
- Conta Corrente (`Corrente`)

### Gerenciamento de Dados
- **Cadastro de usuários**: Endpoints para cadastro e busca de contas.
- **Validação de dados**: Uso de **Value Objects** para validar atributos como CPF, e-mail e endereço.

### Sistema de Exceções Personalizadas
Tratamento de erros de negócio e validações, retornando respostas HTTP apropriadas (`404 NOT FOUND`, `400 BAD REQUEST`, etc.).

---

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3.5.5**: Framework principal para construção da API.
- **Spring Data JPA**: Abstração para a camada de persistência.
- **Lombok**: Redução de código *boilerplate*.
- **H2 Database**: Banco de dados em memória para desenvolvimento.
- **MySQL**: Banco de dados relacional para produção.
- **Maven**: Gerenciamento de dependências.

---

## 📦 Estrutura do Projeto
```text
src/
├── main/
│   ├── java/com/github/bancosil/
│   │   ├── config/           # Configurações globais e tratamento de exceções
│   │   ├── controller/       # Camada de Endpoints REST
│   │   ├── dto/               # Data Transfer Objects
│   │   ├── model/             # Entidades de domínio (Contas e Log)
│   │   ├── repository/        # Camada de persistência (JPA Repositories)
│   │   ├── service/           # Lógica de negócio e operações
│   │   └── exception/         # Exceções personalizadas
│   └── resources/            # Configurações e propriedades da aplicação
```

## 📝 Modelo de Dados
### Entidades Principais:
- `Account`: Entidade abstrata que representa uma conta bancária.
    - `Herança`: Estratégia SINGLE_TABLE com DiscriminatorColumn para diferenciar os tipos de conta (Corrente, Poupanca, Investimento).

- `Log`: Registra todas as operações financeiras.

### Atributos:
- `id`
- `Username` (Value Object)
- `Password` (Value Object)
- `Email` (Value Object)
- `CPF` (Value Object, com validação e restrição de unicidade)
- `Address` (Value Object)
- `Money` (BigDecimal)

## 🔧 Configuração
### Banco de Dados
O projeto suporta dois bancos de dados:
- **H2**: Banco em memória para desenvolvimento.
- **MySQL**: Banco relacional para ambientes de produção.

Para configurar, basta alterar as propriedades no arquivo `application.properties`:

```properties
# Para H2 (desenvolvimento)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true

# Para MySQL (produção)
# spring.datasource.url=jdbc:mysql://localhost:3306/bancosil
# spring.datasource.username=seu-usuario
# spring.datasource.password=sua-senha
# spring.jpa.hibernate.ddl-auto=update
```
