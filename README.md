# 📌 TasksDoErickin  

Um sistema de gerenciamento de **projetos e tarefas** no estilo *to-do list*, desenvolvido como projeto de estudos para praticar **Java com Spring Boot**, **Maven**, **PostgreSQL** e integração com um front simples em **HTML/CSS/JavaScript**.  

O foco do projeto foi no **back-end**, com toda a lógica de CRUD e persistência de dados. O front foi feito de forma básica apenas para fornecer uma interface de interação.  

---

## 🚀 Tecnologias Utilizadas  

### Back-end  
- ☕ **Java 21**  
- 🍃 **Spring Boot 3.5.4**  
  - Spring Web  
  - Spring Data JPA  
- 🐘 **PostgreSQL**  
- 🛠️ **Maven**  

### Front-end  
- 🌐 **HTML5**  
- 🎨 **CSS3**  
- ⚡ **JavaScript**  

---

## 📂 Estrutura do Projeto  
```bash
TasksDoErickin/
│
├── backend/ (Java + Spring Boot)
│ ├── model/ (Entidades: Usuario, Projeto, Tarefa, StatusEnum)
│ ├── repository/ (Camada de acesso ao banco)
│ ├── service/ (Regras de negócio)
│ ├── controller/ (Endpoints REST)
│ ├── exceptions/ (Tratamento de erros customizados)
│ └── config/ (Configurações de CORS)
│
├── frontend/ (HTML, CSS, JS)
│ ├── login.html
│ ├── css/style.css
│ └── js/script.js
│
└── pom.xml (Dependências do Maven)
```


---

## ⚙️ Funcionalidades  

✅ **Usuários**  
- Cadastro  
- Listagem  
- Edição  
- Exclusão  

✅ **Projetos**  
- Criar projeto (com criador associado)  
- Listar todos  
- Buscar por ID  
- Editar projeto  
- Excluir projeto  

✅ **Tarefas**  
- Criar tarefa vinculada a um projeto  
- Listar tarefas de um projeto  
- Buscar por ID  
- Editar tarefa (nome, descrição, status, responsável, projeto)  
- Excluir tarefa  

⚡ Extras:  
- Autocomplete de usuários e projetos no front  
- Integração via fetch API (JavaScript)  
- Configuração de **CORS** para permitir requisições do front  

---

## 🔗 Endpoints da API  

### Usuários (`/usuarios`)  
- `POST /usuarios` → Criar usuário  
- `GET /usuarios` → Listar todos  
- `PUT /usuarios/{id}` → Editar usuário  
- `DELETE /usuarios/{id}` → Deletar usuário  

### Projetos (`/projetos`)  
- `POST /projetos` → Criar projeto  
- `GET /projetos` → Listar todos  
- `GET /projetos/{id}` → Buscar por ID  
- `PUT /projetos/{id}` → Editar projeto  
- `DELETE /projetos/{id}` → Deletar projeto  

### Tarefas (`/tarefas`)  
- `POST /tarefas` → Criar tarefa  
- `GET /tarefas/projeto/{idProjeto}` → Listar tarefas de um projeto  
- `GET /tarefas/{idTarefa}` → Buscar por ID  
- `PUT /tarefas/{idTarefa}` → Editar tarefa  
- `DELETE /tarefas/{idTarefa}` → Deletar tarefa  

---

## 🛠️ Como Rodar Localmente  

### 1. Clonar o repositório  
```bash
git clone https://github.com/erickin/TasksDoErickin.git
cd TasksDoErickin
```
### 2. Configurar o Banco de Dados
Crie um banco no PostgreSQL:
```bash
CREATE DATABASE tasksdoerickin;
```

Configure as credenciais no application.properties (ou application.yml):
```bash
spring.datasource.url=jdbc:postgresql://localhost/tasksdoerickin
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### 3. Rodar o Back-end
```bash
cd backend
mvn spring-boot:run
```

A API estará disponível em:
👉 `http://localhost:8080`

### 4. Rodar o Front-end

Abra o `login.html` no navegador (ou use extensão tipo Live Server no VSCode).
O front consumirá a API rodando localmente.

---
### 🎯 Objetivo

Esse projeto foi desenvolvido com foco em aprendizado, para praticar:

- Modelagem de entidades e relacionamentos no JPA

- Implementação de CRUD completo com Spring Boot

- Integração back-end e front-end via API REST

- Persistência de dados no PostgreSQL

- Organização de camadas (Controller → Service → Repository → Model)

