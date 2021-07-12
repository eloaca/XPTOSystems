package com.xpto.controle;

import com.xpto.dominio.Cidade;
import com.xpto.excecao.CidadeExcecao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ejb.EJB;
import java.util.List;

@RestController
@RequestMapping("/api/example")
public class Exemplo {

    @EJB
    CidadeControle cidadeControle;

    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/cidadesCapitais")
    public List<String> teste() throws CidadeExcecao {
        try {
            return cidadeControle.cidadesCapitais();
        }catch (CidadeExcecao e) {
            return null;
        }
    }

}
