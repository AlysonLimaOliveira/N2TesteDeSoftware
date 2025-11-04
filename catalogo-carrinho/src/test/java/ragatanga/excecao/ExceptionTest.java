package ragatanga.excecao;

import org.junit.jupiter.api.Test;
import ragatanga.exception.InsufficientStockException;
import ragatanga.model.Carrinho;
import ragatanga.repository.EstoqueRepository;
import ragatanga.repository.InMemoryEstoqueRepository;
import ragatanga.service.CarrinhoService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExceptionTest {

    @Test
    void testQuantidadeNegativa() {
        Carrinho carrinho = new Carrinho();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carrinho.adicionar("SKU1", -1);
        });
        
        String expectedMessage = "quantidade deve ser > 0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testEstoqueInsuficiente() {
        EstoqueRepository estoque = new InMemoryEstoqueRepository();
        CarrinhoService service = new CarrinhoService(estoque);
        
        assertThrows(InsufficientStockException.class, () -> {
            service.verificarEstoque("SKU1", 1000);
        });
    }
}