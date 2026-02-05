# 🎭 Backstage Eventos

> Sistema completo de gerenciamento de eventos com checklist de tarefas para produtores organizarem suas produções

---

## 🤔 O que é esse projeto?

Sabe quando você está organizando um evento e tem aquela lista gigante de coisas pra fazer? Contratar som, montar palco, ajustar iluminação, resolver logística... E tudo fica espalhado em papéis, planilhas e post-its? Pois então, esse sistema foi feito pra acabar com isso.

O **Backstage Eventos** permite que produtores de eventos:

- Criem e gerenciem seus eventos
- Organizem tarefas em checklists
- Filtrem tarefas por status (pendente/concluído) e categoria
- Definam prioridades e prazos
- Acompanhem o progresso em tempo real

---

## 🎯 Funcionalidades Principais

✅ **Criar conta e fazer login**  
Cada produtor tem sua conta própria e gerencia apenas seus eventos.

✅ **Cadastrar eventos**  
Crie quantos eventos quiser: shows, festivais, casamentos, o que for!

✅ **Criar tarefas**  
Para cada evento, adicione uma lista de tarefas com:

- Título e descrição
- Categoria (Som, Iluminação, Palco, Logística, Geral)
- Prioridade (Alta, Média, Baixa)
- Prazo de conclusão

✅ **Filtrar tarefas** ⭐ _Funcionalidade destacada!_  
Essa foi uma funcionalidade importante implementada:

- Filtrar por status: ver só as pendentes ou só as concluídas
- Filtrar por categoria: ver só tarefas de som, só de iluminação, etc.
- Filtros combinados: ex: "tarefas pendentes de palco"

✅ **Marcar tarefas como concluídas**  
Risque da lista quando terminar!

✅ **Dashboard visual**  
Veja todos seus eventos num painel organizado

---

## 🏗️ Arquitetura e Stack

O projeto é dividido em duas partes:

### 🔙 Backend (API REST)

**Stack:**

- **Java 21** - Linguagem
- **Spring Boot 3.5** - Framework principal
- **Spring Data JPA** - Acesso ao banco de dados
- **PostgreSQL** - Banco de dados
- **Maven** - Gerenciamento de dependências
- **Lombok** - Redução de boilerplate
- **Bean Validation** - Validações
- **Springdoc OpenAPI** - Documentação automática (Swagger)

**Arquitetura:**

```
Controller → Service → Repository → Database
```

Padrão MVC/REST bem estruturado, com camadas separadas e responsabilidades bem definidas.

**Principais Endpoints:**

- `/api/usuarios` - Cadastro e login
- `/api/eventos` - CRUD de eventos
- `/api/itens` - CRUD de tarefas com filtros

### 🎨 Frontend (SPA)

**Stack:**

- **Angular 19** - Framework principal
- **TypeScript** - Linguagem
- **Bootstrap 5** - CSS/Componentes
- **RxJS** - Programação reativa
- **Angular Forms** - Formulários

**Páginas:**

- Login
- Cadastro
- Dashboard (lista de eventos)
- Checklist (tarefas do evento)

---

## 🗄️ Banco de Dados

### Estrutura:

**3 tabelas principais:**

1. **usuarios** - Produtores/organizadores
2. **eventos** - Eventos criados pelos usuários
3. **itens_checklist** - Tarefas de cada evento

**Relacionamentos:**

```
usuarios (1) → (N) eventos
eventos (1) → (N) itens_checklist
```

Um usuário pode ter vários eventos.  
Um evento pode ter várias tarefas.  
Se você deletar um evento, todas as tarefas são deletadas junto (CASCADE).

---

## 🚀 Como Rodar o Projeto

### 🐳: Com Docker

#### Pré-requisitos:

- **Docker** e **Docker Compose** instalados

#### Como rodar:

```bash
# 1. Clone o repositório (se ainda não tem)
git clone <url-do-repositorio>
cd zetta-backstage-eventos

# 2. Subir tudo com um comando
docker-compose up -d

# Pronto! 🎉
# Backend: http://localhost:8080
# Frontend: http://localhost:4200
# Banco: PostgreSQL rodando no container
```

**Pronto para usar!** Acesse `http://localhost:4200` no navegador e comece a usar! 🚀

---

### 🎉 Como Usar o Sistema

1. Abra o navegador em `http://localhost:4200`
2. Crie uma conta (página de cadastro)
3. Faça login
4. Crie seu primeiro evento
5. Adicione tarefas no checklist
6. Use os filtros para organizar!

---

## 📡 API REST - Endpoints

Se você quiser testar a API direto (Postman, Insomnia, cURL):

