package br.edu.cefsa.model;

import java.io.Serializable;

/**
 *
 * @author Israel
 */
public class Departamento implements Serializable {

    private Long codigo;
    private String nome;

    public Departamento() {
    }

    public Departamento(Long codigo) {
        this.codigo = codigo;
    }

    public Departamento(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
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
}
