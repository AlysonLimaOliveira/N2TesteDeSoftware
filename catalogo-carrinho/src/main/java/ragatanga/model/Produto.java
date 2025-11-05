package ragatanga.model;

import java.math.BigDecimal;

public class Produto {
    private final String sku;
    private final String nome;
    private final BigDecimal preco;

    public Produto(String sku, String nome, BigDecimal preco) {
        if (sku == null)
            throw new NullPointerException("sku não pode ser nulo");
        if (nome == null)
            throw new NullPointerException("nome não pode ser nulo");
        if (preco == null)
            throw new NullPointerException("preço não pode ser nulo");

        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
    }

    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
