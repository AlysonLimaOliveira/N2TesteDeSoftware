package ragatanga.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoTest {

    @Test
    @DisplayName("Deve adicionar item ao carrinho")
    void deveAdicionarItem() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar("SKU123", 2);

        Map<String, Integer> items = carrinho.getItems();
        assertEquals(1, items.size());
        assertEquals(2, items.get("SKU123"));
    }

    @Test
    @DisplayName("Deve somar quantidade quando adicionar o mesmo SKU")
    void deveSomarQuantidadeQuandoRepetido() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar("SKU123", 2);
        carrinho.adicionar("SKU123", 3);

        assertEquals(1, carrinho.getItems().size());
        assertEquals(5, carrinho.getItems().get("SKU123"));
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar adicionar quantidade <= 0")
    void deveFalharQuandoQuantidadeInvalida() {
        Carrinho carrinho = new Carrinho();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> carrinho.adicionar("SKU123", 0));

        assertEquals("quantidade deve ser > 0", ex.getMessage());
    }

    @Test
    @DisplayName("Deve limpar o carrinho")
    void deveLimparCarrinho() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar("SKU1", 2);
        carrinho.adicionar("SKU2", 1);
        carrinho.limpar();

        assertTrue(carrinho.getItems().isEmpty());
    }

    @Test
    @DisplayName("Mapa retornado deve ser imutável")
    void mapaDeveSerImutavel() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar("SKU1", 2);

        Map<String, Integer> items = carrinho.getItems();

        assertThrows(UnsupportedOperationException.class, () -> items.put("SKU2", 1));
    }
}
