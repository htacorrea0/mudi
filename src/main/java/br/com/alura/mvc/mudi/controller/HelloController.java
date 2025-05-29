package br.com.alura.mvc.mudi.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(Model model) {//serve pra pegar os pedidos que o metodo ta retornando e passar pra view
		model.addAttribute("nome", "Mundo");//está adicionando o nome "nome" com o valor "mundo" no request
		return "hello";
	}
}
