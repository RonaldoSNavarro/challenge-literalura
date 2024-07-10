## 🔖 Sobre

# LiterAlura

## Descrição
LiterAlura é uma aplicação em Java que consome a API Gutendex para obter informações sobre livros e autores. Os dados são persistidos em um banco de dados PostgreSQL utilizando JPA (Java Persistence API).

## Funcionalidades

### 1. Busca de Livros por Título
- Permite buscar um livro específico pelo título através da API Gutendex.
- Caso encontrado, o livro é salvo no banco de dados.

### 2. Listagem de Livros Registrados
- Lista todos os livros que estão armazenados no banco de dados.

### 3. Listagem de Autores
- Lista todos os autores que estão armazenados no banco de dados, incluindo seus anos de nascimento e morte, quando disponíveis.

### 4. Listagem de Autores por Ano
- Permite filtrar e listar os autores que estavam vivos em um ano específico.

### 5. Listagem de Livros por Idioma
- Permite filtrar e listar os livros por idioma específico (ex: inglês, espanhol, francês, português).

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.1
- Spring Data JPA
- PostgreSQL como banco de dados
- Maven para gerenciamento de dependências
- Jackson Databind para manipulação de JSON
- Gutendex API para busca de dados de livros e autores

## Como Executar

1. Certifique-se de ter o PostgreSQL instalado e configurado.
2. Configure as variáveis de ambiente no arquivo `application.properties` para conectar ao seu banco de dados PostgreSQL.
3. Execute o projeto utilizando Maven:

# Desenvolvedor

[<img loading="lazy" src="https://avatars.githubusercontent.com/u/134724019?v=4" width=115><br><sub>Ronaldo Navarro</sub>](https://github.com/ronaldosnavarro)