# Bancosil 🏦 - Sistema Bancário em Spring Boot
![Java](https://img.shields.io/badge/Java-21-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-green)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6-blue)

API REST bancária completa construída com **Spring Boot 3**, oferecendo sistema completo de autenticação JWT, operações financeiras seguras e validações robustas para gerenciamento de contas bancárias.

---

## 📋 Status do Projeto
✅ **Concluído**: Sistema completo com autenticação JWT, operações financeiras, validações com Value Objects e tratamento de exceções personalizado.

🚀 **Pronto para Produção**: Arquitetura escalável com Spring Security, documentação OpenAPI e suporte a múltiplos bancos de dados.

🔒 **Seguro**: Autenticação stateless com tokens JWT, senhas criptografadas com BCrypt e endpoints protegidos.

---

# ✨ Funcionalidades Implementadas

### 🔍 Sistema de Rastreabilidade
- **Registro automático**: Todas as operações financeiras são automaticamente logadas
- **Auditoria completa**: Histórico de depósitos, saques e transferências
- **Controle de acesso**: Logs restritos por permissões (admin vs usuário)
- **Busca temporal**: Filtros por intervalo de datas com validação
- **Segurança**: Validação de autorização para acessar logs de outros usuários

## Endpoints da API
### 👥 Autenticação (/auth) - PÚBLICO
- `POST /auth/login` - Autentica um usuário e retorna JWT token
- `POST /auth/register` - Registra novo usuário e retorna JWT token

### 💰 Operações Financeiras (/operation) - PROTEGIDO
- `POST /operation/deposit` - Realiza depósito na conta do usuário logado
- `POST /operation/withdraw` - Realiza saque na conta do usuário logado
- `POST /operation/transfer` - Realiza transferência PIX entre contas

### 👤 Gestão de Contas (/accounts) - PROTEGIDO
- `GET /accounts/{id}` - Busca conta por ID
- `GET /accounts/search?username={username}` - Busca conta por username
- `GET /accounts` - Lista todas as contas (paginação)
- `DELETE /accounts/{id}` - Deleta uma conta

### 📊 Gestão de Logs (Protegido)
| Método | Endpoint | Descrição | Códigos de Resposta |
| :--- | :--- | :--- | :--- |
| **GET** | `/logs` | Listar todos os logs (Admin apenas) | 200, 403, 500 |
| **GET** | `/logs/author?id={userId}` | Listar logs de um usuário específico | 200, 403, 500 |
| **GET** | `/logs/interval` | Listar logs por intervalo de datas | 200, 400, 500 |

### Gerenciamento de Dados
- `Cadastro de usuários`: Endpoints para cadastro e busca de contas
- `Validação de dados`: Uso de Value Objects para validar atributos como CPF, e-mail e endereço
- `Paginação`: Sistema completo de paginação em consultas de listagem

---

## 🔐 Sistema de Segurança JWT

### Autenticação
- **JWT Tokens** com expiração de 2 horas
- **Spring Security 6** com configuração personalizada
- **BCrypt** para hash de senhas
- **Stateless** - sem sessões no servidor

### Proteção de Endpoints
- ✅ **Públicos**: `/auth/*`, `/h2-console/**`, `/swagger-ui/**`
- 🔒 **Protegidos**: Todos os demais endpoints
- **Header obrigatório**: `Authorization: Bearer {token}`

### Componentes de Segurança
- `SecurityFilter` - Valida tokens JWT
- `CustomUserDetailsService` - Integração com entidade Account
- `TokenService` - Geração e validação de tokens
- `SecurityConfig` - Configuração do Spring Security

---

## 💰 Sistema de Operações Financeiras

### Padrão Strategy para Operações
- `Operation` interface para depósito e saque
- `TransferOperation` interface para transferências
- Implementações: `Deposit`, `Withdraw`, `TransferPix`

### Sistema de Auditoria
- Entidade `Log` registrando todas as operações
- `OperationType` enum para categorizar operações
- Registro de autor, receptor, valor e timestamp

---

## 🔒 Tratamento de Exceções Personalizadas

### Exceções Específicas Implementadas:
- `AccountNotFoundException` - Conta não encontrada (404)
- `UnauthorizedException` - Acesso não autorizado (401)
- `InsufficientBalanceException` - Saldo insuficiente (400)
- `SelfTransferException` - Tentativa de auto-transferência (400)
- `NegativeOperationException` - Valores negativos em operações (400)
- `InvalidCPFNumberException` - CPF inválido (400)
- `InvalidEmailException` - E-mail inválido (400)
- `ShortUsernameException` - Nome de usuário muito curto (400)
- `InvalidPasswordException` - Senha não atende aos requisitos (400)
- `ShortPasswordException` - Senha muito curta (400)
- `AccountAlreadyExistsException` - Conta já existe (400)
- `FailedLoginAttemptException` - Falha no login (400)
- `DataIntegrityViolationException` - Dados duplicados (409)
- `InvalidIntervalDateException` - Intervalo de datas inválido (400)

### Handler Global
- `@RestControllerAdvice` centralizando tratamento de erros
- Respostas HTTP apropriadas para cada tipo de exceção
- Mensagens claras e específicas para o usuário

---

## 🛠️ Tecnologias Utilizadas
- **Spring Boot 3.5.6**
- **Spring Security 6** + JWT
- **Spring Data JPA**
- **H2 Database** (desenvolvimento)
- **MySQL** (produção)
- **OpenAPI 3** (Swagger UI)
- **Lombok** - Redução de boilerplate
- **MapStruct** - Mapeamento entre DTOs e Entidades
- **Maven** - Gerenciamento de dependências
- **Java 21**

---

## 🏗️ Arquitetura e Padrões Implementados

### 🔷 Padrões de Projeto
- **JWT Authentication Pattern**: Autenticação stateless com tokens
- **Filter Chain Pattern**: Interceptação de requisições com SecurityFilter
- **Strategy Pattern**: Operações bancárias (Deposit, Withdraw, Transfer)
- **Factory Method**: Criação dinâmica de operações
- **DTO Pattern**: Segurança na transferência de dados
- **Value Objects**: Validação de CPF, e-mail, senha e username

---

### 📊 Sistema de Consultas

#### Endpoints de Busca
- `GET /accounts/{id}` - Busca conta por ID
- `GET /accounts/search?username={username}` - Busca conta por username (exato)
- `GET /accounts` - Lista todas as contas

---

## 📦 Estrutura do Projeto
```text
src/
├── main/
│   ├── java/com/github/bancosil/
│   │   ├── controller/       # Endpoints REST + interfaces
│   │   ├── dto/              # Data Transfer Objects  
│   │   ├── enums/            # Classes de enumerações
│   │   ├── exceptions/       # Exceções personalizadas
│   │   ├── handler/          # Tratamento global de exceções
│   │   ├── mapper/           # Conversores DTO/Entity
│   │   ├── model/            # Entidades + Value Objects
│   │   ├── repository/       # JPA Repositories
│   │   ├── security/         # Configurações de segurança JWT
│   │   ├── service/          # Lógica de negócio
│   │   ├── service/operation/# Operações bancárias (Strategy)
│   │   └── util/             # Constantes e utilitários
│   └── resources/            # Configurações
```

---

## 📝 Modelo de Dados
### Entidades Principais:
- `Account`: Entidade que representa uma conta bancária.
- `Log`: Registra todas as operações financeiras.

---

### Tipos de Conta  
- Conta Corrente (`CORRENTE`)
- Conta Poupança (`POUPANCA`)
- Conta Investimento (`INVESTIMENTO`)
- Conta Admin (`ADMIN`)

---

### Atributos:
- `id`
- `Username` (Value Object)
- `Password` (Value Object)
- `Email` (Value Object)
- `CPF` (Value Object, com validação e restrição de unicidade)
- `Address` (Value Object)
- `Money` (BigDecimal)

---

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

# 📋 Exemplos de Uso

### 💡 Novos Exemplos de Uso

## Auditoria e Logs
```bash
# Visualizar todos os logs (apenas admin)
GET /logs
Authorization: Bearer {token-admin}

# Visualizar meus próprios logs
GET /logs/author?id=123
Authorization: Bearer {token}

# Buscar logs por intervalo de datas
GET /logs/interval
Authorization: Bearer {token-admin}
{
    "startDate": "01-10-2024T00:00:00",
    "endDate": "31-10-2024T23:59:59"
}
```

## 🔑 Exemplos de Autenticação

### 1. Obter Token
```bash
POST /auth/login
{
    "username": "admin",
    "password": "admin"
}
```

### Criar Conta
```bash
POST /auth/register
{
    "username": "joao.silva",
    "email": "joao@email.com", 
    "password": "Senha123!",
    "cpf": "123.456.789-00",
    "type": "CORRENTE"
}
}
```
### Buscar Contas com Paginação
- `GET /accounts/search?username=joao`

### Realizar Transferência
- `POST /operation/transfer`

```bash
{
  "amount": 100.50,
  "id": 2
}
```

### 🔄 Mudanças Principais (Versão 1.7)

#### ✅ Adicionado
- **Sistema completo de logs** para auditoria de operações
- **Serviço de rastreabilidade** com registro automático
- **Controle de acesso granular** para visualização de logs
- **Busca por intervalo temporal** com validação de datas

#### 🎯 Aprimorado
- **Transparência operacional** - histórico completo de transações
- **Segurança** - validação de permissões para acesso a logs
- **Conformidade** - sistema de auditoria para requisitos regulatórios
- **Debugging** - ferramentas para análise de operações