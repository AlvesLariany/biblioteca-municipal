package com.cadastralivro.cadastralivro.infraestructure.implement;


import com.cadastralivro.cadastralivro.domain.model.Livro;
import com.cadastralivro.cadastralivro.domain.repository.LivroRepository;
import com.cadastralivro.cadastralivro.infraestructure.entity.LivroEntity;
import com.cadastralivro.cadastralivro.infraestructure.repository.LivroJpa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LivroRepositoryImpl implements LivroRepository {

   private final LivroJpa jpaRepository;

    public LivroRepositoryImpl(LivroJpa jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    //Data Mapper
    //onverter o objeto de domínio para uma entidade do banco
    private LivroEntity toEntity(Livro livro) {
        LivroEntity entity = new LivroEntity();
        entity.setId(livro.getId());
        entity.setIsbn(livro.getIsbn());
        entity.setTitulo(livro.getTitulo());
        entity.setAutor(livro.getAutor());
        entity.setAnoPublicacao(livro.getAnoPublicacao());
        entity.setQuantidadeEstoque(livro.getQuantidadeEstoque());
        return entity;
    }

    //converter uma entidade vinda do banco para o objeto de domínio
    private Livro toDomain(LivroEntity entity) {
        Livro livro = new Livro();
        livro.setId(entity.getId());
        livro.setIsbn(entity.getIsbn());
        livro.setTitulo(entity.getTitulo());
        livro.setAutor(entity.getAutor());
        livro.setAnoPublicacao(entity.getAnoPublicacao());
        livro.setQuantidadeEstoque(entity.getQuantidadeEstoque());
        return livro;
    }

    @Override
    @Transactional
    public Livro cadastrarLivro(Livro livro) {
        LivroEntity saved = jpaRepository.saveAndFlush(toEntity(livro));
        return toDomain(saved);
    }

    @Override
    @Transactional
    public List<Livro> buscarTodosOsLivros() {
        List<LivroEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(this::toDomain)
                .toList();
    }


    @Override
    public Livro buscarPorId(Integer id){
        LivroEntity entity = jpaRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return  toDomain(entity);
    }

    @Override
    public Livro buscarPorIsbn(String isbn) {
        LivroEntity entity = jpaRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return toDomain(entity);
        }

    @Override
    public void deletarLivro(Integer id){
        LivroEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        jpaRepository.delete(entity);
    }

    @Override
    public Livro atualizarLivro(Integer id, Livro livroAtualizado) {

        LivroEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));


        entity.setTitulo(livroAtualizado.getTitulo());
        entity.setAutor(livroAtualizado.getAutor());
        entity.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
        entity.setQuantidadeEstoque(livroAtualizado.getQuantidadeEstoque());
        entity.setIsbn(livroAtualizado.getIsbn());

        LivroEntity atualizado = jpaRepository.save(entity);
        return toDomain(atualizado);
    }



}

