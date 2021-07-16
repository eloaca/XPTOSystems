package com.xpto.controle;

import com.xpto.excecao.CidadeExcecao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ejb.EJB;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/xpto")
public class API {

    @EJB
    CidadeControle cidadeControle;

    @GetMapping("salvarCidadesCsv")
    public Object salvarCsv(){
        try {
            cidadeControle.salvarCidadesCsv();
        } catch (IOException | SQLException e) {
            return e.getMessage();
        }
        return "Arquivo CSV salvo com sucesso";
    }

    @GetMapping("/cidadesQueSaoCapitais")
    public Object cidadesQueSaoCapitais() {
        try {
            return cidadeControle.cidadesQueSaoCapitais();
        } catch (CidadeExcecao e) {
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/estadoMaiorEMenor")
    public Object estadoMaiorEMenor() {
        try {
            return cidadeControle.estadoMaiorEMenor();
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/cidadesPorEstado")
    public Object cidadesPorEstado() {
        try {
            return cidadeControle.cidadesPorEstado();
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/quantidadeDeRegistros")
    public Object quantidadeDeRegistros(){
        try {
            return cidadeControle.quantidadeDeRegistro();
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/dadosCidadeById/{idIBGE}")
    public Object dadosCidadeById(@PathVariable int idIBGE){
        try {
            return cidadeControle.dadosCidadeByIdIBGE(idIBGE);
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/dadosCidadeByUF/{uf}")
    public Object dadosCidadeByUF(@PathVariable String uf){
        try {
            return cidadeControle.cidadesPorEstado(uf);
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    public Object deletarUmaCidade(@PathVariable int idIBGE){
        try {
            return cidadeControle.deletarCidade(idIBGE);
        } catch (SQLException e) {
            throw new CidadeExcecao("Nao foi possivel executar esta acao: "+e.getMessage());
        }
    }

}
