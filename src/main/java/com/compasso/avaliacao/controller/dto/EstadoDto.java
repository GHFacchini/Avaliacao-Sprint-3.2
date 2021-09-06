package com.compasso.avaliacao.controller.dto;

import com.compasso.avaliacao.modelo.Estado;
import com.compasso.avaliacao.modelo.Regiao;
import org.springframework.data.domain.Page;


import java.math.BigDecimal;

public class EstadoDto {

    private Long id;
    private String nome;
    private Regiao regiao;
    private int populacao;
    private String capital;
    private BigDecimal area;

    public EstadoDto(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.regiao = estado.getRegiao();
        this.populacao = estado.getPopulacao();
        this.capital = estado.getCapital();
        this.area = estado.getArea();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public static Page<EstadoDto> converter(Page<Estado> topicos) {

        return topicos.map(EstadoDto::new);
    }

}
