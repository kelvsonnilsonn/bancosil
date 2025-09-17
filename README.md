# Bancosil 🏦
Sistema bancário construído com Spring, oferecendo funcionalidades básicas de gerenciamento de contas bancárias.

___

## 📋 Status do Projeto 
Desenvolvido: suficientemente concluído para um uso comum. 
Passível de outras modificações para acréscimo de funcionalidades.

___

## ✨ Funcionalidades Implementadas
### Sistema de Autenticação: 
Login e logout de usuários

### Tipos de Conta:
- Conta Corrente (Corrente)
- Conta Poupança (Poupanca)
- Conta Investimento (Investimento)

### Operações Bancárias:
- Depósito
- Saque 
- Transferência via PIX

### Gerenciamento de Dados:
- Cadastro de usuários
- Endereço completo com validações
- Dados pessoais (CPF, email, etc.)

### Sistema de Exceções Personalizadas:
- Tratamento específico para erros de negócio

___

## 🛠️ Tecnologias Utilizadas
- Java 21
- Spring 3.5.5
- Spring Data JPA
- Lombok (para redução de código boilerplate)
- H2 Database (desenvolvimento)
- MySQL (pronto para produção)
- Maven (gerenciamento de dependências)

___

## 📦 Estrutura do Projeto

```text
src/
├── main/
│   ├── java/com/github/bancosil/
│   │   ├── config/          # Configurações do sistema
│   │   ├── model/           # Entidades do domínio
│   │   ├── valueobjects/    # Value Objects
│   │   ├── service/         # Lógica de negócio
│   │   ├── repository/      # Camada de persistência
│   │   └── exception/       # Tratamento de exceções personalizadas
│   └── resources/          # Configurações e propriedades
```

___

## 📝 Modelo de Dados
- Entidade Principal: **Account**
- Herança: Estratégia **SINGLE_TABLE** com discriminator

### Atributos:
- Username (Value Object)
- Password (Value Object)
- Email (Value Object)
- CPF (Value Object, único)
- Address (Value Object)
- Money (BigDecimal)

___

## 🔧 Configuração
### Banco de Dados
O projeto suporta dois bancos de dados:

- **H2**: Banco em memória para desenvolvimento

- **MySQL**: Banco de produção

Configure no arquivo application.properties:

```properties
# Para H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true

# Para MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/bancosil
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
```

___

## 🎯 Serviços Implementados
### AccountService
- Criação de contas
- Exclusão de contas
- Busca por ID
- Atualização de contas
- Sistema de login

### OperationalService
- Operações de saque (withdraw)
- Operações de depósito (deposit)
- Padrão de operações através da interface Operation
