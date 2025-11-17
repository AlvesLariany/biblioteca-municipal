package com.cadastralivro.cadastralivro.domain.repository;

import com.cadastralivro.cadastralivro.domain.model.Livro;

import java.util.List;

public interface LivroRepository {

    Livro cadastrarLivro(Livro livro);
    List<Livro> buscarTodosOsLivros();
    Livro buscarPorId(Integer id);
    Livro  buscarPorIsbn(String isbn);
    Livro atualizarLivro (Integer id, Livro livro);
    void deletarLivro(Integer id);

}
