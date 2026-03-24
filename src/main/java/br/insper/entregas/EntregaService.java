package br.insper.entregas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class EntregaService {
    private HashMap<String, Entrega> entregas = new HashMap<>();

    @Autowired
    private EntregadorService entregadorService;

    @Autowired
    private ClienteService clienteService;

    // recebe a entrega e a cadastra
    public Entrega cadastraEntrega(Entrega entrega){
        if (entregas.containsKey(entrega.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entrega ja existe");
        }
        if (!clienteService.clientes.containsKey(entrega.getCliente().getCpf())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não existe");
        }
        if (!entregadorService.entregadores.containsKey(entrega.getEntregador().getDocumento())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entregador não existe");
        }
        entregas.put(entrega.getId(), entrega);
        return entrega;
    }

    // lista os entregas cadastrados
    public Collection<Entrega> listaEntregas() {
        Collection<Entrega> resposta = new ArrayList<>();
        for (Entrega entrega : entregas.values()) {
            resposta.add(entrega);
        }
        return resposta;
    }

    // pega uma entrega
    public Entrega buscaEntrega(String id) {
        if (!entregas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entrega não existe");
        }
        return entregas.get(id);
    }

    // troca informações de uma entrega especifica
    public Entrega atualizaEntrega(String id, Entrega entrega) {

        if (!entregas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entrega não existe");
        }

        // id
        if (entrega.getId() != null){
            entregas.get(id).setId(entrega.getId());
        }

        // dataSolicitacao
        if (entrega.getDataSolicitacao() != null){
            entregas.get(id).setDataSolicitacao(entrega.getDataSolicitacao());
        }

        return entregas.get(id);
    }


    public void deletarEntrega(String id) {
        if (!entregas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não existe");
        }
        entregas.remove(id);
    }
}
