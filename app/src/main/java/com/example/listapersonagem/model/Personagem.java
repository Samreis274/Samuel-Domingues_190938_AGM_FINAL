package com.example.listapersonagem.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {

    //cria variaveis nome, altura, nascimento, telefone, endereco, cep, rg, genero
    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String endereco;
    private String CEP;
    private String RG;
    private String genero;
    //cria variavel id com valor de 0
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento, String telefone, String endereco,
                      String CEP, String RG, String genero) {

        //colocando valores nas variaveis
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.CEP = CEP;
        this.RG = RG;
        this.genero = genero;


    }

    public Personagem() {
    }

    //pegando os valores e sentando em cada variavel
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }
    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }


    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    public String getCEP() {
        return CEP;
    }
    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getRG() {
        return RG;
    }
    public void setRG(String RG) { this.RG = RG; }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    @NonNull
    @Override
    public String toString() {
        //retorna nome para aparecer na lista
        return nome;
    }

    //seta o valor da id
    public void setId(int id) {
        this.id = id;
    }

    //pega o valor da id
    public int getId() {
        return id;
    }

    // verifica o id se é valido, ou seja maior que 0
    public boolean IdValido(){
        return id>0;
    }
}
