package ragatanga.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FakePaymentGatewayTest {

    @Test
    void deveRetornarAprovadoQuandoApproveForTrue() {
        FakePaymentGateway gateway = new FakePaymentGateway(true, 0);
        PaymentRequest request = new PaymentRequest("CART123", 100.0);

        PaymentResult result = gateway.process(request);

        assertTrue(result.approved);
        assertEquals("APPROVED", result.code);
    }

    @Test
    void deveRetornarReprovadoQuandoApproveForFalse() {
        FakePaymentGateway gateway = new FakePaymentGateway(false, 0);
        PaymentRequest request = new PaymentRequest("CART123", 100.0);

        PaymentResult result = gateway.process(request);

        assertFalse(result.approved);
        assertEquals("DECLINED", result.code);
    }

    @Test
    void deveAguardarODelayInformado() {
        long delay = 200;
        FakePaymentGateway gateway = new FakePaymentGateway(true, delay);
        PaymentRequest request = new PaymentRequest("CART123", 100.0);

        long start = System.currentTimeMillis();
        gateway.process(request);
        long duration = System.currentTimeMillis() - start;

        assertTrue(duration >= delay, "O process deve esperar ao menos " + delay + "ms");
    }
}
