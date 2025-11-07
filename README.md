# Sistema de Locadora em Java (POO)

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17%2B-007396?style=for-the-badge&logo=java">
  <img alt="Gradle" src="https://img.shields.io/badge/Gradle-7.6%2B-02303A?style=for-the-badge&logo=gradle">
  <img alt="OOP" src="https://img.shields.io/badge/Design-OOP-blue?style=for-the-badge&logo=databricks">
  <img alt="Status" src="https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge">
</p>

Uma aplicação de console (CLI) robusta em Java para o gerenciamento de uma locadora de jogos e consoles. O projeto é focado em demonstrar um design de software sólido e a aplicação de conceitos de **Programação Orientada a Objetos (POO)**.

O sistema gerencia duas lógicas de negócio paralelas e distintas:
1.  **Locação de Mídia:** Aluguel de jogos por dia.
2.  **Aluguel de Equipamentos:** Aluguel de consoles e acessórios por hora.

---

## Desafios e Soluções de Design

Este projeto foi um mergulho nos padrões de design de POO. Os principais desafios de arquitetura foram:

1.  **Modelagem de Dados (N-M):**
    * **Problema:** Um `Jogo` pode existir em múltiplas `Plataformas`, e vice-versa. Além disso, a `LocacaoJogo` (recibo) precisa conter múltiplos `JogoPlataforma`.
    * **Solução:** Implementação de **Classes de Associação** (`JogoPlataforma` e `ItemLocacao`) para quebrar as relações N-M em duas 1-N. Isso permitiu que atributos da *própria relação* (como `precoDiario` ou `quantidadeDias`) tivessem um lugar correto para existir.

2.  **Integridade Referencial (Ciclo de Vida):**
    * **Problema:** O que acontece se um Admin tentar deletar um `Cliente` que possui um histórico de aluguéis? Ou deletar um `Jogo` que ainda está no estoque (`JogoPlataforma`)?
    * **Solução:** Implementação da **Composição** (losango cheio no UML). O sistema ativamente impede a remoção de entidades "pai" (como `Cliente`, `Jogo`, `Plataforma`) se elas ainda possuírem entidades "filho" ativas. Isso garante a integridade dos dados e evita registros órfãos no histórico.

3.  **Encapsulamento e Responsabilidade:**
    * **Problema:** Onde calcular o total de um aluguel? Como garantir que o total seja sempre correto?
    * **Solução:** A responsabilidade foi distribuída. O `ItemLocacao` tem um método `public` para calcular seu subtotal. A `LocacaoJogo` (o recibo) possui um método `private` (`calcularValorTotal()`) que é chamado *internamente* sempre que um item é adicionado ou removido, garantindo que o `valorTotal` do objeto esteja sempre sincronizado e protegido de manipulação externa.

---

## ✨ Funcionalidades Principais

O sistema é dividido em dois módulos de acesso com responsabilidades claras:

### 👤 Módulo Administrador
* **Gestão de Clientes:** CRUD (Criar, Listar, Atualizar, Remover) de clientes.
* **Gestão de Inventário:**
    * CRUD de Títulos de Jogos (ex: "God of War").
    * CRUD de Plataformas (ex: "PS5").
    * CRUD de Consoles (ex: "Console PS5 Slim").
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
---

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
