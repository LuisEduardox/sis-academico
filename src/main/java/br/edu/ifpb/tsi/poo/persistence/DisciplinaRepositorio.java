package br.edu.ifpb.tsi.poo.persistence;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import br.edu.ifpb.tsi.poo.model.Disciplina;

public class DisciplinaRepositorio implements Repositorio<Disciplina>{

    private Map <String, Disciplina> banco = new HashMap<>();

    @Override
    public boolean salvar (Disciplina disciplina){
        if (banco.containsKey(disciplina.getNome())){
            return false;
        }

        banco.put(disciplina.getNome(), disciplina);
        return true;
    }
    
    @Override
    public Disciplina buscar(String Nome){
        return banco.get(Nome);
    }

    @Override
    public List <Disciplina> buscarTodos(){
        return new ArrayList<>(banco.values());
    }

    @Override
    public boolean atualizar(Disciplina disciplina){
        if (!banco.containsKey(disciplina.getNome())){
            return false;
        }

        banco.put(disciplina.getNome(),disciplina);
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

