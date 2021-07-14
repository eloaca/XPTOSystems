package com.xpto.repositorio;

import com.xpto.dominio.Cidade;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CidadeRepositorioBean implements CidadeRepositorio {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Cidade buscarCidadePeloIBGEId(int idIBGE) {
        try {
            return em.find(Cidade.class, idIBGE);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Cidade> buscarTodasAsCidades() {
        StringBuilder jpql = new StringBuilder()
            .append("SELECT * FROM Cidade c");

        Query query = em.createNativeQuery(jpql.toString())
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(Cidade.class));

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void salvarTodasCidades(List<Cidade> cidades) {
        for (Cidade c : cidades) {
            em.persist(c);
        }
    }

    @Override
    public List<Cidade> buscarCidadesPorParametro(String uf) {
        StringBuilder jpql = new StringBuilder();
            jpql.append("SELECT c.* FROM " +Cidade.class);
            jpql.append("CIDADE c ");
            jpql.append("WHERE c.uf = :uf ");

        Query query = em.createQuery(jpql.toString());
            query.setParameter("uf", uf);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void adicionarCidade(Cidade cidade) {
        em.merge(cidade);
        em.persist(cidade);
    }

    @Override
    public int deletarCidade(Long idIBGE) {
        StringBuilder jpql = new StringBuilder();
            jpql.append("DELETE FROM CIDADE c ");
            jpql.append("WHERE c.id_ibge = :idIBGE ");

        Query query = em.createQuery(jpql.toString());
            query.setParameter("idIBGE", idIBGE);

        return query.executeUpdate();

    }

    @Override
    public List<Cidade> buscarCidadesCapitais() {
        StringBuilder jpql = new StringBuilder()
                .append("SELECT * FROM Cidade c")
                .append(" WHERE c.capital = true");

        Query query = em.createNativeQuery(jpql.toString())
                .unwrap(org.hibernate.Query.class)
                .setResultTransformer(Transformers.aliasToBean(Cidade.class));

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
