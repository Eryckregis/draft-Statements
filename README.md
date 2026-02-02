# Draft Statements API

API REST desenvolvida em Java com Spring Boot para upload e download de arquivos PDF.

Projeto **exclusivamente back-end**. Não há interface gráfica nem endpoint raiz.

---

## Descrição

A API permite:

* Upload de arquivos PDF via HTTP
* Armazenamento do arquivo no servidor
* Persistência dos metadados no banco de dados
* Download do PDF utilizando o nome original

Os arquivos são salvos no diretório `uploads` com nome gerado automaticamente para evitar conflitos.

---

## Tecnologias

* Java 17
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* H2 (desenvolvimento)
* PostgreSQL (produção)
* Maven
* Docker

---

## Estrutura do Projeto

```
com.statementapi.draft.statements
├── entities
│   └── Declaracao
├── repositories
│   └── DeclaracaoRepository
├── services
│   ├── DeclaracaoService
│   └── exceptions
│       └── DeclaracaoException
├── resources
│   ├── DeclaracaoResource
│   └── exceptions
│       └── ResourceExceptionHandler
└── DraftStatementsApplication
```

---

## Endpoints

### POST /declaracoes/upload

Realiza upload de um arquivo PDF.

* Content-Type: `multipart/form-data`
* Campo obrigatório: `file`
* Aceita somente `application/pdf`

Resposta:

```json
{
  "id": 1,
  "nomeOriginal": "arquivo.pdf",
  "nomeArmazenado": "uuid.pdf",
  "caminho": "uploads/uuid.pdf",
  "dataUpload": "2026-02-02T12:30:45"
}
```

---

### GET /declaracoes/{id}/download

Realiza o download do PDF associado ao ID informado.

* Retorna o arquivo binário
* Header `Content-Disposition` configurado para download com nome original

---

## Tratamento de Erros

* 400 – Arquivo inválido (não PDF)
* 404 – Declaração não encontrada

---

## Execução Local

Requisitos:

* Java 17+
* Maven

Comandos:

```bash
git clone https://github.com/Eryckregis/draft-Statements.git
cd draft-Statements
mvn spring-boot:run
```

Aplicação disponível em:

```
http://localhost:8080
```

---

## Docker

Build da imagem:

```bash
docker build -t draft-statements .
```

Execução:

```bash
docker run -p 8080:8080 draft-statements
```

---

## Deploy

URL:

```
https://draft-statements.onrender.com
```

O endpoint `/` não possui retorno. Utilize diretamente os endpoints documentados.

---

## Autor

Eryck Regis

---

## Notas

* Uploads são armazenados no diretório `uploads`
* API não possui autenticação
* Projeto voltado para estudo e integração com front-end
