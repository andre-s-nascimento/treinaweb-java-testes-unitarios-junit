package dev.andrenascimento.biblioteca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import dev.andrenascimento.biblioteca.enums.Reputacao;
import dev.andrenascimento.biblioteca.enums.Tipo;
import dev.andrenascimento.biblioteca.models.Autor;
import dev.andrenascimento.biblioteca.models.Cliente;
import dev.andrenascimento.biblioteca.models.Obra;

public class EmprestimoServiceTest {
    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        // cenário
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-12", Reputacao.REGULAR);
        var autor = new Autor(1l, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(cliente, emprestimo.getCliente());
        assertEquals(List.of(obra), emprestimo.getObras());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRuimDeveRetornarUmEmprestimoComDevolucaoParaUmDias() {
        // cenario
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-12", Reputacao.RUIM);
        var autor = new Autor(1l, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRegularDeveRetornarUmEmprestimoComDevolucaoParaTresDias() {
        // cenário
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-12", Reputacao.REGULAR);
        var autor = new Autor(1l, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoBoaDeveRetornarUmEmprestimoComDevolucaoParaCincoDias() {
        // cenário
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-12", Reputacao.BOA);
        var autor = new Autor(1l, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }
}
