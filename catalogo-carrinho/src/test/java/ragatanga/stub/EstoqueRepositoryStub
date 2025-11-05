package ragatanga.stub;

import ragatanga.repository.EstoqueRepository;
import java.util.HashMap;
import java.util.Map;

public class EstoqueRepositoryStub implements EstoqueRepository {

    private final Map<String, Integer> estoque = new HashMap<>();

    public void adicionar(String sku, int quantidade) {
        estoque.put(sku, quantidade);
    }

    @Override
    public boolean temEstoque(String sku, int quantidade) {
        return estoque.getOrDefault(sku, 0) >= quantidade;
    }

    @Override
    public void reservar(String sku, int quantidade) {
        if (!temEstoque(sku, quantidade)) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }
        estoque.put(sku, estoque.get(sku) - quantidade);
    }

    public int quantidadeRestante(String sku) {
        return estoque.getOrDefault(sku, 0);
    }
}