**Nota:** Os exemplos abaixo utilizam a sintaxe padrão **Bash** (Linux/macOS/Git Bash).
Caso esteja executando via **Prompt de Comando (CMD)** ou **PowerShell** no Windows, atente-se à necessidade de escapar aspas duplas no JSON (ex: `\"chave\": \"valor\"`) ou utilize uma ferramenta gráfica como Postman.

### Usuários

```bash
# Cadastrar
POST http://localhost:8080/api/usuarios
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123"
}

# Login
POST http://localhost:8080/api/usuarios/login
{
  "email": "joao@email.com",
  "senha": "senha123"
}
```

### Eventos

```bash
# Criar evento
POST http://localhost:8080/api/eventos?usuarioId=1
{
  "nome": "Festival de Música 2026",
  "dataEvento": "2026-07-15",
  "localizacao": "Estádio Municipal"
}

# Listar eventos do usuário
GET http://localhost:8080/api/eventos/produtor/1

# Deletar evento
DELETE http://localhost:8080/api/eventos/1
```

### Tarefas (Itens do Checklist)

```bash
# Criar tarefa
POST http://localhost:8080/api/itens?eventoId=1
{
  "nome": "Contratar equipe de som",
  "descricao": "Empresa especializada",
  "prioridade": "ALTA",
  "categoria": "SOM",
  "dataLimite": "2026-06-30T18:00:00"
}

# Listar todas as tarefas de um evento
GET http://localhost:8080/api/itens/evento/1

# Filtrar por status
GET http://localhost:8080/api/itens/evento/1?status=PENDENTE
GET http://localhost:8080/api/itens/evento/1?status=CONCLUIDO

# Filtrar por categoria
GET http://localhost:8080/api/itens/evento/1?categoria=SOM
GET http://localhost:8080/api/itens/evento/1?categoria=PALCO

# Filtros combinados (status + categoria)
GET http://localhost:8080/api/itens/evento/1?status=PENDENTE&categoria=SOM

# Atualizar status
PATCH http://localhost:8080/api/itens/1/status?status=CONCLUIDO

# Deletar tarefa
DELETE http://localhost:8080/api/itens/1
```

## 🔍 Swagger/OpenAPI

A API tem documentação interativa automática!

Quando o backend estiver rodando, acesse:

```
http://localhost:8080/swagger-ui.html
```

Lá você pode:

- Ver todos os endpoints
- Testar a API direto no navegador
- Ver os schemas de request/response

---

### Validações Implementadas:

**Backend:**

- Email único (não pode cadastrar mesmo email 2x)
- Senha mínima de 6 caracteres
- Email válido (formato)
- Data do evento deve ser presente ou futura
- Campos obrigatórios validados

**Frontend:**

- Formulários com validação
- Mensagens de erro amigáveis
- Confirmação antes de deletar
- Feedback visual (loading, success, error)

---

## 📁 Estrutura do Projeto

```
zetta-backstage-eventos/
├── backstage-api/          # Backend Spring Boot
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── br.ufla.zetta.backstage_api/
│   │   │   │       ├── BackstageApiApplication.java  # Main
│   │   │   │       ├── config/      # Configurações (CORS, Swagger)
│   │   │   │       ├── controller/  # REST Controllers
│   │   │   │       ├── service/     # Lógica de negócio
│   │   │   │       ├── repository/  # Acesso ao banco (JPA)
│   │   │   │       ├── model/       # Entidades (Usuario, Evento, etc)
│   │   │   │       └── exception/   # Tratamento de erros
│   │   │   └── resources/
│   │   │       └── application.properties  # Configurações
│   │   └── test/
│   └── pom.xml             # Dependências Maven
│
├── backstage-web/          # Frontend Angular
│   ├── src/
│   │   ├── app/
│   │   │   ├── pages/      # Componentes de páginas
│   │   │   │   ├── login/
│   │   │   │   ├── cadastro/
│   │   │   │   ├── dashboard/
│   │   │   │   └── checklist/
│   │   │   └── services/   # Serviços (API calls)
│   │   ├── index.html
│   │   └── styles.css
│   ├── angular.json
│   └── package.json        # Dependências npm
│
└── README.md
```

---

## 🎉 Conclusão

E é isso! Você tem um sistema completo de gerenciamento de eventos, de ponta a ponta:

- ✅ Backend REST robusto em Spring Boot
- ✅ Frontend moderno em Angular
- ✅ Banco de dados relacional bem estruturado
- ✅ Sistema de filtros inteligente
- ✅ Interface bonita e responsiva
- ✅ Fácil de rodar e testar

Espero que você goste do projeto! Se tiver qualquer dúvida, é só perguntar. 😊

---
