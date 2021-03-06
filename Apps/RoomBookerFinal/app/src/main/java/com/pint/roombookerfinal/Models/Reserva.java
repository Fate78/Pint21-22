package com.pint.roombookerfinal.Models;

import java.util.List;

public class Reserva {
    private List<Reserva> reservaList;
    private int idReserva;
    private int idSala;
    private int idUtilizador;
    private String horaInicio;
    private String horaFim;
    private String dataReserva;
    private int numPessoas;
    private Sala idSalaNavigation;
    private boolean ativo;

    public Reserva(int idSala, int idUtilizador, String horaInicio, String horaFim, String dataReserva, int numPessoas, boolean ativo){
        this.idSala = idSala;
        this.idUtilizador = idUtilizador;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dataReserva = dataReserva;
        this.numPessoas = numPessoas;
        this.ativo = ativo;
    }

    public Reserva(int idReserva, int idSala, int idUtilizador, String horaInicio, String horaFim, String dataReserva, int numPessoas, boolean ativo){
        this.idReserva = idReserva;
        this.idSala = idSala;
        this.idUtilizador = idUtilizador;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dataReserva = dataReserva;
        this.numPessoas = numPessoas;
        this.ativo = ativo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }

    public int getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(int numPessoas) {
        this.numPessoas = numPessoas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Sala getIdSalaNavigation() {
        return idSalaNavigation;
    }

    public void setIdSalaNavigation(Sala idSalaNavigation) {
        this.idSalaNavigation = idSalaNavigation;
    }

    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }
}
