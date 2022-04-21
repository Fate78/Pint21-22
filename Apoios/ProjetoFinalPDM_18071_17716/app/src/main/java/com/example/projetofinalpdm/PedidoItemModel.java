package com.example.projetofinalpdm;

public class PedidoItemModel {
    private int id_pedido;
    private int id_produto;

    public PedidoItemModel(int id_pedido, int id_produto){
        this.id_pedido= id_pedido;
        this.id_produto= id_produto;
    }

    public PedidoItemModel() {
    }

    @Override
    public String toString() {
        return "PedidoItemModel{" +
                "id_pedido=" + id_pedido +
                ", id_produto=" + id_produto +
                '}';
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }
}
