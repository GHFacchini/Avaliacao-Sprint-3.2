package com.compasso.avaliacao.controller.form;

import com.compasso.avaliacao.modelo.Estado;
import com.compasso.avaliacao.modelo.Regiao;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EstadoForm {

    @NotEmpty
    @NotNull
    @Length(min = 4, max = 19)
    private String nome;
    //tem alguns arquivos desorganizados na pasta pois estava bugando o import do @enum
    @Enum(enumClass = Regiao.class, ignoreCase = true)
    private String regiao;
    @NotNull
    @Min(0)
    private Integer populacao;
    @NotEmpty
    @NotNull
    private String capital;
    @NotNull
    @DecimalMin("0")
    private BigDecimal area;

    public Estado converter() {
        String regiaoUp = regiao.toUpperCase();
        Regiao regiaoFormatada = null;
        switch (regiaoUp) {
            case "NORTE":
                regiaoFormatada = Regiao.NORTE;
                break;
            case "NORDESTE":
                regiaoFormatada = Regiao.NORDESTE;
                break;
            case "SUL":
                regiaoFormatada = Regiao.SUL;
                break;
            case "SUDESTE":
                regiaoFormatada = Regiao.SUDESTE;
                break;
            case "CENTRO-OESTE":
                regiaoFormatada = Regiao.CENTRO_OESTE;
                break;
        }

        return new Estado(nome, regiaoFormatada, populacao, capital, area);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
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
}
