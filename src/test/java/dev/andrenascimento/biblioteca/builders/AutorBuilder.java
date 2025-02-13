package dev.andrenascimento.biblioteca.builders;

import java.time.LocalDate;

import dev.andrenascimento.biblioteca.models.Autor;

public class AutorBuilder {
    private Autor autor;

    public static AutorBuilder builder() {
        var builder = new AutorBuilder();

        var autor = new Autor(1l, "Autor Teste", LocalDate.now(), null);
        builder.autor = autor;

        return builder;
    }

    public Autor build() {
        return autor;
    }
}
