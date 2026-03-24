package br.insper.entregas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.Collection;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clientes")
    public Cliente postClientes(@Valid @RequestBody Cliente cliente){
        return clienteService.cadastraCliente(cliente);
    }

    @GetMapping("/clientes")
    public Collection<Cliente> getClientes(){
        return clienteService.listaClientes();
    }

    @GetMapping("/clientes/{id}")
    public Cliente getCliente(@PathVariable String id){
        return clienteService.buscaCliente(id);
    }

    @PutMapping("/clientes/{id}")
    public Cliente updateCliente(@PathVariable String id ,@RequestBody Cliente cliente){
        return clienteService.atualizaCliente(id, cliente);
    }

    // statue 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/clientes/{id}")
    public void deleteCliente(@PathVariable String id) {
        clienteService.deletarCliente(id);
    }



}
