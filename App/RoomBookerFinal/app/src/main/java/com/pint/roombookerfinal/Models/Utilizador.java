package com.pint.roombookerfinal.Models;

import java.util.ArrayList;
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
    private boolean verificado;
    private boolean ativo;
    private String idTipoNavigation = null;
    ArrayList< Object > reservas = new ArrayList < Object > ();
    ArrayList < Object > tickets = new ArrayList < Object > ();
    ArrayList < Object > utilizadorCentros = new ArrayList < Object > ();


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

    public boolean getVerificado() {
        return verificado;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public String getIdTipoNavigation() {
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

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setIdTipoNavigation(String idTipoNavigation) {
        this.idTipoNavigation = idTipoNavigation;
    }
}
