package com.example.projetofinalpdm;

public class ProductModel {
    private int id;
    private String designacao;
    private float preco;
    private String alergias;
    private String tipo;

    public ProductModel(int id,String designacao,float preco,String alergias,String tipo){
        this.id= id;
        this.designacao = designacao;
        this.preco = preco;
        this.alergias = alergias;
        this.tipo = tipo;
    }

    public ProductModel() {

    }
    //toString para dar print dos conteúdos da Class


    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", designacao='" + designacao + '\'' +
                ", preco=" + preco +
                ", alergias='" + alergias + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
