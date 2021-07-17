package com.xpto.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class CidadeDistancia {

    private Cidade a;
    private Cidade b;
    private BigDecimal distancia;
}
