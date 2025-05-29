package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired//cria uma instancia de PedidoRepository
	private PedidoRepository pedidoRepository;
	
	@GetMapping //("/home")permite que eu possa escrever localhost:8080/home
	public String home(Model model) {
		//Pedido pedido = new Pedido();
		//pedido.setNomeProduto("Notebook Dell Inspiron I15-I120K-A15P");
		//pedido.setUrlImagem("https://m.media-amazon.com/images/I/71epkJC5SxL._AC_SL1000_.jpg");
		//pedido.setUrlProduto("https://www.amazon.com.br/Notebook-Dell-Inspiron-I15-I120K-A15P-Gera%C3%A7%C3%A3o/dp/B0DCLBBWLV?ref_=Oct_d_obs_d_16364755011_0&pd_rd_w=Wzuko&content-id=amzn1.sym.dad6c099-8e10-4cdc-b06d-8fea4d63d62c&pf_rd_p=dad6c099-8e10-4cdc-b06d-8fea4d63d62c&pf_rd_r=28SYY6D7HW8S58ZWHBGS&pd_rd_wg=Qdj7U&pd_rd_r=9d729a89-95ed-4de9-b54a-a9c8c9cf508d&pd_rd_i=B0DCLBBWLV");
		//pedido.setDescricao("Uma descrição qualquer para esse pedido");
		
		//List<Pedido> pedidos = Arrays.asList(pedido);//fazendo me retornar uma lista com todos os pedidos

		List<Pedido> pedidos = pedidoRepository.findAll();
		model.addAttribute("pedidos", pedidos);//mandei pra view
		
		return "home";
	}

	@GetMapping("/{status}")//permite que eu possa escrever localhost:8080/home
	public String porStatus(@PathVariable("status") String status, Model model) {
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		model.addAttribute("pedidos", pedidos);//mandei pra view
		model.addAttribute("status", status);
		
		return "home";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError(){
		return "redirect:/home";
	}
}
