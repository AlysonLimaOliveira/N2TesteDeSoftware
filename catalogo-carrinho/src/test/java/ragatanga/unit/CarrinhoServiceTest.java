package ragatanga.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ragatanga.external.FreteAPI;
import ragatanga.model.Carrinho;
import ragatanga.model.Produto;
import ragatanga.repository.EstoqueRepository;
import ragatanga.service.CarrinhoService;
import ragatanga.service.EmailService;
import ragatanga.service.PaymentGateway;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CarrinhoServiceTest {

    private CarrinhoService service;
    private FreteAPI freteApi;
    private EstoqueRepository estoque;
    private PaymentGateway paymentGateway;
    private EmailService emailService;

    @BeforeEach
    void setup() {
        freteApi = mock(FreteAPI.class);
        estoque = mock(EstoqueRepository.class);
        paymentGateway = mock(PaymentGateway.class);
        emailService = mock(EmailService.class);

        service = new CarrinhoService(estoque, freteApi, paymentGateway, emailService);
    }

    @Test
    void deveCalcularTotalSemCupom() {
        Produto p1 = new Produto("SKU1", "Produto 1", new BigDecimal("100.00"));
        service.adicionarProdutoCatalog(p1);

        Carrinho carrinho = new Carrinho();
        carrinho.adicionar("SKU1", 2);

        when(freteApi.calcularFrete("89254-000", 1.0)).thenReturn(new BigDecimal("20.00"));

        BigDecimal total = service.calcularTotal(carrinho, Optional.empty(), "89254-000");

        // 2x100 = 200
        // desconto progressivo: 5% (10)
        // subtotal = 190
        // frete = 20
        assertEquals(new BigDecimal("210.00"), total);
    }

    @Test
    void deveAplicarCupom10PorCento() {
        Produto p1 = new Produto("SKU1", "Produto 1", new BigDecimal("50.00"));
        service.adicionarProdutoCatalog(p1);

        Carrinho carrinho = new Carrinho();
        carrinho.adicionar("SKU1", 1);

        when(freteApi.calcularFrete("89254-000", 0.5)).thenReturn(new BigDecimal("15.00"));

        BigDecimal total = service.calcularTotal(carrinho, Optional.of("CUPOM10"), "89254-000");

        // 1x50 = 50
        // sem desconto progressivo
        // cupom 10% = 45
        // frete = 15
        assertEquals(new BigDecimal("60.00"), total);
    }
}