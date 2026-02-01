package br.edu.ifpb.tsi.poo.persistence;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import br.edu.ifpb.tsi.poo.model.Aluno;

public class AlunoRepositorio implements Repositorio<Aluno>{

    private Map <String, Aluno> banco = new HashMap<>();

    @Override
    public boolean salvar (Aluno aluno){
        if (banco.containsKey(aluno.getMatricula())){
            return false;
        }

        banco.put(aluno.getMatricula(), aluno);
        return true;
    }
    
    @Override
    public Aluno buscar(String Matricula){
        return banco.get(Matricula);
    }

    @Override
    public List <Aluno> buscarTodos(){
        return new ArrayList<>(banco.values());
    }

    @Override
    public boolean atualizar(Aluno aluno){
        if (!banco.containsKey(aluno.getMatricula())){
            return false;
        }

        banco.put(aluno.getMatricula(),aluno);
        return true;
    }

    @Override
    public boolean excluir(String matricula) {
        return banco.remove(matricula) != null;
    }

    @Override
    public long conteTodos() {
        return banco.size();
    }

}

    
