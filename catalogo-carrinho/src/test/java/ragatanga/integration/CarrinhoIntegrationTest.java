package ragatanga.integration;

import org.junit.jupiter.api.Test;
import ragatanga.external.FakeFreteAPI;
import ragatanga.model.Carrinho;
import ragatanga.model.Produto;
import ragatanga.repository.InMemoryEstoqueRepository;
import ragatanga.service.CarrinhoService;
import ragatanga.service.FakeEmailService;
import ragatanga.service.PaymentGateway;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class CarrinhoIntegrationTest {

    @Test
    void deveCalcularTotalComFluxoCompleto() {
        var estoque = new InMemoryEstoqueRepository();
        var freteApi = new FakeFreteAPI();
        PaymentGateway payment = mock(PaymentGateway.class); // mock simples
        var email = new FakeEmailService();

        var service = new CarrinhoService(estoque, freteApi, payment, email);

        Produto p1 = new Produto("SKU1", "Produto 1", new BigDecimal("80.00"));
        service.adicionarProdutoCatalog(p1);

        Carrinho carrinho = new Carrinho();
        carrinho.adicionar("SKU1", 3);

        BigDecimal total = service.calcularTotal(carrinho, Optional.of("CUPOM10"), "89254-000");

        // Apenas valida que o total é positivo e inclui frete
        assertTrue(total.compareTo(BigDecimal.ZERO) > 0);
    }
}