package com.github.lassulfi.dto;

import java.math.BigDecimal;

public class AtualizaProdutoDto {

    private BigDecimal valor;

    public AtualizaProdutoDto() {
    }

    public AtualizaProdutoDto(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
