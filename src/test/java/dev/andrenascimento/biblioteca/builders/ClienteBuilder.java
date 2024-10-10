package dev.andrenascimento.biblioteca.builders;

import java.time.LocalDate;

import dev.andrenascimento.biblioteca.enums.Reputacao;
import dev.andrenascimento.biblioteca.models.Cliente;

public class ClienteBuilder {
    private Cliente cliente;

    public static ClienteBuilder builder() {
        var builder = new ClienteBuilder();

        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-12", Reputacao.REGULAR);
        builder.cliente = cliente;

        return builder;
    }

    public ClienteBuilder reputacao(Reputacao reputacao) {
        cliente.setReputacao(reputacao);

        return this;
    }

    public Cliente build() {
        return cliente;
    }
}
