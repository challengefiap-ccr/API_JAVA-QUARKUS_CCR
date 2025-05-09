package org.acme.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TTCCR_USUARIO")
public class UserEntity {
    @Id
    @Column(name = "ID_USUARIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Column(name = "NOME", nullable = false)
    private String nome;


    @Column(name = "EMAIL", nullable = false)
    private String email;


    @Column(name = "SENHA", nullable = false)
    private String senha;


    public UserEntity() {

    }

    // Getters e Setters

    public String getId() {
        return String.valueOf(id);
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
