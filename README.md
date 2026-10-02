# RUPE API

API REST para geração, consulta e processamento de pagamentos de RUPE, desenvolvida com **Java 21 e Spring Boot**.

O projecto disponibiliza endpoints para:

* Gerar um RUPE
* Consultar um RUPE
* Processar um pagamento

## Tecnologias

* Java 21
* Spring Boot
* PostgreSQL
* Spring Data JPA
* Flyway
* OpenAPI / Swagger
* Docker
* Maven

---

## API

### Gerar RUPE

```http
POST /api/v1/rupes
```

Request:

```json
{
  "nif": "123456789",
  "nomeContribuinte": "Contribuinte API",
  "codigoServico": "SERV-001",
  "dataExpiracao": "2026-12-31T23:59:59"
}
```

---

### Consultar RUPE

```http
GET /api/v1/rupes/{referencia}
```

Exemplo:

```http
GET /api/v1/rupes/00010100000000000511
```

---

### Processar pagamento

```http
POST /api/v1/pagamentos
```

Header obrigatório:

```http
Idempotency-Key: <uuid>
```

Request:

```json
{
  "referencia": "00010100000000000511",
  "numeroRecibo": "REC-2026-000001",
  "dataPagamento": "2026-10-01T21:05:00"
}
```

A utilização de `Idempotency-Key` permite reenviar uma operação sem provocar o processamento duplicado do mesmo pagamento.

---

## Executar localmente

### Pré-requisitos

* Java 21
* Docker
* Maven

Clonar o projecto:

```bash
git clone git@github.com:alfredobaptista/rupe.git
cd rupe
```

Executar a aplicação:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

### Executar os testes

```bash
./mvnw test
```

### Gerar o build

```bash
./mvnw clean package
```

---

## Docker

Construir a imagem:

```bash
docker build -t rupe:latest .
```

Executar:

```bash
docker run --rm -p 8080:8080 rupe:latest
```

---

## Swagger / OpenAPI

Com a aplicação em execução:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

---

## Instância em produção

A API também possui uma instância publicada no **Render**:

```text
https://rupe-api.onrender.com
```

### Endpoints

```text
POST https://rupe-api.onrender.com/api/v1/rupes
GET  https://rupe-api.onrender.com/api/v1/rupes/{referencia}
POST https://rupe-api.onrender.com/api/v1/pagamentos
```

### Swagger

```text
https://rupe-api.onrender.com/swagger-ui/index.html
```
---

## Autor

**Alfredo Fernando Baptista**

Backend Developer — Java & Spring Boot

Luanda, Angola
