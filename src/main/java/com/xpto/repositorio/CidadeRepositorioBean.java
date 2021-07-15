package com.xpto.repositorio;

import com.xpto.dominio.Cidade;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        StringBuilder jpql = new StringBuilder()
                .append("SELECT * FROM Cidade c")
                .append(" WHERE c.ibge_id = :idIBGE");

        try {
            return (Cidade) em.createNativeQuery(jpql.toString())
                    .unwrap(org.hibernate.Query.class)
                    .setResultTransformer(Transformers.aliasToBean(Cidade.class))
                    .setParameter("idIBGE", idIBGE).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cidade> buscarTodasAsCidades() {
        StringBuilder jpql = new StringBuilder()
            .append("SELECT * FROM Cidade c");

        try {
            return em.createNativeQuery(jpql.toString())
                    .unwrap(org.hibernate.Query.class)
                    .setResultTransformer(Transformers.aliasToBean(Cidade.class)).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cidade> buscarCidadesPorParametro(String uf) {
        StringBuilder jpql = new StringBuilder()
                .append("SELECT * FROM Cidade c ")
                .append("WHERE c.uf = :uf");

        try {
            return em.createNativeQuery(jpql.toString())
                    .unwrap(org.hibernate.Query.class)
                    .setResultTransformer(Transformers.aliasToBean(Cidade.class))
                    .setParameter("uf", uf)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void adicionarCidade(Cidade cidade) throws SQLException {
        em.merge(cidade);
        em.persist(cidade);
    }

    @Override
    public int deletarCidade(int idIBGE) {
        StringBuilder jpql = new StringBuilder()
                .append("DELETE FROM CIDADE c ")
                .append("WHERE c.id_ibge = :idIBGE ");

        Query query = em.createNativeQuery(jpql.toString())
                .setParameter("idIBGE", idIBGE);

        return query.executeUpdate();

    }

    @Override
    public List<Cidade> buscarCidadesCapitais() {
        StringBuilder jpql = new StringBuilder()
                .append("SELECT * FROM Cidade c")
                .append(" WHERE c.capital = true");

        try {
            return em.createNativeQuery(jpql.toString())
                    .unwrap(org.hibernate.Query.class)
                    .setResultTransformer(Transformers.aliasToBean(Cidade.class)).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
