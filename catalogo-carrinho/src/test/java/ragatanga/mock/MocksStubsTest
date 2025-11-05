package ragatanga.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ragatanga.service.EmailService;
import ragatanga.service.PaymentGateway;
import ragatanga.model.Carrinho;

import static org.mockito.Mockito.*;

class MocksStubsTest {

    @Test
    void testPagamentoComMock() {
        // Mock do gateway de pagamento
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        when(paymentGateway.processPayment(anyDouble())).thenReturn(true);

        // Mock do serviço de email
        EmailService emailService = mock(EmailService.class);
        doNothing().when(emailService).sendConfirmationEmail(anyString());

        // Criando carrinho e adicionando itens
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar("SKU1", 2);

        // Verificando se os métodos foram chamados
        verify(paymentGateway, times(1)).processPayment(anyDouble());
        verify(emailService, times(1)).sendConfirmationEmail(anyString());
    }

    @Test
    void testPagamentoFalha() {
        // Stub do gateway de pagamento que sempre falha
        PaymentGateway paymentGateway = mock(PaymentGateway.class);
        when(paymentGateway.processPayment(anyDouble())).thenReturn(false);

        // Mock do serviço de email
        EmailService emailService = mock(EmailService.class);

        // Verificando que o email não foi enviado em caso de falha
        verify(emailService, never()).sendConfirmationEmail(anyString());
    }
}