package com.xpto.excecao;

import java.util.List;

public class CidadeExcecao extends Exception {

    private static final long serialVersionUID = 7504011435259960582L;

    private String mensagemExcecao;
    private List<String> detalhes;

    public CidadeExcecao(String mensagemExcecao, List<String> detalhes) {
        this.mensagemExcecao = mensagemExcecao;
        this.detalhes = detalhes;
    }

    public String getMensagemExcecao() {
        return mensagemExcecao;
    }

    public void setMensagemExcecao(String mensagemExcecao) {
        this.mensagemExcecao = mensagemExcecao;
    }

    public List<String> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<String> detalhes) {
        this.detalhes = detalhes;
    }

    public CidadeExcecao(String mensagemExcecao) {
        this.mensagemExcecao = mensagemExcecao;
    }
}
