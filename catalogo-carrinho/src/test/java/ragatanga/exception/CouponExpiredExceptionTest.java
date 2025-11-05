package ragatanga.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CouponExpiredExceptionTest {

    @Test
    void deveCriarExceptionComMensagem() {
        String mensagem = "Cupom expirado";
        CouponExpiredException exception = new CouponExpiredException(mensagem);

        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    void deveLancarCouponExpiredException() {
        assertThrows(CouponExpiredException.class, () -> {
            throw new CouponExpiredException("Cupom expirado");
        });
    }
}
