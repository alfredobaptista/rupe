# RUPE API

API REST para **geração, consulta e processamento de pagamentos de RUPE**, desenvolvida com **Java 21 e Spring Boot**.

A aplicação pode ser executada localmente ou consumida através da instância publicada na cloud.

---

## 🚀 API em produção

A API está disponível em:

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

A API disponibiliza:

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

Os requests podem ser executados através de um ficheiro `.http`, utilizando o **REST Client do VS Code** ou um IDE compatível com HTTP Client.

Uma organização possível:

```text
requests/
└── rupe-api.http
```

## Configuração do ambiente

### Cloud

```http
@baseUrl = https://rupe-api.onrender.com
```

### Local

```http
@baseUrl = http://localhost:8080
```

Desta forma, os mesmos requests podem ser utilizados nos dois ambientes, alterando apenas `@baseUrl`.

---

# 1. Listar serviços

Lista os serviços disponíveis para geração de RUPE.

```http
GET {{baseUrl}}/api/v1/rupes/servicos
Accept: application/json
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

O `codigo` do serviço é utilizado na geração da RUPE.

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

### Exemplo de resposta

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
  "estado": "ABERTO",
  "numeroRecibo": null,
  "dataPagamento": null
}
```

Uma RUPE recém-gerada inicia no estado:

```text
ABERTO
```

A `referencia` retornada pode posteriormente ser utilizada para consulta ou processamento do pagamento.

---

# 3. Consultar RUPE

Consulta uma RUPE através da sua referência.

```http
GET {{baseUrl}}/api/v1/rupes/00010100000000000016
Accept: application/json
```

### Exemplo de resposta

```json
{
  "referencia": "00010100000000000016",
  "nomeContribuinte": "Contribuinte Teste",
  "codigoServico": "IAPI-MAR-001",
  "descricaoServico": "Registo de Marca",
  "valor": 11176.00,
  "dataEmissao": "2026-10-02T16:10:13.555954",
  "dataExpiracao": "2026-11-01T23:59:59",
  "estado": "ABERTO",
  "numeroRecibo": null,
  "dataPagamento": null
}
```

---

# 4. Processar pagamento

Processa o pagamento de uma RUPE.

O endpoint exige o header:

```text
Idempotency-Key
```

Request:

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

### Exemplo de resposta

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

Depois do processamento, a mesma referência pode ser consultada novamente:

```http
GET {{baseUrl}}/api/v1/rupes/00010100000000000016
Accept: application/json
```

A RUPE deverá apresentar o estado:

```json
{
  "referencia": "00010100000000000016",
  "estado": "PAGO",
  "numeroRecibo": "REC-2026-000001",
  "dataPagamento": "2026-10-02T16:15:00"
}
```

---

# 6. Idempotência no pagamento

O processamento utiliza uma `Idempotency-Key` para identificar uma operação lógica de pagamento.

Para reenviar a **mesma operação**, deve ser utilizada a mesma chave:

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

Para uma nova operação de pagamento, deve ser utilizada uma nova `Idempotency-Key`.

---

# Fluxo da API

```text
GET /api/v1/rupes/servicos
            │
            ▼
       Código do serviço
            │
            ▼
POST /api/v1/rupes
            │
            ▼
       RUPE ABERTO
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
* PostgreSQL
* Docker
* Git

O projecto utiliza Maven Wrapper.

## Clonar

```bash
git clone git@github.com:alfredobaptista/rupe.git
cd rupe
```

## Configurar PostgreSQL

A aplicação necessita de uma instância PostgreSQL configurada de acordo com as propriedades do ambiente.

As migrações do schema são geridas automaticamente pelo **Flyway**.

Depois de configurar a base de dados:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

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

> `-DskipTests` impede a execução dos testes, mas o Maven continua a compilar o código de testes. Para ignorar também a compilação dos testes, utilize `-Dmaven.test.skip=true`.

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

A aplicação ficará disponível em:

```text
http://localhost:8080
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

A instância publicada pode ser consumida directamente sem instalar o projecto.

### Consultar serviços

```http
GET https://rupe-api.onrender.com/api/v1/rupes/servicos
Accept: application/json
```

### Gerar RUPE

```http
POST https://rupe-api.onrender.com/api/v1/rupes
Content-Type: application/json
Accept: application/json

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
Accept: application/json
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

GitHub: https://github.com/alfredobaptista
