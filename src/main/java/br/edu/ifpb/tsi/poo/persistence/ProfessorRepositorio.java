package br.edu.ifpb.tsi.poo.persistence;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import br.edu.ifpb.tsi.poo.model.Professor;

public class ProfessorRepositorio implements Repositorio <Professor>{
    
    private Map<String, Professor> banco = new HashMap<>();

    @Override
    public boolean salvar(Professor professor){
        if (banco.containsKey(professor.getMatricula())){
            return false;
        }
        banco.put(professor.getMatricula(), professor);
        return true;
    }

    @Override
    public Professor buscar(String matricula){
        return banco.get(matricula);
    }

    @Override
    public List<Professor> buscarTodos(){
        return new ArrayList<>(banco.values());
    }

    @Override
    public boolean atualizar(Professor professor) {
        if (!banco.containsKey(professor.getMatricula())){
            return false;
        }
        banco.put(professor.getMatricula(), professor);
        return true;
    }

    @Override
    public boolean excluir(String matricula){
        return banco.remove(matricula) != null;
    }

    @Override
    public long conteTodos(){
        return banco.size();
    }

}
