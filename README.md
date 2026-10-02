# RUPE API

API REST para **geração, consulta e processamento de pagamentos de RUPE**, desenvolvida com **Java 21 e Spring Boot**.

A aplicação está disponível para execução local e também possui uma instância publicada na cloud.

---

## 🚀 API em produção

A aplicação está disponível em:

**https://rupe-api.onrender.com**

### Base URL

```text
https://rupe-api.onrender.com
```

### Swagger UI

```text
https://rupe-api.onrender.com/swagger-ui/index.html
```

### OpenAPI

```text
https://rupe-api.onrender.com/v3/api-docs
```

A API pode ser utilizada directamente através da instância publicada, sem necessidade de executar o projecto localmente.

---

# Funcionalidades

A API disponibiliza os seguintes recursos:

* Listagem dos serviços disponíveis;
* Geração de RUPE;
* Consulta de RUPE;
* Processamento de pagamentos;
* Idempotência no processamento de pagamentos.

---

# Endpoints

| Método | Endpoint                     | Descrição                     |
| ------ | ---------------------------- | ----------------------------- |
| `GET`  | `/api/v1/rupes/servicos`     | Lista os serviços disponíveis |
| `POST` | `/api/v1/rupes`              | Gera uma RUPE                 |
| `GET`  | `/api/v1/rupes/{referencia}` | Consulta uma RUPE             |
| `POST` | `/api/v1/pagamentos`         | Processa um pagamento         |

---

# REST Client

Os requests da API podem ser executados através de um ficheiro `.http`.

O projecto pode manter os exemplos em:

```text
requests/
└── rupe-api.http
```

O ficheiro pode ser executado directamente através do **REST Client do VS Code** ou de um IDE compatível com HTTP Client.

---

## Configuração do ambiente

### Cloud

Para consumir a API publicada:

```http
@baseUrl = https://rupe-api.onrender.com
```

### Local

Para utilizar a aplicação localmente:

```http
@baseUrl = http://localhost:8080
```

Desta forma, os mesmos requests podem ser utilizados nos dois ambientes, alterando apenas a variável `@baseUrl`.

---

# 1. Listar serviços

Lista os serviços disponíveis para geração de RUPE.

```http
GET {{baseUrl}}/api/v1/rupes/servicos
Accept: application/json
```

### URL em produção

```text
https://rupe-api.onrender.com/api/v1/rupes/servicos
```

### Exemplo de resposta

```json
[
  {
    "codigo": "IAPI-MAR-001",
    "nome": "Registo de Marca",
    "emolumentos": [
      {
        "nome": "Taxa oficial",
        "valor": 11176.00
      }
    ]
  }
]
```

O código do serviço é utilizado na geração da RUPE.

---

# 2. Gerar RUPE

Cria uma nova referência para pagamento.

```http
POST {{baseUrl}}/api/v1/rupes
Content-Type: application/json
Accept: application/json

{
  "nif": "009150115UE044",
  "nomeContribuinte": "Contribuinte Teste",
  "codigoServico": "IAPI-MAR-001",
  "dataExpiracao": "2026-11-01T23:59:59"
}
```

### URL em produção

```text
https://rupe-api.onrender.com/api/v1/rupes
```

### Resposta

```json
{
  "referencia": "00010100000000000016",
  "nif": "009150115UE044",
  "nomeContribuinte": "Contribuinte Teste",
  "codigoServico": "IAPI-MAR-001",
  "descricaoServico": "Registo de Marca",
  "valor": 11176.00,
  "dataEmissao": "2026-10-02T16:10:13.555954",
  "dataExpiracao": "2026-11-01T23:59:59",
  "estado": "PENDENTE",
  "numeroRecibo": null,
  "dataPagamento": null
}
```

A referência retornada deve ser utilizada para consultar ou pagar a RUPE.

---

# 3. Consultar RUPE

Consulta uma RUPE através da referência.

```http
GET {{baseUrl}}/api/v1/rupes/00010100000000000016
Accept: application/json
```

### URL em produção

```text
https://rupe-api.onrender.com/api/v1/rupes/00010100000000000016
```

### Resposta

```json
{
  "referencia": "00010100000000000016",
  "nomeContribuinte": "Contribuinte Teste",
  "codigoServico": "IAPI-MAR-001",
  "descricaoServico": "Registo de Marca",
  "valor": 11176.00,
  "dataEmissao": "2026-10-02T16:10:13.555954",
  "dataExpiracao": "2026-11-01T23:59:59",
  "estado": "PENDENTE",
  "numeroRecibo": null,
  "dataPagamento": null
}
```

---

# 4. Processar pagamento

Processa o pagamento de uma RUPE.

Este endpoint exige o header `Idempotency-Key`.

```http
POST {{baseUrl}}/api/v1/pagamentos
Content-Type: application/json
Accept: application/json
Idempotency-Key: PAGAMENTO-RUPE-00010100000000000016-001

{
  "referencia": "00010100000000000016",
  "numeroRecibo": "REC-2026-000001",
  "dataPagamento": "2026-10-02T16:15:00"
}
```

