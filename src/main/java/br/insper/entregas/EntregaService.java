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
    public Entrega cadastraEntrega(EntregaDTO entregaDTO){

        // falta de informacoes
        if (entregaDTO.getCliente() == null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Não é possivel adicionar uma entrega sem um cliente atrelado");
        }
        if (entregaDTO.getDataSolicitacao() == null){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Não é possivel adicionar uma entrega sem uma data de solicitação atrelada");
        }


        // conflitos de existencia
        if (entregas.containsKey(entregaDTO.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entrega ja existe");
        }
        if (entregaDTO.getCliente().getAtivo() == false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não existe");
        }

        // se existe um entregador ativo, colocamos ele para realizar a entrega, se nao retornamos um erro
        if(entregadorService.listaEntregadors().size() != 0){
            Entrega entrega = new Entrega(entregaDTO.getId(), entregaDTO.getDataSolicitacao(), entregaDTO.getCliente(), entregaDTO.isAtivo(),  entregadorService.listaEntregadors().getFirst());
            entregas.put(entrega.getId(), entrega);
            return entrega;
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existem entregadores ativos para fazer a sua entrega no momento");
        }


    }

    // lista os entregas cadastrados
    public Collection<Entrega> listaEntregas() {
        Collection<Entrega> resposta = new ArrayList<>();
        for (Entrega entrega : entregas.values()) {
            if (entrega.isAtivo() == true){
                resposta.add(entrega);
            }
        }
        return resposta;
    }

    // pega uma entrega
    public Entrega buscaEntrega(String id) {
        if (!entregas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entrega não existe");
        }
        if (entregas.get(id).isAtivo() == false){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"entrega não existe");
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

        // ativo
        if (entrega.isAtivo() != null){
            entregas.get(id).setAtivo(entrega.isAtivo());
        }

        return entregas.get(id);
    }


    public void deletarEntrega(String id) {
        if (!entregas.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não existe");
        }
        buscaEntrega(id).setAtivo(false);
    }
}
