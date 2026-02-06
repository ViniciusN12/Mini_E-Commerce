# E-Commerce System

Este é um sistema de e-commerce desenvolvido em Java com Spring Boot. Ele permite o cadastro, autenticação e gerenciamento de usuários, produtos e compras, com interface web e API REST.

## Funcionalidades
- Cadastro e autenticação de usuários
- Cadastro, edição e remoção de produtos
- Compra de produtos com controle de estoque
- Interface web para administração e usuários
- API REST para integração
- Controle de permissões por perfil (admin/usuário)

## Estrutura do Projeto
```
src/main/java/e_commerce/
  config/         # Configurações de segurança e globais
  controller/     # Controllers MVC e REST
  exception/      # Tratamento de exceções
  models/         # Entidades, DTOs e enums
  repository/     # Repositórios JPA
  service/        # Lógica de negócio
src/main/resources/
  templates/      # Templates Thymeleaf
  static/         # Arquivos estáticos (CSS, JS, imagens)
  application.properties # Configurações da aplicação
```

## Como rodar o projeto
1. Clone o repositório:
   ```sh
   git clone https://github.com/SEU_USUARIO/e-commerce.git
   ```
2. Configure o banco de dados em `src/main/resources/application.properties`.
3. Compile e rode o projeto:
   ```sh
   ./mvnw spring-boot:run
   ```
4. Acesse `http://localhost:8080` no navegador.

## Requisitos
- Java 17+
- Maven 3.8+
- Banco de dados relacional (ex: MySQL, PostgreSQL)

## Observações
- Não suba arquivos de configuração sensíveis (`application.properties`).
- O sistema já possui `.gitignore` configurado.

## Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
