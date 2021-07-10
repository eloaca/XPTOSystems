package com.xpto.repositorio;

import com.xpto.dominio.Cidade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CidadeRepositorioBean implements CidadeRepositorio {

    @PersistenceContext
    private final EntityManager em;

    public CidadeRepositorioBean(EntityManager em) {
        this.em = em;
    }


    @Override
    public Cidade buscarCidadePeloIBGEId(Long idIBGE) {
        return em.find(Cidade.class, idIBGE);
    }

    @Override
    public List<Cidade> buscarTodasAsCidades() {
        return null;
    }

    @Override
    public void salvarTodasCidades(List<Cidade> cidades) {
        for (Cidade c : cidades) {
            em.persist(c);
        }
    }

    @Override
    public List<Cidade> buscarCidadesPorParametro(String... strings) {
        return null;
    }

    @Override
    public void adicionarCidade(Cidade cidade) {
        em.merge(cidade);
        em.persist(cidade);
    }

    @Override
    public void deletarCidade(Cidade cidade) {

    }
}
