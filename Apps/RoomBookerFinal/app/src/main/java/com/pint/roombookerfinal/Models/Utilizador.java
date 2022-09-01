package com.pint.roombookerfinal.Models;

import java.util.List;

public class Utilizador {
    private List<Utilizador> utilizadoresList;
    private Integer idUtilizador;
    private Integer idTipo;
    private String nomeUtilizador;
    private String nomeCompleto;
    private String palavraPasse;
    private String email;
    private String dataNascimento;
    private boolean email_verificado;
    private boolean password_verificada;
    private String codigo_verificacao;
    private boolean ativo;
    private IdTipoNavigation idTipoNavigation = null;
    private List<Reserva> reservas = null;
    private List < Object > tickets = null;
    private List<CentroGeo> utilizadorCentro = null;

    public Utilizador(int idUtilizador, String nomeUtilizador, String email)
    {
        this.idUtilizador = idUtilizador;
        this.nomeUtilizador = nomeUtilizador;
        this.email = email;
    }

    public Utilizador(int idUtilizador, int idTipo, String nomeUtilizador, String nomeCompleto, String palavraPasse, String email,
                      String dataNascimento, boolean verificado, boolean password_verificada ,boolean ativo)
    {
        this.idUtilizador = idUtilizador;
        this.nomeUtilizador = nomeUtilizador;
        this.nomeCompleto = nomeCompleto;
        this.palavraPasse = palavraPasse;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.email_verificado = email_verificado;
        this.password_verificada = password_verificada;
        this.ativo = ativo;
        this.idTipo = idTipo;
    }

    // Getter Methods
    public Integer getIdUtilizador() {
        return idUtilizador;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getPalavraPasse() {
        return palavraPasse;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public boolean getEmailVerificado() {
        return email_verificado;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public IdTipoNavigation getIdTipoNavigation() {
        return idTipoNavigation;
    }

    // Setter Methods

    public void setIdUtilizador(Integer idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setPalavraPasse(String palavraPasse) {
        this.palavraPasse = palavraPasse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setVerificado(boolean email_verificado) {
        this.email_verificado = email_verificado;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setIdTipoNavigation(IdTipoNavigation idTipoNavigation) {
        this.idTipoNavigation = idTipoNavigation;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Object> getTickets() {
        return tickets;
    }

    public void setTickets(List<Object> tickets) {
        this.tickets = tickets;
    }

    public List<CentroGeo> getUtilizadorCentro() {
        return utilizadorCentro;
    }

    public void setUtilizadorCentro(List<CentroGeo> utilizadorCentro) {
        this.utilizadorCentro = utilizadorCentro;
    }

    public boolean isPassword_verificada() {
        return password_verificada;
    }

    public void setPassword_verificada(boolean password_verificada) {
        this.password_verificada = password_verificada;
    }
}
