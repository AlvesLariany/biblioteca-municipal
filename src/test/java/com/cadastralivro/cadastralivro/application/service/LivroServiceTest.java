package com.cadastralivro.cadastralivro.application.service;

import com.cadastralivro.cadastralivro.domain.model.Livro;
import com.cadastralivro.cadastralivro.domain.repository.LivroRepository;
import com.cadastralivro.cadastralivro.infraestructure.implement.LivroRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Inicializa todos os mocks
@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    //Classe testada
    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Test
    public void deveCadastrarLivro() {
        Livro livro = new Livro("123456", "Dom Casmurro", "Machado de Assis", "1899", 300);
        Mockito.when(livroRepository.cadastrarLivro(livro)).thenReturn(livro);

        Livro resultado = livroService.cadastrarLivro(livro);

        assertNotNull(resultado);
        assertEquals("123456", resultado.getIsbn());
        Mockito.verify(livroRepository, Mockito.times(1)).cadastrarLivro(livro);
    }


    @Test
    public void deveRetornarUmaListaComUmLivro() {
        Livro livroMockado= new Livro("123456","A hora da estrela", "Clarice Lispector", "2020", 100);
        Mockito.when(livroRepository.buscarTodosOsLivros()).thenReturn(Collections.singletonList(livroMockado));
        List<Livro> livros= livroService.buscarTodosOsLivros();

        Assertions.assertEquals(1,livros.size());
    }

    @Test
    public void deveBuscarLivroPorId() {
        Livro livro = new Livro("123", "A Hora da Estrela", "Clarice", "2020", 100);

        Mockito.when(livroRepository.buscarPorId(1)).thenReturn(livro);

        Livro resultado = livroService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals("A Hora da Estrela", resultado.getTitulo());

    }


    @Test
    public void deveBuscarLivroPorIsbn() {
        Livro livro = new Livro("123456", "Dom Casmurro", "Machado de Assis", "1900", 200);

        Mockito.when(livroRepository.buscarPorIsbn("123456")).thenReturn(livro);

        Livro resultado = livroService.buscarPorIsbn("123456");

        assertNotNull(resultado);
        assertEquals("Dom Casmurro", resultado.getTitulo());
        Mockito.verify(livroRepository).buscarPorIsbn("123456");
    }

    @Test
    public void deveAtualizarLivro() {
        Livro atualizado = new Livro("999", "Livro Novo", "Autor Novo", "2024", 300);

        Mockito.when(livroRepository.atualizarLivro(1, atualizado)).thenReturn(atualizado);

        Livro resultado = livroService.atualizarLivro(1,atualizado);

        assertNotNull(resultado);
        assertEquals("Livro Novo", resultado.getTitulo());
        Mockito.verify(livroRepository).atualizarLivro(1,atualizado);
    }


    @Test
    public void deveDeletarLivro() {
        Integer id = 5;
        livroService.deletarLivro(id);
        Mockito.verify(livroRepository, Mockito.times(1)).deletarLivro(id);
    }

}