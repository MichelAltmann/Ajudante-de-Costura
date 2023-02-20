package modelDominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Pedido implements Serializable {

    private String idPedido;
    private Cliente cliente;
    private int prioridade;
    private String Titulo;
    private Float preco;
    private String descricao;
    private Date dataEntrega;
    private Date dataCriacao;
    private ArrayList<Material> listaMateriais;
    private byte[] imagem;
    private Medidas medidas;

    public Pedido(String idPedido, Cliente cliente, int prioridade, String Titulo, Float preco, String descricao, Date dataEntrega, Date dataCriacao, ArrayList<Material> listaMateriais, byte[] imagem, Medidas medidas) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.prioridade = prioridade;
        this.Titulo = Titulo;
        this.preco = preco;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.dataCriacao = dataCriacao;
        this.listaMateriais = listaMateriais;
        this.imagem = imagem;
        this.medidas = medidas;
    }

    public Pedido(Cliente cliente, String uuidPedido, int prioridade, String Titulo, Float preco, String descricao, Date dataEntrega, Date dataCriacao, ArrayList<Material> listaMateriais, byte[] imagem, Medidas medidas) {
        this.cliente = cliente;
        this.idPedido = idPedido;
        this.prioridade = prioridade;
        this.Titulo = Titulo;
        this.preco = preco;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.dataCriacao = dataCriacao;
        this.listaMateriais = listaMateriais;
        this.imagem = imagem;
        this.medidas = medidas;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }





    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ArrayList<Material> getListaMateriais() {
        return listaMateriais;
    }

    public void setListaMateriais(ArrayList<Material> listaMateriais) {
        this.listaMateriais = listaMateriais;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Medidas getMedidas() {
        return medidas;
    }

    public void setMedidas(Medidas medidas) {
        this.medidas = medidas;
    }

    public float CalculaPrecoPedido() {
        float precoPedido = 0;
        for (int x = 0; x > this.listaMateriais.size(); x++) {
            precoPedido += this.listaMateriais.get(x).CalculaPrecoTotal();
        }
        return precoPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", cliente=" + cliente + ", prioridade=" + prioridade + ", Titulo=" + Titulo + ", descricao=" + descricao + ", dataEntrega=" + dataEntrega + ", dataCriacao=" + dataCriacao + ", listaMateriais=" + listaMateriais + ", imagem=" + imagem + ", medidas=" + medidas + '}';
    }


}
