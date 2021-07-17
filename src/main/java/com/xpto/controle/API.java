package com.xpto.controle;

import javax.validation.Valid;
import com.google.gson.Gson;
import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private Gson gson;
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
    public String cidadesQueSaoCapitais() {
        try {
            return gson.toJson(cidadeControle.cidadesQueSaoCapitais());
        } catch (CidadeExcecao e) {
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/estadoMaiorEMenor")
    public String estadoMaiorEMenor() {
        try {
            return gson.toJson(cidadeControle.estadoMaiorEMenor());
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/cidadesPorEstado")
    public String cidadesPorEstado() {
        try {
            return gson.toJson(cidadeControle.cidadesPorEstado());
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/quantidadeDeRegistros")
    public String quantidadeDeRegistros(){
        try {
            return gson.toJson(cidadeControle.quantidadeDeRegistro());
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/dadosCidadeById/{idIBGE}")
    public String dadosCidadeById(@PathVariable int idIBGE){
        try {
            return gson.toJson(cidadeControle.dadosCidadeByIdIBGE(idIBGE));
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/dadosCidadeByUF/{uf}")
    public String dadosCidadeByUF(@PathVariable String uf){
        try {
            return gson.toJson(cidadeControle.cidadesPorEstado(uf));
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @DeleteMapping("/deletarCidade/{idIBGE}")
    public String deletarUmaCidade(@PathVariable int idIBGE){
        return gson.toJson(cidadeControle.deletarCidade(idIBGE));
    }

    @GetMapping("/lerArquivoCSV")
    public String lerArquivoCSV(){
        try {
            return gson.toJson(cidadeControle.lerArquivoCSV());
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/stringPorColuna/{a}/{b}")
    public String colunaStringCSV(@PathVariable String a, @PathVariable String b){
        try {
            return gson.toJson(cidadeControle.stringPorColuna(a, b));
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/registroPorColuna/{a}")
    public String colunaRegistro(@PathVariable String a){
        try {
            return gson.toJson(cidadeControle.registroPorColuna(a));
        } catch (CidadeExcecao e){
            return e.getMensagemExcecao();
        }
    }

    @GetMapping("/distanciaEntreCidades")
    public String distanciaEntreCidades() {
        try {
            return gson.toJson(cidadeControle.distanciaEntreCidade());
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/novaCidade")
    public String novaCidade(@RequestBody @Valid Cidade cidade) {
        try {
            return gson.toJson(cidadeControle.adicionarNovaCidade(cidade));
        } catch (SQLException e) {
            return e.getMessage();
        }
    }

}
