package com.example.projetofinalpdm;

public class PedidoModel {
    private int id;
    private String Local;

    public PedidoModel(int id, String Local){
        this.id = id;
        this.Local = Local;

    }

    public PedidoModel() {
    }

    @Override
    public String toString() {
        return "PedidoModel{" +
                "id=" + id +
                ", Local='" + Local + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }
}
