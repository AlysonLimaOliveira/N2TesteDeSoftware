package ragatanga.repository;

import org.junit.jupiter.api.Test;
import ragatanga.exception.InsufficientStockException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryEstoqueRepositoryTest {

    @Test
    void deveConsultarQuantidadeExistente() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setQuantidade("SKU123", 10);

        Optional<Integer> quantidade = repo.consultarQuantidade("SKU123");

        assertTrue(quantidade.isPresent());
        assertEquals(10, quantidade.get());
    }

    @Test
    void deveRetornarEmptyQuandoSkuNaoExistir() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();

        Optional<Integer> quantidade = repo.consultarQuantidade("SKU999");

        assertFalse(quantidade.isPresent());
    }

    @Test
    void deveReservarComSucessoQuandoHaEstoque() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setQuantidade("SKU123", 10);

        repo.reservar("SKU123", 4);
        Optional<Integer> restante = repo.consultarQuantidade("SKU123");

        assertTrue(restante.isPresent());
        assertEquals(6, restante.get());
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueInsuficiente() {
        InMemoryEstoqueRepository repo = new InMemoryEstoqueRepository();
        repo.setQuantidade("SKU123", 3);

        assertThrows(InsufficientStockException.class, () -> {
            repo.reservar("SKU123", 5);
        });
    }
}
