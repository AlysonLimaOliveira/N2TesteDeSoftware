package ragatanga.ciclovida;

import org.junit.jupiter.api.*;
import ragatanga.model.Carrinho;

public class CicloVidaTest {
    private static Carrinho carrinho;

    @BeforeAll
    static void setUpClass() {
        System.out.println("@BeforeAll - Executa uma vez antes de todos os testes");
    }

    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach - Executa antes de cada teste");
        carrinho = new Carrinho();
    }

    @Test
    void testAdicionarItem() {
        System.out.println("Executando teste 1");
        carrinho.adicionar("SKU1", 1);
        Assertions.assertEquals(1, carrinho.getItems().get("SKU1"));
    }

    @Test
    void testAdicionarOutroItem() {
        System.out.println("Executando teste 2");
        carrinho.adicionar("SKU2", 2);
        Assertions.assertEquals(2, carrinho.getItems().get("SKU2"));
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach - Executa depois de cada teste");
    }

    @AfterAll
    static void tearDownClass() {
        System.out.println("@AfterAll - Executa uma vez depois de todos os testes");
    }
}