package dev.andrenascimento.biblioteca.services;

import java.time.LocalDate;
import java.util.List;

import dev.andrenascimento.biblioteca.models.Cliente;
import dev.andrenascimento.biblioteca.models.Emprestimo;
import dev.andrenascimento.biblioteca.models.Obra;

public class EmprestimoService {

    public Emprestimo novo(Cliente cliente, List<Obra> obras) {
        if (cliente == null){
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        if(obras == null || obras.isEmpty()){
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
}
