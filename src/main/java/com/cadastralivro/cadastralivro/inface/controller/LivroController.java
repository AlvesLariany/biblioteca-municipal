package com.cadastralivro.cadastralivro.inface.controller;


import com.cadastralivro.cadastralivro.application.service.LivroService;
import com.cadastralivro.cadastralivro.domain.model.Livro;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")

public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Livro> buscarTodosOsLivros(){
        return service.buscarTodosOsLivros();
    }
    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/isbn/{isbn}")
    public Livro buscarPorIsbn(@PathVariable String isbn) {
        return service.buscarPorIsbn(isbn);
    }

    @PostMapping
    public Livro cadastrarLivro(@RequestBody Livro livro){
        return service.cadastrarLivro(livro);
    }

    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Integer id){
        service.deletarLivro(id);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro( @PathVariable Integer id, @RequestBody Livro livro){
       return  service.atualizarLivro(id, livro);
    }


}
