package src;

public class Game {
    private String name;
    private String descricao;
    private Double price;
            
    public Game(String name, String descricao, Double preco) {
        this.name = name;
        this.descricao = descricao;
        this.price = preco;
    }
    
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
}
