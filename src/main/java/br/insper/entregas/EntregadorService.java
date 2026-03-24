package br.insper.entregas;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class EntregadorService {

    private HashMap<String, Entregador> entregadores = new HashMap<>();

    // recebe o entregador e o cadastra
    public Entregador cadastraEntregador(Entregador entregador){
        if (entregadores.containsKey(entregador.getDocumento())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entregador ja existe");
        }
        entregadores.put(entregador.getDocumento(), entregador);
        return entregador;
    }

    // lista os entregadores cadastrados
    public Collection<Entregador> listaEntregadors() {
        Collection<Entregador> resposta = new ArrayList<>();
        for (Entregador entregador : entregadores.values()) {
            resposta.add(entregador);
        }
        return resposta;
    }

    // pega um entregador
    public Entregador buscaEntregador(String documento) {
        if (!entregadores.containsKey(documento)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entregador não existe");
        }
        return entregadores.get(documento);
    }

    // troca informações de um entregador especifico
    public Entregador atualizaEntregador(String documento, Entregador entregador) {

        if (!entregadores.containsKey(documento)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Entregador não existe");
        }

        // nome
        if (entregador.getNome() != null){
            entregadores.get(documento).setNome(entregador.getNome());
        }

        // email
        if (entregador.getEmail() != null){
            entregadores.get(documento).setEmail(entregador.getEmail());
        }

        // documento
        if (entregador.getDocumento() != null){
            entregadores.get(documento).setDocumento(entregador.getDocumento());
        }

        // veiculo
        if (entregador.getVeiculo() != null){
            entregadores.get(documento).setVeiculo(entregador.getVeiculo());
        }

        return entregadores.get(documento);
    }


    public void deletarEntregador(String documento) {
        if (!entregadores.containsKey(documento)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entregador não existe");
        }
        entregadores.remove(documento);
    }
}
