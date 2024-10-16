package dev.andrenascimento.biblioteca.dao;

import java.util.List;

import dev.andrenascimento.biblioteca.models.Emprestimo;

public interface EmprestimoDAO {
    List<Emprestimo> buscarTodos();
}
