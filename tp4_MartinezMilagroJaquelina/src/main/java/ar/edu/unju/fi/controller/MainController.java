package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {
	
	@GetMapping("Inicio")  
	public String getIndexPage(Model model) {
		model.addAttribute("titulo", "Inicio");
		return "index";
	}
}
