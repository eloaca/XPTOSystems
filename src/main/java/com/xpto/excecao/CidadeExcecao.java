package com.xpto.excecao;

import java.util.Map;

public class CidadeExcecao extends RuntimeException {

    private static final long serialVersionUID = 7504011435259960582L;

    private String mensagemExcecao;

    public CidadeExcecao(String mensagemExcecao) {
        this.mensagemExcecao = mensagemExcecao;
    }

    public String getMensagemExcecao() {
        return mensagemExcecao;
    }

    public void setMensagemExcecao(String mensagemExcecao) {
        this.mensagemExcecao = mensagemExcecao;
    }
}
