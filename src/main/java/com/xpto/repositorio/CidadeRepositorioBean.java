package com.xpto.repositorio;

import com.xpto.dominio.Cidade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class CidadeRepositorioBean implements CidadeRepositorio {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Cidade buscarCidadePeloIBGEId(int idIBGE) {
        try {
            return em.find(Cidade.class, idIBGE);
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Cidade> buscarTodasAsCidades() {
        StringBuilder jpql = new StringBuilder()
            .append("SELECT c FROM Cidade c");
        try {
            return em.createQuery(jpql.toString())
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cidade> buscarCidadesPorParametro(String uf) {
        StringBuilder jpql = new StringBuilder()
                .append("SELECT c FROM Cidade c ")
                .append("WHERE c.uf = :uf");
        try {
            return em.createQuery(jpql.toString())
                    .setParameter("uf", uf)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Cidade adicionarCidade(Cidade cidade) throws SQLException {
        em.persist(cidade);
        em.merge(cidade);
        em.flush();
        return cidade;
    }

    @Override
    public int deletarCidade(int idIBGE) {
        StringBuilder jpql = new StringBuilder()
                .append("DELETE FROM Cidade c ")
                .append("WHERE c.ibge_id = :idIBGE");

        return em.createQuery(jpql.toString())
                .setParameter("idIBGE", idIBGE)
                .executeUpdate();
    }

    @Override
    public List<Cidade> buscarCidadesCapitais() {
        StringBuilder jpql = new StringBuilder()
                .append("SELECT c FROM Cidade c ")
                .append("WHERE c.capital = true");
        try {
            return em.createQuery(jpql.toString()).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Object> buscarQtoCidadesPorEstado() {
        StringBuilder jpql = new StringBuilder()
                .append("SELECT c.uf, COUNT(c.uf) ")
                .append("FROM Cidade c ")
                .append("GROUP BY c.uf ")
                .append("ORDER BY 2 DESC");
        try {
            return em.createQuery(jpql.toString()).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long qtoDeRegistros() {
        StringBuilder jpql = new StringBuilder()
                .append("SELECT COUNT(*) FROM Cidade c");
        try {
            return (Long) em.createQuery(jpql.toString()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
