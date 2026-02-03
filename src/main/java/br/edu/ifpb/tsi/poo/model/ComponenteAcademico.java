package br.edu.ifpb.tsi.poo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class ComponenteAcademico{
    protected static final float MEDIA_MINIMA_APROVACAO = 70;
    @EqualsAndHashCode.Include
    private String nome;
    private int cargaHoraria;

    public abstract float calcularMedia();

}
