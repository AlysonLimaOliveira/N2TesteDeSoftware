package ragatanga.service;

import org.junit.jupiter.api.Test;
import ragatanga.external.FreteAPI;
import ragatanga.model.Carrinho;
import ragatanga.model.Produto;
import ragatanga.repository.EstoqueRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoServiceTest {

    @Test
    void deveCalcularTotalComPromocaoCupomEFrete() {

        // Stub de estoque
        EstoqueRepository estoqueStub = new EstoqueRepository() {
            @Override
            public Optional<Integer> consultarQuantidade(String sku) {
                return Optional.of(10); // sempre tem estoque
            }

            @Override
            public void reservar(String sku, int qtd) {
                // não faz nada, sempre "funciona"
            }
        };

        // Mock simples de frete
        FreteAPI freteMock = new FreteAPI() {
            @Override
            public BigDecimal calcularFrete(String cep, double peso) {
                return BigDecimal.valueOf(20);
            }
        };

        // Stub correto de PaymentGateway
        PaymentGateway paymentStub = request -> new PaymentResult(true, "OK");

        // Stub de Email
        EmailService emailStub = (to, subject, body) -> {
        };

        CarrinhoService service = new CarrinhoService(
                estoqueStub, freteMock, paymentStub, emailStub);

        // Produto no catálogo
        Produto p = new Produto("SKU1", "Produto Teste", BigDecimal.valueOf(100));
        service.adicionarProdutoCatalog(p);

        // Carrinho com 2 itens
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar("SKU1", 2);

        // cupom CUPOM10
        BigDecimal total = service.calcularTotal(carrinho, Optional.of("CUPOM10"), "89254000");

        // Cálculo esperado:
        // 100 * 2 = 200
        // promoção 5% = -10 -> 190
        // cupom 10% = -19 -> 171
        // frete = +20 -> 191
        assertEquals(new BigDecimal("191.00"), total);
    }
}
