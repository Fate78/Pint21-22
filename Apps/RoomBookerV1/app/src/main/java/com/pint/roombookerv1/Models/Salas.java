package com.pint.roombookerv1.Models;

import java.util.List;

public class Salas {
    private Integer idSala;
    private Integer idCentro;
    private Integer nSala;
    private Integer lotacaoMax;
    private String tempoMinLimp;
    private Boolean ativo;
    private Object idCentroNavigation;
    private List<Object> reservas = null;

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public Integer getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(Integer idCentro) {
        this.idCentro = idCentro;
    }

    public Integer getnSala() {
        return nSala;
    }

    public void setnSala(Integer nSala) {
        this.nSala = nSala;
    }

    public Integer getLotacaoMax() {
        return lotacaoMax;
    }

    public void setLotacaoMax(Integer lotacaoMax) {
        this.lotacaoMax = lotacaoMax;
    }

    public String getTempoMinLimp() {
        return tempoMinLimp;
    }

    public void setTempoMinLimp(String tempoMinLimp) {
        this.tempoMinLimp = tempoMinLimp;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Object getIdCentroNavigation() {
        return idCentroNavigation;
    }

    public void setIdCentroNavigation(Object idCentroNavigation) {
        this.idCentroNavigation = idCentroNavigation;
    }

    public List<Object> getReservas() {
        return reservas;
    }

    public void setReservas(List<Object> reservas) {
        this.reservas = reservas;
    }
}
