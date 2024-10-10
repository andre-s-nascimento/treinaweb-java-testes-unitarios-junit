package dev.andrenascimento.biblioteca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import dev.andrenascimento.biblioteca.builders.ClienteBuilder;
import dev.andrenascimento.biblioteca.builders.ObraBuilder;
import dev.andrenascimento.biblioteca.enums.Reputacao;
import dev.andrenascimento.biblioteca.models.Obra;

public class EmprestimoServiceTest {

    private EmprestimoService emprestimoService;

    // Antes de cada teste
    @BeforeEach
    void setUp() {
        emprestimoService = new EmprestimoService();
    }

    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        // cenário
        var cliente = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();

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
        // cenário
        var cliente = ClienteBuilder.builder().reputacao(Reputacao.RUIM).build();
        var obra = ObraBuilder.builder().build();

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRegularDeveRetornarUmEmprestimoComDevolucaoParaTresDias() {
        // cenário
        var cliente = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoBoaDeveRetornarUmEmprestimoComDevolucaoParaCincoDias() {
        // cenário
        var cliente = ClienteBuilder.builder().reputacao(Reputacao.BOA).build();
        var obra = ObraBuilder.builder().build();

        // execução
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // verificação
        assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraNulaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenário
        var cliente = ClienteBuilder.builder().build();
        var mensagemEsperada = "Obras não podem ser nulas e nem vazias";

        // execução
        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));

        // verificação
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraVaziaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenário
        var cliente = ClienteBuilder.builder().build();
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obras não podem ser nulas e nem vazias";

        // execução
        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, obras));

        // verificação
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenário
        var obra = ObraBuilder.builder().build();
        var mensagemEsperada = "Cliente não pode ser nulo";

        // execução
        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));

        // verificação
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
