package ragatanga.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InsufficientStockExceptionTest {

    @Test
    void deveCriarExceptionComMensagem() {
        String mensagem = "Estoque insuficiente";
        InsufficientStockException exception = new InsufficientStockException(mensagem);

        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    void deveLancarInsufficientStockException() {
        assertThrows(InsufficientStockException.class, () -> {
            throw new InsufficientStockException("Estoque insuficiente");
        });
    }
}
