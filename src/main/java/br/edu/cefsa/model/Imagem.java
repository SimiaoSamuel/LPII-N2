/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.cefsa.model;

import java.io.Serializable;

/**
 *
 * @author MKarrer
 */
public class Imagem implements Serializable {

    private Long codigo;
    private String nome;
    private byte[] conteudo;

    public Imagem() {
    }

    public Imagem(Long codigo) {
        this.codigo = codigo;
    }

    public Imagem(String nome, byte[] conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
    }

    public Imagem(Long codigo, String nome, byte[] conteudo) {
        this.codigo = codigo;
        this.nome = nome;
        this.conteudo = conteudo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }
}