package br.insper.entregas;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class EntregadorController {

    @Autowired
    private EntregadorService entregadorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/entregadores")
    public Entregador postEntregadores(@Valid @RequestBody Entregador entregador){
        return entregadorService.cadastraEntregador(entregador);
    }

    @GetMapping("/entregadores")
    public Collection<Entregador> getEntregadors(){
        return entregadorService.listaEntregadors();
    }

    @GetMapping("/entregadores/{documento}")
    public Entregador getEntregador(@PathVariable String documento){
        return entregadorService.buscaEntregador(documento);
    }

    @PutMapping("/entregadores/{documento}")
    public Entregador updateEntregador(@PathVariable String documento ,@RequestBody Entregador entregador){
        return entregadorService.atualizaEntregador(documento, entregador);
    }

    // statue 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/entregadores/{documento}")
    public void deleteEntregador(@PathVariable String documento) {
        entregadorService.deletarEntregador(documento);
    }

}
