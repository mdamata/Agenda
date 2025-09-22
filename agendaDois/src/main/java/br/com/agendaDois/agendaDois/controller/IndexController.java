package br.com.agendaDois.agendaDois.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.agendaDois.agendaDois.model.Cadastro;
import br.com.agendaDois.agendaDois.repository.CadastroRepository;


@Controller
public class IndexController {
	
	/*@GetMapping("/")
	public String index() {
		return "index"; // caso não fosse listar os dados somente á pagina sem listar
	}*/
	
	@Autowired
	public CadastroRepository cadastroRepository;
	
	@GetMapping("/")
	public String index(Model model) {
	    model.addAttribute("cadastros", cadastroRepository.findAll());
	    return "index"; // exibe á pagina e ao mesmo tempo lista os dados
	}
	
	@PostMapping("/excluirCadastro/{id}")
	public String excluirCadastro(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
		try {
			Cadastro cadastro = cadastroRepository.findById(id).orElse(null);
			if(cadastro != null) {
				cadastroRepository.delete(cadastro);
				redirectAttributes.addFlashAttribute("mensagem","Funcionário excluido com sucesso");
			} else {
				redirectAttributes.addFlashAttribute("erro","Funcionário não encontrado!");
			}
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("erro","Erro ao excluir funcionario: "+e.getMessage());
		}
		return "redirect:/";
	}

	@GetMapping("/editarContato/{id}")
	public String editarContato(@PathVariable("id") long id, Model model) {
		Cadastro cadastro = cadastroRepository.findById(id).orElse(null);
		if (cadastro != null) {
			model.addAttribute("cadastro", cadastro);
			return "editarContato";
		}
		return "redirect:/";
		
	}
	@PostMapping("/atualizarCadastro{id}")
	public String atualizarcadastro(@PathVariable("id") long id, Cadastro cadastroAtualizado) {
		return atualizarcadastro(id, cadastroAtualizado);
	
		

	}
	@PostMapping("/atualizarCadastro/{id}")
	public String atualizarcadastro(@PathVariable("id") long id, Cadastro cadastroAtualizado, RedirectAttributes redirectAttributes) {
	    try {
	        // Buscando o cadastro no banco de dados pelo ID
	        Cadastro cadastroExistente = cadastroRepository.findById(id).orElse(null);

	        if (cadastroExistente != null) {
	            // Atualizando os dados do cadastro
	            cadastroExistente.setNome(cadastroAtualizado.getNome());
	            cadastroExistente.setEmail(cadastroAtualizado.getEmail());
	            cadastroExistente.setTelefone(cadastroAtualizado.getTelefone());
	            cadastroExistente.setCargo(cadastroAtualizado.getCargo());

	            // Salvando o cadastro atualizado no banco
	            cadastroRepository.save(cadastroExistente);

	            // Adicionando uma mensagem de sucesso
	            redirectAttributes.addFlashAttribute("mensagem", "Cadastro atualizado com sucesso!");

	        } else {
	            // Caso o cadastro não exista
	            redirectAttributes.addFlashAttribute("erro", "Cadastro não encontrado!");
	        }
	    } catch (Exception e) {
	        // Caso ocorra algum erro
	        redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar cadastro: " + e.getMessage());
	    }

	    // Redirecionando para a página inicial após a atualização
	    return "redirect:/";
	}

}
