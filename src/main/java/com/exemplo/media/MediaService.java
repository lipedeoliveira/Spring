package com.exemplo.media;

import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MediaService {
    
    public static final double NOTA_MINIMA = 7.0;

    public double calcularMedia(List<Double>notas){
        if (notas.isEmpty()){
            throw new IllegalArgumentException("Informe pelo menos uma nota");
        
        }

        double soma = 0;
    
        for (double nota : notas){
            soma += nota;
        }


        return soma / notas.size();

    }

public String situacao(double media) {
    return media >=NOTA_MINIMA ? "AProvado" : "Reprovado" ;
}

}
