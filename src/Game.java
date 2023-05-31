package src;

public class Game {
    private String name;
    private String descricao;
    private Double preco;
    private Image image;
            
    public Game(String name, String descricao, Double preco, Image image) {
        this.name = name;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    public void getName() { return name }

    public void setName(String name) { this.name = name }

    public void getDescricao() { return descricao }

    public void setDescricao(String descricao) { this.descricao = descricao }

    public void getPrice() { return price }

    public void setPrice(Double price) { this.price = price }
}
