package com.cadastralivro.cadastralivro.application.service;


import com.cadastralivro.cadastralivro.domain.model.Livro;
import com.cadastralivro.cadastralivro.domain.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private  final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Livro cadastrarLivro(Livro livro){
        return repository.cadastrarLivro(livro);
    }

    @Transactional
    public List<Livro> buscarTodosOsLivros(){
        return repository.buscarTodosOsLivros();
    }
    @Transactional
    public Livro buscarPorId(Integer id){
        return  repository.buscarPorId(id);
    }

    @Transactional
    public  Livro buscarPorIsbn(String isbn){
        return repository.buscarPorIsbn(isbn);
    }

     @Transactional
      public  Livro atualizarLivro(Integer id, Livro livro){
           return repository.atualizarLivro(id,livro);
       }

    @Transactional
    public void deletarLivro(Integer id){
         repository.deletarLivro(id);
    }
}
