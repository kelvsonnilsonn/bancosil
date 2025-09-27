# Bancosil 🏦 - Sistema Bancário em Spring Boot
API REST bancária construída com **Spring Boot**, que oferece funcionalidades básicas para gerenciamento de contas bancárias.

---

## 📋 Status do Projeto
✅ **Concluído**: Sistema completo com autenticação, operações financeiras, validações robustas e tratamento de erros.

🚀 **Em produção**: Pronto para uso com arquitetura escalável e documentação completa.

---

## ✨ Funcionalidades Implementadas
### Sistema de Autenticação
Endpoints REST para login e logout de usuários.

### Endpoints da API
#### 👥 Autenticação (/auth)
- `POST /auth/login` - Autentica um usuário
- `POST /auth/logout` - Desloga o usuário atual

#### 💳 Gestão de Contas (/accounts)
- `POST /accounts/create` - Cria uma nova conta
- `GET /accounts/{id}` - Busca uma conta por ID
- `GET /accounts/search?username={username}&page={page}&size={size}` - Busca contas por nome de usuário (paginação)
- `GET /accounts?page={page}&size={size}` - Lista todas as contas (paginação)
- `DELETE /accounts/{id}` - Deleta uma conta

#### 💰 Operações Financeiras (/operation)
- `POST /operation/deposit` - Realiza um depósito na conta do usuário logado
- `POST /operation/withdraw` - Realiza um saque na conta do usuário logado
- `POST /operation/transfer` - Realiza uma transferência PIX entre contas

### Tipos de Conta
- Conta Corrente (`Corrente`)

### Gerenciamento de Dados
- Cadastro de usuários: Endpoints para cadastro e busca de contas
-Validação de dados: Uso de Value Objects para validar atributos como CPF, e-mail e endereço
-Paginação: Sistema completo de paginação em consultas de listagem

## 💰 Sistema de Operações Financeiras

### Padrão Strategy para Operações
- `Operation` interface para depósito e saque
- `TransferOperation` interface para transferências
- Implementações: `Deposit`, `Withdraw`, `TransferPix`

### Sistema de Auditoria
- Entidade `Log` registrando todas as operações
- `OperationType` enum para categorizar operações
- Registro de autor, receptor, valor e timestamp

## 🔒 Tratamento de Exceções Personalizadas

### Exceções Específicas Implementadas:
- `AccountNotFoundException` - Conta não encontrada (404)
- `UnauthorizedException` - Acesso não autorizado (401)
- `InsufficientBalanceException` - Saldo insuficiente (400)
- `SelfTransferException` - Tentativa de auto-transferência (400)
- `NegativeOperationException` - Valores negativos em operações (400)
- `InvalidCPFNumberException` - CPF inválido (400)
- `InvalidEmailException` - E-mail inválido (400)

### Handler Global
- `@RestControllerAdvice` centralizando tratamento de erros
- Respostas HTTP apropriadas para cada tipo de exceção
---

## 🛠️ Tecnologias Utilizadas
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **Lombok**
- **H2 Database** (dev)
- **MySQL** (prod)
- **OpenAPI 3** (Swagger UI)
- **Maven**
---

## 🏗️ Arquitetura e Padrões Implementados

### 🔷 Padrões de Projeto
- **Strategy Pattern**: Operações bancárias (Deposit, Withdraw, Transfer)
- **Factory Method**: Criação dinâmica de operações
- **DTO Pattern**: Segurança na transferência de dados
- **Value Objects**: Validação de CPF, e-mail, senha e username

### 📊 Sistema de Paginação
- Implementação de `PageResponseDTO` para consultas paginadas
- Parâmetros `page` e `size` em endpoints de listagem
- Ordenação por username nas consultas

## 📦 Estrutura do Projeto
```text
src/
├── main/
│   ├── java/com/github/bancosil/
│   │   ├── config/           # Configurações globais
│   │   ├── controller/       # Endpoints REST + interfaces
│   │   ├── dto/              # Data Transfer Objects  
│   │   ├── model/            # Entidades + Value Objects
│   │   ├── repository/       # JPA Repositories
│   │   ├── service/          # Lógica de negócio
│   │   ├── service/operation/# Operações bancárias (Strategy)
│   │   ├── handler/          # Tratamento global de exceções
│   │   ├── mapper/           # Conversores DTO/Entity
│   │   └── util/             # Constantes e utilitários
│   └── resources/            # Configurações
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
## 🚀 Como Executar
### Pré-requisitos
- Java 21+
- Maven 3.6+

### Execução
```bash
mvn spring-boot:run
```

## Documentação Interativa
- Swagger UI: http://localhost:8080/swagger-ui.html

## 📋 Exemplos de Uso
### Criar Conta
- `POST /accounts/create`
```bash
{
    "username": "joao.silva",
    "email": "joao@email.com",
    "password": "senha123",
    "cpf": "123.456.789-00"
}
```
### Buscar Contas com Paginação
- `GET /accounts/search?username=joao&page=0&size=10`

### Realizar Transferência
- `POST /operation/transfer`

```bash
{
  "amount": 100.50,
  "id": 2
}
```
