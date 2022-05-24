package com.pint.roombookerfinal.Models;

import java.util.List;

public class Sala {
    private List<Sala> salasList;
    private Integer idSala;
    private Integer idCentro;
    private Integer nSala;
    private Integer lotacaoMax;
    private String tempoMinLimp;
    private Boolean ativo;
    private CentroGeografico idCentroNavigation;
    private List<Reserva> reservas = null;

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

    public CentroGeografico getIdCentroNavigation() {
        return idCentroNavigation;
    }

    public void setIdCentroNavigation(CentroGeografico idCentroNavigation) {
        this.idCentroNavigation = idCentroNavigation;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Sala> getSalasList() {
        return salasList;
    }

    public void setSalasList(List<Sala> salasList) {
        this.salasList = salasList;
    }
}
