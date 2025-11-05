package ragatanga.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemCarrinhoTest {

    @Test
    void deveCriarItemComSkuEQuantidade() {
        ItemCarrinho item = new ItemCarrinho("SKU123", 3);

        assertEquals("SKU123", item.getSku());
        assertEquals(3, item.getQuantidade());
    }

    @Test
    void devePermitirQuantidadeZero() {
        ItemCarrinho item = new ItemCarrinho("SKU-TEST", 0);

        assertEquals("SKU-TEST", item.getSku());
        assertEquals(0, item.getQuantidade());
    }

    @Test
    void deveAceitarValoresNegativosMasIdealmenteDeveriaValidar() {
        // O comportamento atual permite, então o teste documenta isso
        ItemCarrinho item = new ItemCarrinho("SKU-NEG", -5);

        assertEquals("SKU-NEG", item.getSku());
        assertEquals(-5, item.getQuantidade());
    }
}
