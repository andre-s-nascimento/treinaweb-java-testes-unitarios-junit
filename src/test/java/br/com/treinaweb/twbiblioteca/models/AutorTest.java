package br.com.treinaweb.twbiblioteca.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AutorTest {

    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoNulaDeveRetornarTrue() {

        // cenário
        var autor = new Autor();

        // execução
        var estaVivo = autor.estaVivo();

        // verificação
        //assertEquals(true, estaVivo);
        assertTrue(estaVivo);
    }

    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoPreenchidaDeveRetornarFalse() {

        // cenário
        var autor = new Autor();
        autor.setDataFalecimento(LocalDate.now());

        // execução
        var estaVivo = autor.estaVivo();

        // verificação
        //assertEquals(false, estaVivo);
        assertFalse(estaVivo);

    }
}
