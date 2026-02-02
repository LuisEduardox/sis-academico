package br.edu.ifpb.tsi.poo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ComponenteAcademico{
    protected static final float MEDIA_MINIMA_APROVACAO = 70;
    private String nome;
    private int cargaHoraria;

    public abstract float calcularMedia();

}
