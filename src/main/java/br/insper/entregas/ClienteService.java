package br.insper.entregas;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class ClienteService {
    private HashMap<String, Cliente> clientes = new HashMap<>();

    // recebe o cliente e o cadastra
    public Cliente cadastraCliente(Cliente cliente){
        if (clientes.containsKey(cliente.getCpf())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Cliente ja existe");
        }
        clientes.put(cliente.getCpf(), cliente);
        return cliente;
    }

    // lista os clientes cadastrados
    public Collection<Cliente> listaClientes() {
        Collection<Cliente> resposta = new ArrayList<>();
        for (Cliente cliente : clientes.values()) {
            resposta.add(cliente);
        }
        return resposta;
    }

    // pega um cliente
    public Cliente buscaCliente(String id) {
        if (!clientes.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não existe");
        }
        return clientes.get(id);
    }

    // troca informações de um cliente especifico
    public Cliente atualizaCliente(String cpf, Cliente cliente) {

        if (!clientes.containsKey(cpf)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não existe");
        }

        // nome
        if (cliente.getNome() != null){
            clientes.get(cpf).setNome(cliente.getNome());
        }

        // email
        if (cliente.getEmail() != null){
            clientes.get(cpf).setEmail(cliente.getEmail());
        }

        // cpf
        if (cliente.getCpf() != null){
            clientes.get(cpf).setCpf(cliente.getCpf());
        }

        // telefone
        if (cliente.getTelefone() != null){
            clientes.get(cpf).setTelefone(cliente.getTelefone());
        }

        return clientes.get(cpf);
    }


    public void deletarCliente(String id) {
        if (!clientes.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não existe");
        }
        clientes.remove(id);
    }
}
