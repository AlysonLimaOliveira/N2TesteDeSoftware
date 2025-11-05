# 🛒 Catálogo & Carrinho — Teste de Software (N2)

Projeto desenvolvido para a **N2 de Teste de Software (CSC)**, aplicando práticas de **TDD**, **dobles (mocks/stubs)**, **testes de integração simulada** e **CI com cobertura JaCoCo**.

---

## 🎯 Objetivos

- Aplicar ciclo de vida de testes (JUnit 5).
- Implementar TDD em funcionalidades de negócio.
- Criar testes parametrizados e de exceções.
- Utilizar **stubs e mocks** para dependências externas (frete, pagamento).
- Medir **cobertura de código** com JaCoCo.
- Configurar **CI no GitHub Actions** com relatório automático.

---

## 🧠 Cenário escolhido — Catálogo & Carrinho

**Entidades principais**
- `Produto`
- `Carrinho`
- `Estoque`

**Regras de negócio**
- Reserva de estoque ao adicionar itens.
- Cálculo de total com cupom e promoção progressiva.
- Cálculo de frete via mock (`FreteService`).
- Simulação de pagamento (`FakePaymentGateway`).

**Dependências dobradas**
- `EstoqueRepository` — *Stub* em memória.
- `FreteService` — *Mock* controlado.
- `PaymentGateway` — *Fake* simulando aprovação ou negação.

---

## ⚙️ Estrutura

```
catalogo-carrinho/
 ├── pom.xml
 ├── src/
 │   ├── main/java/ragatanga/...
 │   └── test/java/ragatanga/unit/...
 ├── .github/workflows/ci.yml
 └── README.md
```

---

## 🧪 Testes Implementados

| Tipo de teste | Descrição | Exemplo |
|----------------|------------|----------|
| Unitário | Testa regras isoladas do carrinho e produto | `CarrinhoServiceTest` |
| Exceções | Valida casos inválidos (ex: produto inexistente) | `assertThrows` |
| Parametrizado | Testa variações de descontos e cupons | `@ParameterizedTest` |
| Integração simulada | Fluxo ponta-a-ponta com mocks/stubs | `CarrinhoServiceIntegrationTest` |
| Performance | Mede tempo de operação crítica | `assertTimeout(Duration.ofMillis(200))` |

---

## 📈 Cobertura de Código

Relatório JaCoCo gerado automaticamente:
```
target/site/jacoco/index.html
```

Meta:  
- ≥ 80% **linhas cobertas**  
- ≥ 70% **branches cobertos**

---

## 🚀 CI — Integração Contínua

O pipeline do GitHub Actions executa automaticamente:
1. Build com Maven
2. Testes JUnit 5
3. Geração e publicação do relatório de cobertura JaCoCo

Arquivo: `.github/workflows/ci.yml`

---

## 🧩 Limites conhecidos

- Não há persistência real (uso apenas de repositórios em memória).
- Mock de API de frete não realiza chamadas HTTP reais.
- O cálculo de frete é fixo e apenas simulado.

---

## 🧱 Execução local

```bash
cd catalogo-carrinho
mvn clean test
```

Para abrir o relatório JaCoCo:
```bash
start target/site/jacoco/index.html
```

---

## 🧭 Decisões de Design

- A camada de serviço (`CarrinhoService`) centraliza as regras de negócio.
- Cada dependência externa (frete, pagamento, estoque) é injetada e dobrada com mocks/stubs.
- Os testes seguem o padrão **AAA (Arrange–Act–Assert)**.
- Os métodos seguem nomeação descritiva (`deveCalcularTotalComCupomValido` etc.).

---

## 👨‍💻 Autores

- Alyson Lima Oliveira — N2 Teste de Software (CSC)

---

### ✅ Entrega completa:
- [x] Ciclo de vida de testes  
- [x] TDD  
- [x] Exceções e parametrizados  
- [x] Stubs e mocks  
- [x] Integração simulada  
- [x] Performance  
- [x] Cobertura JaCoCo  
- [x] CI GitHub Actions  
- [x] README documentado  
