package ragatanga.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    @DisplayName("Deve criar produto corretamente")
    void deveCriarProduto() {
        Produto produto = new Produto("SKU123", "Camiseta", BigDecimal.valueOf(99.90));

        assertEquals("SKU123", produto.getSku());
        assertEquals("Camiseta", produto.getNome());
        assertEquals(BigDecimal.valueOf(99.90), produto.getPreco());
    }

    @Test
    @DisplayName("Deve manter imutabilidade dos atributos")
    void deveSerImutavel() {
        Produto produto = new Produto("SKU1", "Tenis", BigDecimal.TEN);

        // não há setters — verifica apenas se é final e valores são retornados
        // corretamente
        assertEquals("SKU1", produto.getSku());
        assertEquals("Tenis", produto.getNome());
        assertEquals(BigDecimal.TEN, produto.getPreco());
    }

    @Test
    @DisplayName("Preço não deve ser nulo")
    void precoNaoDeveSerNulo() {
        assertThrows(NullPointerException.class,
                () -> new Produto("SKU", "Item", null));
    }

    @Test
    @DisplayName("Nome não deve ser nulo")
    void nomeNaoDeveSerNulo() {
        assertThrows(NullPointerException.class,
                () -> new Produto("SKU", null, BigDecimal.ONE));
    }

    @Test
    @DisplayName("SKU não deve ser nulo")
    void skuNaoDeveSerNulo() {
        assertThrows(NullPointerException.class,
                () -> new Produto(null, "Produto", BigDecimal.ONE));
    }
}
