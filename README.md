# Sistema de Locadora em Java (POO)

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17%2B-F89820?style=for-the-badge">
  <img alt="Gradle" src="https://img.shields.io/badge/Gradle-7.6%2B-02303A?style=for-the-badge&logo=gradle">
  <img alt="POO" src="https://img.shields.io/badge/Design-POO-blue?style=for-the-badge">
</p>

Uma aplicação de console (CLI) robusta em Java para o gerenciamento de uma locadora de jogos e consoles. O projeto é focado em demonstrar um design de software sólido e a aplicação de conceitos de **Programação Orientada a Objetos (POO)**.

O sistema gerencia duas lógicas de negócio paralelas e distintas:
1.  **Locação de Mídia:** Aluguel de jogos por dia.
2.  **Aluguel de Equipamentos:** Aluguel de consoles e acessórios por hora.

---

## ✨ Funcionalidades Principais

O sistema é dividido em dois módulos de acesso com responsabilidades claras:

### 👤 Módulo Administrador
* **Gestão de Clientes:** CRUD (Criar, Listar, Atualizar, Remover) de clientes.
* **Gestão de Inventário:**
    * CRUD de Títulos de Jogos (ex: "God of War").
    * CRUD de Plataformas (ex: "PS5").
    * CRUD de Consoles (ex: "Console PS5").
    * CRUD de Acessórios (ex: "Controle DualSense").
* **Estoque e Preços:**
    * Associação de jogos a plataformas (`JogoPlataforma`), definindo preço diário e estoque.
    * Associação de acessórios a plataformas (compatibilidade N-M).
* **Consulta Geral:** Acesso ao histórico completo de todas as transações do sistema.

### 🎮 Módulo Cliente
* **Autenticação:** Login seguro por ID e senha.
* **Autoatendimento:**
    * Realizar uma nova **Locação de Jogo**, selecionando itens e dias.
    * Realizar um novo **Aluguel de Console**, selecionando o console, horas e acessórios compatíveis.
* **Transparência:**
    * Recebimento de um resumo para confirmação (S/N) antes de finalizar o pedido.
    * Consulta ao seu próprio histórico detalhado de locações e aluguéis.
    * Atualização do próprio cadastro (nome, e-mail, telefone, senha).
* **Comprovante:** Geração de um comprovante formatado ao final de cada transação.

---

## 🏗️ Diagrama de Classes (UML - Implementação)

Abaixo está o diagrama de classes do projeto, focado na implementação e refletindo as relações de Composição e Agregação.

![Diagrama de Classes UML](diagrama/diagramaAtualizado.svg)

<a href="https://github.com/pedrovalete/sistema-locadora-poo/blob/main/diagrama/diagramaAtualizado.pdf?download=true" download>
  <strong>Diagrama em PDF</strong>
</a>


## 🛠️ Tecnologias Utilizadas

* **Java (JDK 17+)**
* **Gradle:** Ferramenta de automação de build e gerenciamento de dependências.
* **Programação Orientada a Objetos (POO):** Encapsulamento, Abstração.
* **Estruturas de Dados:** `HashMap` para acesso rápido (O(1)) e gerenciamento de dados; `ArrayList` para listas ordenadas.
* **API `java.time`:** Uso de `LocalDate`, `LocalDateTime` e `DateTimeFormatter` para gerenciamento de datas e horas de forma imutável.
* **Git & GitHub:** Controle de versão e hospedagem de portfólio.

---

## 🏁 Como Executar o Projeto

1.  **Pré-requisitos:**
    * Java JDK 17 (ou superior) instalado.
    * Git instalado.

2.  **Clone o Repositório:**
    ```sh
    git clone https://github.com/pedrovalete/sistema-locadora-poo.git
    cd java-rental-store
    ```

3.  **Execute pela IDE (Recomendado):**
    * Abra o projeto na sua IDE favorita (IntelliJ IDEA, Eclipse, VS Code com Java Extension Pack).
    * Localize o arquivo principal `App.java`.
    * Execute o método `main()`.

4.  **Navegue pela Aplicação:**
    * Siga as instruções numéricas no console para acessar os menus de Administrador ou Cliente.

---
## 📄 Contexto do Projeto

Este projeto foi desenvolvido para a disciplina de Programação Orientada a Objetos do curso de **Análise e Desenvolvimento de Sistemas** no **IFSC (Instituto Federal de Santa Catarina)**.
