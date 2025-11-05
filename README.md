# N2TesteDeSoftware

Trabalho N2 da disciplina de Testes de Software.

## Descrição

Este projeto implementa um sistema de carrinho de compras em Java, com foco em boas práticas de desenvolvimento e testes automatizados. O objetivo é mostrar a estruturação de um sistema real utilizando TDD, testes unitários, testes de integração, uso de mocks/stubs, cobertura e exemplos do ciclo de vida de testes.

## Estrutura do Sistema

- **Carrinho:** Permite adicionar produtos, calcular totais, aplicar descontos e cupons.
- **Produto:** Representa os itens do catálogo (SKU, nome, preço).
- **Estoque:** Simula o controle de estoque, reservando e consultando quantidade.
- **Serviços externos:** Integração simulada (email, pagamento, frete).

## Estrutura dos Testes

Os testes estão organizados em `src/test/java/ragatanga/unit` e `src/test/java/ragatanga/integration`:

- **Ciclo de vida:** Demonstra uso de `@BeforeEach`, `@AfterEach`, etc.
- **TDD:** Exemplos de criação de testes antes da implementação.
- **Testes de exceção:** Garante que erros são tratados corretamente.
- **Testes parametrizados:** Verifica comportamento para múltiplas entradas.
- **Stubs/mocks:** Simula dependências mediante Mockito ou fakes.
- **Performance:** Verifica tempo máximo de execução.
- **Cobertura:** Gera relatório de cobertura via JaCoCo.

## Como rodar os testes

1. Instale o Maven (`mvn`).
2. Execute os testes:
   ```sh
   mvn clean test
   ```
3. (Opcional) Gere a cobertura:
   ```sh
   mvn test
   ```
   Abra em: `target/site/jacoco/index.html`

## Exemplos de comandos úteis

- Rodar testes de unidade:
  ```sh
  mvn test -Dtest="ragatanga.unit.*"
  ```
- Rodar todos os testes:
  ```sh
  mvn test
  ```
- Gerar relatório de cobertura:
  ```sh
  mvn jacoco:report
  ```

## Referências

- [JUnit 5](https://junit.org/junit5/)
- [JaCoCo](https://www.jacoco.org/jacoco/)

---

Projeto acadêmico para demonstração de práticas de teste de software.