# 📊 Projeto DAO JDBC

Este projeto foi desenvolvido com o objetivo de **estudar persistência de dados em Java**, utilizando o padrão **DAO (Data Access Object)** em conjunto com a biblioteca **JDBC (Java Database Connectivity)** para acesso a banco de dados.

## 🛠️ Descrição do Projeto

O projeto simula uma aplicação de persistência de dados, onde um **banco de dados fictício** foi criado e populado inteiramente através do código. Foram implementados testes para realizar as operações básicas de CRUD (**Create, Read, Update, Delete**), permitindo a inserção, remoção, busca e atualização de registros no banco de dados.

## 🎯 Objetivos

- Aplicar o padrão de design **DAO**, que separa a lógica de acesso aos dados da lógica de negócios, facilitando a manutenção e evolução do sistema.
- Utilizar o **JDBC**, que é uma API nativa do Java para conectar e executar consultas SQL em um banco de dados.
- Aprender e praticar operações CRUD em um banco de dados, manipulando tabelas fictícias.

## 🗂️ Estrutura do Projeto

O projeto está organizado de maneira modular para facilitar a compreensão e manutenção do código. Abaixo está uma visão geral das principais camadas:

- **DAO Layer**: Contém as classes responsáveis por interagir diretamente com o banco de dados, realizando operações como inserção, remoção, atualização e consulta de dados.
  
- **Entities**: Representa os objetos que serão persistidos no banco de dados (as tabelas), como por exemplo, `Vendedor` e `Departamento`.
  
- **Database Setup**: Scripts e comandos SQL utilizados para criar e popular o banco de dados fictício. Toda a estrutura do banco foi gerada e manipulada diretamente no código.

## 🔧 Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **JDBC (Java Database Connectivity)**: Biblioteca de conexão ao banco de dados.
- **MySQL** (ou qualquer banco de dados relacional): O banco de dados usado para simulação.
- **Padrão DAO (Data Access Object)**: Padrão arquitetural para separação das camadas de acesso a dados.

## 📝 Funcionalidades Implementadas

As operações de banco de dados implementadas no projeto são:

1. **Inserção de Dados**: Inclusão de novos registros no banco de dados.
2. **Consulta de Dados (Read)**: Busca de registros existentes com base em critérios específicos.
3. **Atualização de Dados (Update)**: Modificação de dados já existentes no banco de dados.
4. **Remoção de Dados (Delete)**: Exclusão de registros no banco de dados.
