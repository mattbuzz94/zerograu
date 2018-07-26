package br.com.zerograu.services;

import br.com.zerograu.domain.Produto;
import br.com.zerograu.repositories.ProdutoRepository;
import br.com.zerograu.repositories.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoRepository produtoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }    

    @Override
    public List<Produto> listAll() {
        List<Produto> Produtos = new ArrayList<>();
        produtoRepository.findAll().forEach(Produtos::add); // fun with Java 8
        return Produtos;
    }


    // @Override
    public List<Produto> searchProduto(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> r = query.from(Produto.class);
        Predicate predicate = builder.conjunction();

        for (SearchCriteria param : params) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate,
                        builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate,
                        builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase(":")) {
                if (Objects.equals(param.getKey(), "tituloProduto") || Objects.equals(param.getKey(), "descricaoProduto")
                        || Objects.equals(param.getKey(), "solucaoProposta") || Objects.equals(param.getKey(), "comentarioInterno")) {
                    predicate = builder.and(
                            builder.or(predicate, builder.like(r.get(param.getKey()), "%" + param.getValue() + "%")));
                }
            } else if (param.getOperation().equalsIgnoreCase("=")) {
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate,
                            builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
                }
            }
        }

        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Produto> retornaBusca(String textoBusca, String projetoBusca) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Produto> cquery = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> root = cquery.from(Produto.class);

        Path<String> projetoPath = root.get("projeto");
        Path<String> tituloProdutoPath = root.get("tituloProduto");
        Path<String> descricaoProdutoPath = root.get("descricaoProduto");
        Path<String> solucaoPropostaPath = root.get("solucaoProposta");
        Path<String> comentarioInternoPath = root.get("comentarioInterno");

        Predicate tituloLike = criteriaBuilder.like(criteriaBuilder.upper(tituloProdutoPath), "%" + textoBusca.toUpperCase() + "%");
        Predicate descricaoLike = criteriaBuilder.like(criteriaBuilder.upper(descricaoProdutoPath), "%" + textoBusca.toUpperCase() + "%");
        Predicate solucaoLike = criteriaBuilder.like(criteriaBuilder.upper(solucaoPropostaPath), "%" + textoBusca.toUpperCase() + "%");
        Predicate comentarioLike = criteriaBuilder.like(criteriaBuilder.upper(comentarioInternoPath), "%" + textoBusca.toUpperCase() + "%");
        Predicate projetoLike = criteriaBuilder.like(criteriaBuilder.upper(projetoPath), "%" + projetoBusca.toUpperCase() + "%");


        cquery.where(criteriaBuilder.and(projetoLike,
                criteriaBuilder.or(descricaoLike, solucaoLike, tituloLike, comentarioLike)));

        return entityManager.createQuery(cquery).getResultList();
    }

    @Override
    public Produto getById(Integer id) {
        return produtoRepository.findOne(id);
    }

    @Override
    public Produto saveOrUpdate(Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    @Override
    public void delete(Integer id) {
        produtoRepository.delete(id);
    }
}