### URL em produção

```text
https://rupe-api.onrender.com/api/v1/pagamentos
```

### Resposta

```json
{
  "referencia": "00010100000000000016",
  "nif": "009150115UE044",
  "nomeContribuinte": "Contribuinte Teste",
  "codigoServico": "IAPI-MAR-001",
  "descricaoServico": "Registo de Marca",
  "valor": 11176.00,
  "dataEmissao": "2026-10-02T16:10:13.555954",
  "dataExpiracao": "2026-11-01T23:59:59",
  "estado": "PAGO",
  "numeroRecibo": "REC-2026-000001",
  "dataPagamento": "2026-10-02T16:15:00"
}
```

---

# 5. Consultar após o pagamento

Depois do pagamento, a mesma referência pode ser consultada novamente:

```http
GET {{baseUrl}}/api/v1/rupes/00010100000000000016
Accept: application/json
```

A resposta deverá apresentar:

```json
{
  "referencia": "00010100000000000016",
  "estado": "PAGO",
  "numeroRecibo": "REC-2026-000001",
  "dataPagamento": "2026-10-02T16:15:00"
}
```

---

# 6. Testar idempotência

A mesma operação pode ser reenviada utilizando a mesma `Idempotency-Key`.

```http
POST {{baseUrl}}/api/v1/pagamentos
Content-Type: application/json
Accept: application/json
Idempotency-Key: PAGAMENTO-RUPE-00010100000000000016-001

{
  "referencia": "00010100000000000016",
  "numeroRecibo": "REC-2026-000001",
  "dataPagamento": "2026-10-02T16:15:00"
}
```

A `Idempotency-Key` identifica a operação lógica de pagamento.

Para uma nova operação deve ser utilizada uma nova chave.

---

# Fluxo completo

```text
GET /api/v1/rupes/servicos
            │
            ▼
       Código serviço
            │
            ▼
POST /api/v1/rupes
            │
            ▼
       RUPE PENDENTE
            │
            ▼
GET /api/v1/rupes/{referencia}
            │
            ▼
POST /api/v1/pagamentos
       + Idempotency-Key
            │
            ▼
         RUPE PAGO
            │
            ▼
GET /api/v1/rupes/{referencia}
            │
            ▼
       Estado: PAGO
```

---

# Executar localmente

## Pré-requisitos

* Java 21
* Docker
* Git

O projecto utiliza Maven Wrapper.

## Clonar

```bash
git clone git@github.com:alfredobaptista/rupe.git
cd rupe
```

## Configurar PostgreSQL

A aplicação necessita de uma instância PostgreSQL.

Depois de configurar a base de dados de acordo com as propriedades da aplicação, executar:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

As migrações da base de dados são geridas pelo Flyway.

---

# Executar os testes

```bash
./mvnw test
```

---

# Gerar o build

```bash
./mvnw clean package
```

Para gerar o artefacto sem executar os testes:

```bash
./mvnw clean package -DskipTests
```

---

# Docker

Construir a imagem:

```bash
docker build -t rupe:latest .
```

Executar:

```bash
docker run --rm \
  -p 8080:8080 \
  rupe:latest
```

---

# Swagger / OpenAPI

## Local

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

## Cloud

Swagger UI:

```text
https://rupe-api.onrender.com/swagger-ui/index.html
```

OpenAPI:

```text
https://rupe-api.onrender.com/v3/api-docs
```

---

# Exemplo de utilização em produção

A instância cloud pode ser utilizada directamente sem instalar o projecto.

### Consultar serviços

```http
GET https://rupe-api.onrender.com/api/v1/rupes/servicos
Accept: application/json
```

### Gerar RUPE

```http
POST https://rupe-api.onrender.com/api/v1/rupes
Content-Type: application/json

{
  "nif": "009150115UE044",
  "nomeContribuinte": "Contribuinte Teste",
  "codigoServico": "IAPI-MAR-001",
  "dataExpiracao": "2026-11-01T23:59:59"
}
```

### Consultar RUPE

```http
GET https://rupe-api.onrender.com/api/v1/rupes/00010100000000000016
Accept: application/json
```

### Processar pagamento

```http
POST https://rupe-api.onrender.com/api/v1/pagamentos
Content-Type: application/json
Idempotency-Key: PAGAMENTO-RUPE-00010100000000000016-001

{
  "referencia": "00010100000000000016",
  "numeroRecibo": "REC-2026-000001",
  "dataPagamento": "2026-10-02T16:15:00"
}
```

---

# Tecnologias

* Java 21
* Spring Boot 4.1.1
* Spring Data JPA
* Hibernate
* PostgreSQL
* Flyway
* Docker
* Maven
* OpenAPI / Swagger

---

# Autor

**Alfredo Fernando Baptista**

Backend Developer — Java & Spring Boot

Luanda, Angola

GitHub: [alfredobaptista](https://github.com/alfredobaptista)
