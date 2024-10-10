package dev.andrenascimento.biblioteca.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import dev.andrenascimento.biblioteca.enums.Reputacao;
import dev.andrenascimento.biblioteca.enums.Tipo;
import dev.andrenascimento.biblioteca.models.Autor;
import dev.andrenascimento.biblioteca.models.Cliente;
import dev.andrenascimento.biblioteca.models.Obra;

public class EmprestimoServiceTest {

    //private EmprestimoService emprestimoService;

    //Antes de cada teste
    @BeforeEach
    void antesDeCadaMetodo(){
        System.out.println("Antes do método");
    }
    //Depois de cada teste
    @AfterEach
    void depoisDeCadaMetodo(){
        System.out.println("Depois do método");
    }
    //Antes dos testes
    @BeforeAll
    static void antesDaClasse(){
        System.out.println("Antes da classe");
    }
    //Depois dos testes
    @AfterAll
    static void depoisDaClasse(){
        System.out.println("Depois da classe");
    }

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
        // cenário
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

    @Test
    void quandoMetodoNovoForChamadoComObraNulaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenário
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-12", Reputacao.REGULAR);
        var mensagemEsperada = "Obras não podem ser nulas e nem vazias";

        // execução
        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));

        // verificação
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraVaziaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // cenário
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-12", Reputacao.REGULAR);
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
        var emprestimoService = new EmprestimoService();
        var autor = new Autor(1l, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);
        var mensagemEsperada = "Cliente não pode ser nulo";

        // execução
        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));

        // verificação
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
