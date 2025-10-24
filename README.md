# Bancosil 🏦 - Sistema Bancário em Spring Boot
![Java](https://img.shields.io/badge/Java-21-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-green)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6-blue)

API REST bancária completa construída com **Spring Boot 3**, oferecendo sistema completo de autenticação JWT, operações financeiras seguras e validações robustas para gerenciamento de contas bancárias.

---

## 📋 Status do Projeto
✅ **Em desenvolvimento**: Sistema completo com autenticação JWT, operações financeiras, validações com Value Objects e tratamento de exceções personalizado.

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
| Método | Endpoint | Descrição | Permissões |
|:---|:---|:---|:---|
| **POST** | `/auth/login` | Autentica um usuário e retorna JWT token | PÚBLICO |
| **POST** | `/auth/register` | Registra novo usuário e retorna JWT token | PÚBLICO |

### 💰 Operações Financeiras (/operation) - PROTEGIDO
| Método | Endpoint | Descrição | Permissões |
|:---|:---|:---|:---|
| **POST** | `/operation/deposit` | Realiza depósito na conta do usuário logado | PROTEGIDO |
| **POST** | `/operation/withdraw` | Realiza saque na conta do usuário logado | PROTEGIDO |
| **POST** | `/operation/transfer` | Realiza transferência PIX entre contas | PROTEGIDO |

### 📈 Event Store (/events) - PROTEGIDO
| Método | Endpoint | Descrição | Permissões |
|:---|:---|:---|:---|
| **GET** | `/events` | Meus eventos (usuário logado) | Todos usuários |
| **POST** | `/events/my-events/interval` | Meus eventos por intervalo temporal | Todos usuários |
| **GET** | `/admin/events` | Todos os eventos do sistema | Apenas Admin |
| **POST** | `/admin/events/interval` | Eventos do sistema por intervalo | Apenas Admin |
| **POST** | `/admin/events/user-events/interval` | Eventos de usuário específico por intervalo | Apenas Admin |
| **GET** | `/admin/events/user/{userId}` | Eventos de um usuário específico | Apenas Admin |
| **GET** | `/admin/events/aggregate/{aggregateId}` | Eventos de um aggregate específico | Apenas Admin |
| **GET** | `/admin/events/type/{aggregateType}` | Eventos por tipo de aggregate | Apenas Admin |

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
- **Hypersistence Utils** - Suporte a JSON nativo no Hibernate
- **Maven** - Gerenciamento de dependências
- **Java 21**

---

## 🏗️ Arquitetura e Padrões Implementados

### 🔷 Padrões de Projeto
- **Event Sourcing**: Armazenamento de estado como sequência de eventos imutáveis
- **JWT Authentication Pattern**: Autenticação stateless com tokens
- **Filter Chain Pattern**: Interceptação de requisições com SecurityFilter
- **Strategy Pattern**: Operações bancárias (Deposit, Withdraw, Transfer)
- **Factory Method**: Criação dinâmica de operações
- **DTO Pattern**: Segurança na transferência de dados
- **Value Objects**: Validação de CPF, e-mail, senha e username

---

## 📦 Estrutura do Projeto
```text
src/
├── main/
│   ├── java/com/github/bancosil/
│   │   ├── controller/       # Endpoints REST + interfaces
│   │   ├── dto/              # Data Transfer Objects  
│   │   ├── enums/            # Classes de enumerações
│   │   ├── event/            # Event Store e Event Sourcing
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
 
### Entidade Event Store:
- `EventStore`: Armazena todos os eventos do sistema de forma imutável
    - `id`: Identificador único do evento
    - `aggregateType`: Tipo do aggregate (ACCOUNT, OPERATION, etc.)
    - `accountId`: ID da conta relacionada ao evento
    - `eventType`: Tipo específico do evento
    - `eventData`: Dados do evento em formato JSON
    - `createdAt`: Timestamp de criação do evento
    - 
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

### 🔄 Mudanças Principais (Versão 2.0)

#### ✅ Arquitetura Event Sourcing
- **Event Store Completo**: Implementação completa do padrão Event Sourcing
- **Armazenamento JSON**: Eventos armazenados em formato JSON nativo
- **Consultas Temporais**: Busca avançada por intervalos de datas
- **Separação de Concerns**: Eventos segregados por usuário e aggregate

#### ✅ Novos Endpoints
- **API de Eventos**: Endpoints completos para consulta de eventos
- **Controle de Acesso Granular**: Diferentes níveis de acesso para usuários e admins
- **Filtros Avançados**: Busca por usuário, aggregate, tipo e intervalo temporal

#### ✅ Aprimoramentos de Auditoria
- **Rastreabilidade Total**: Capacidade de reconstruir qualquer estado passado
- **Imutabilidade**: Eventos armazenados de forma permanente e imutável
- **Transparência**: Visibilidade completa de todas as operações do sistema

#### 🎯 Benefícios Adicionais
- **Debugging Avançado**: Capacidade de reproduzir cenários específicos
- **Business Intelligence**: Dados ricos para análise e tomada de decisão
- **Resiliência**: Sistema mais robusto para recuperação de falhas