package br.com.agendaDois.agendaDois.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.agendaDois.agendaDois.model.Cadastro;
import br.com.agendaDois.agendaDois.repository.CadastroRepository;

@Controller
public class CadastroController {
	

    @Autowired
    private CadastroRepository cadastroRepository;
    
    /*
     *  @GetMapping("/cadastro")
     *  public String cadastro(){
     *  return "cadastro";   }*/
    
    @GetMapping("/cadastro")
      public String cadastro(){
      return "cadastro";   }
    
   /*O código Abaixo não é necessario por que o index controller já faz isso
    *  @GetMapping(value = "/cadastro")
    public String mostrarCadastro(Model model) {
    	model.addAttribute("cadastros",cadastroRepository.findAll());
    	return "cadastro";
    }*/

   @PostMapping(value = "/cadastro")
   public String salvarCadastro(Cadastro cadastro) {
	   cadastroRepository.save(cadastro);
	   return "redirect:/cadastro"; // redireciona/aponta para a tela cadastro 
   }    
   
}
