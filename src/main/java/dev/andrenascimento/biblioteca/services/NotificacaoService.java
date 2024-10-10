package dev.andrenascimento.biblioteca.services;

import dev.andrenascimento.biblioteca.models.Emprestimo;

public interface NotificacaoService {
    
    void notificar(Emprestimo emprestimo);
}
