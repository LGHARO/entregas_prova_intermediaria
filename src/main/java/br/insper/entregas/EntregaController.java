package br.insper.entregas;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/entregas")
    public Entrega postEntregas(@Valid @RequestBody Entrega entrega){
        return entregaService.cadastraEntrega(entrega);
    }

    @GetMapping("/entregas")
    public Collection<Entrega> getEntregas(){
        return entregaService.listaEntregas();
    }

    @GetMapping("/entregas/{id}")
    public Entrega getEntrega(@PathVariable String id){
        return entregaService.buscaEntrega(id);
    }

    @PutMapping("/entregas/{id}")
    public Entrega updateEntrega(@PathVariable String id ,@RequestBody Entrega entrega){
        return entregaService.atualizaEntrega(id, entrega);
    }

    // statue 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/entregas/{id}")
    public void deleteEntrega(@PathVariable String id) {
        entregaService.deletarEntrega(id);
    }

}
