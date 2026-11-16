package com.exemplo.media;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller 
public class MediaController {
    
    private final MediaService service;

    public MediaController(MediaService service){
        this.service = service;
    }

    @GetMapping("/")
    public String Formulario(){
        return "index";
    }

    @PostMapping("/Calcular") 
    public String Calcular(@RequestParam String nome,
        @RequestParam String notas,
        Model model

    ){
        try{
            List<Double> lista = new ArrayList<>();
            for (String parte : notas.split(";")){
                lista.add(Double.parseDouble(parte.trim().replace(",",".")));
            }

            double media = service.calcularMedia(lista);

            model.addAttribute("nome",nome);
            model.addAttribute("media",String.format("%.2f",media));
            model.addAttribute("situacao",service.situacao(media));
        }catch(NumberFormatException e){
            model.addAttribute("Erro",
                "Use apenas numeros separados por ponto e virgula"
            );
        }catch(IllegalArgumentException e ){
            model.addAttribute("Erro",e.getMessage());
        }




        return "index";
    }
}
