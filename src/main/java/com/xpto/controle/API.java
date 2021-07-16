package com.xpto.controle;

import com.xpto.excecao.CidadeExcecao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ejb.EJB;

@RestController
@RequestMapping("/api/xpto")
public class API {

    @EJB
    CidadeControle cidadeControle;
/*
    @GetMapping("salvarCidadesCsv")
    public Object salvarCsv(){
        try {
            cidadeControle.salvarCidadesCsv();
        } catch (IOException | SQLException e) {
            return e.getMessage();
        }
        return "Arquivo CSV salvo com sucesso";
    }
 */
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

    @GetMapping("/deletarCidade/{idIBGE}")
    public Object deletarUmaCidade(@PathVariable int idIBGE){
        return cidadeControle.deletarCidade(idIBGE);
    }

    @GetMapping("/lerArquivoCSV")
    public Object lerArquivoCSV(@RequestParam("arquivo") String arquivo){
        try {
            return cidadeControle.lerArquivoCSV(arquivo);
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

}
