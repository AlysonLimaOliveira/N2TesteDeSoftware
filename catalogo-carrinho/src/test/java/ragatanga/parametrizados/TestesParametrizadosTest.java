package ragatanga.parametrizados;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ragatanga.model.Carrinho;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestesParametrizadosTest {

    private static Stream<Arguments> produtosComQuantidades() {
        return Stream.of(
            Arguments.of("SKU1", 1, 1),
            Arguments.of("SKU2", 2, 2),
            Arguments.of("SKU3", 3, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("produtosComQuantidades")
    void testAdicionarDiferentesProdutos(String sku, int quantidade, int expectedQuantidade) {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(sku, quantidade);
        assertEquals(expectedQuantidade, carrinho.getItems().get(sku));
    }

    private static Stream<Arguments> multiplasOperacoes() {
        return Stream.of(
            Arguments.of("SKU1", new int[]{1, 2, 3}, 6),
            Arguments.of("SKU2", new int[]{2, 2, 2}, 6),
            Arguments.of("SKU3", new int[]{5, 3, 2}, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("multiplasOperacoes")
    void testMultiplasAdicoes(String sku, int[] quantidades, int expectedTotal) {
        Carrinho carrinho = new Carrinho();
        for (int quantidade : quantidades) {
            carrinho.adicionar(sku, quantidade);
        }
        assertEquals(expectedTotal, carrinho.getItems().get(sku));
    }
}