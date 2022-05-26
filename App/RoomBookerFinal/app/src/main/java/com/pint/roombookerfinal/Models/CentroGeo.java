package com.pint.roombookerfinal.Models;

import java.util.ArrayList;
import java.util.List;

public class CentroGeo {
    private int idCentro;
    private String nomeCentro;
    private Object imagem;
    private boolean ativo;
    private List<Sala> salas;
    private ArrayList<Object> utilizadorCentros;

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public String getNomeCentro() {
        return nomeCentro;
    }

    public void setNomeCentro(String nomeCentro) {
        this.nomeCentro = nomeCentro;
    }

    public Object getImagem() {
        return imagem;
    }

    public void setImagem(Object imagem) {
        this.imagem = imagem;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }

    public ArrayList<Object> getUtilizadorCentros() {
        return utilizadorCentros;
    }

    public void setUtilizadorCentros(ArrayList<Object> utilizadorCentros) {
        this.utilizadorCentros = utilizadorCentros;
    }
}
