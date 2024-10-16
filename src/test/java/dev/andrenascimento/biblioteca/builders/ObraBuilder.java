package dev.andrenascimento.biblioteca.builders;

import dev.andrenascimento.biblioteca.enums.Tipo;
import dev.andrenascimento.biblioteca.models.Obra;

public class ObraBuilder {
    private Obra obra;

    public static ObraBuilder builder(){
        var builder = new ObraBuilder();

        var autor = AutorBuilder.builder().build();
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);
        builder.obra = obra;

        return builder;
    }

    public Obra build(){
        return obra;
    }
}
