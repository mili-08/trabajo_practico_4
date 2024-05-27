package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {
	
	@GetMapping({"Inicio", "/"})  //Se utiliza {} para disponer dos path a una misma vista
	public String getIndex() {
		return "index";
	}
	

}
