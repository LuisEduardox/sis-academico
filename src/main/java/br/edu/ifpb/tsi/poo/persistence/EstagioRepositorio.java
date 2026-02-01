package br.edu.ifpb.tsi.poo.persistence;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import br.edu.ifpb.tsi.poo.model.Estagio;

public class EstagioRepositorio implements Repositorio<Estagio>{

    private Map <String, Estagio> banco = new HashMap<>();

    @Override
    public boolean salvar (Estagio estagio){
        if (banco.containsKey(estagio.getNome())){
            return false;
        }

        banco.put(estagio.getNome(), estagio);
        return true;
    }
    
    @Override
    public Estagio buscar(String Nome){
        return banco.get(Nome);
    }

    @Override
    public List <Estagio> buscarTodos(){
        return new ArrayList<>(banco.values());
    }

    @Override
    public boolean atualizar(Estagio estagio){
        if (!banco.containsKey(estagio.getNome())){
            return false;
        }

        banco.put(estagio.getNome(), estagio);
        return true;
    }

    @Override
    public boolean excluir(String nome) {
        return banco.remove(nome) != null;
    }

    @Override
    public long conteTodos() {
        return banco.size();
    }

}


