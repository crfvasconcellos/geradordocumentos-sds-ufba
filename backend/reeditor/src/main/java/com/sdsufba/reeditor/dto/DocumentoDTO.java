package com.sdsufba.reeditor.dto;

public class DocumentoDTO {
    private String nome;
    private String campeonato;

    public DocumentoDTO() {
    }

    public DocumentoDTO(String nome, String campeonato) {
        this.nome = nome;
        this.campeonato = campeonato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(String campeonato) {
        this.campeonato = campeonato;
    }
}
