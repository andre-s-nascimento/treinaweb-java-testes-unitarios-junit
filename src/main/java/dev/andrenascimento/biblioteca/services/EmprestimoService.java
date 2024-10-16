package dev.andrenascimento.biblioteca.services;

import java.time.LocalDate;
import java.util.List;

import dev.andrenascimento.biblioteca.dao.EmprestimoDAO;
import dev.andrenascimento.biblioteca.models.Cliente;
import dev.andrenascimento.biblioteca.models.Emprestimo;
import dev.andrenascimento.biblioteca.models.Obra;

public class EmprestimoService {

    private EmprestimoDAO emprestimoDAO;
    private NotificacaoService notificacaoService;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, NotificacaoService notificacaoService) {
        this.emprestimoDAO = emprestimoDAO;
        this.notificacaoService = notificacaoService;
    }

    public Emprestimo novo(Cliente cliente, List<Obra> obras) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        if (obras == null || obras.isEmpty()) {
            throw new IllegalArgumentException("Obras não podem ser nulas e nem vazias");
        }
        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        var diasParaDevolucao = cliente.getReputacao().obterDiasParaDevolucao();

        var dataDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);

        emprestimo.setCliente(cliente);
        emprestimo.setObras(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);
        return emprestimo;
    }

    public void notificarAtrasos() {
        var hoje = LocalDate.now();

        var emprestimos = emprestimoDAO.buscarTodos();

        for (Emprestimo emprestimo : emprestimos) {
            var estaAtrasado = emprestimo.getDataDevolucao().isBefore(hoje);
            if (estaAtrasado) {
                notificacaoService.notificar(emprestimo);
            }
        }

    }
}
