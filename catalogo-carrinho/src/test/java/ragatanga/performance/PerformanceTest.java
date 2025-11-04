package ragatanga.performance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import ragatanga.model.Carrinho;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class PerformanceTest {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testPerformanceAdicionarItens() {
        Carrinho carrinho = new Carrinho();
        for (int i = 0; i < 1000; i++) {
            carrinho.adicionar("SKU" + i, 1);
        }
    }

    @Test
    void testPerformanceComAssertTimeout() {
        assertTimeout(java.time.Duration.ofMillis(500), () -> {
            Carrinho carrinho = new Carrinho();
            for (int i = 0; i < 500; i++) {
                carrinho.adicionar("SKU" + i, 1);
            }
        });
    }

    @Test
    void testPerformanceComPreemptiveTimeout() {
        assertTimeoutPreemptively(java.time.Duration.ofMillis(500), () -> {
            Carrinho carrinho = new Carrinho();
            for (int i = 0; i < 500; i++) {
                carrinho.adicionar("SKU" + i, 1);
            }
        });
    }
}