public class Pedido {
    private String codigo;
    private String descricao;

    public Pedido(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString() {
        return "Pedido{codigo='" + codigo + "', descricao='" + descricao + "'}";
    }
}

