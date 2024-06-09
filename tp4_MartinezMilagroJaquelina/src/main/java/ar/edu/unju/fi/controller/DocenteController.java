package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.model.Docente;



@Controller
@RequestMapping ("/docente")
public class DocenteController {
	
	 @Autowired 
     private Docente docente;
	
	@GetMapping("/listado")
	public String getDocentesPage(Model model) {
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("titulo", "Docentes");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		return "docentes";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaDocentePage(Model model) {
		boolean edicion=false;
		model.addAttribute("titulo", "Nueva Docente");
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docente);
		return "docente";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView guardarDocente(@ModelAttribute ("docente") Docente docente) {
		ModelAndView modelView = new ModelAndView("docentes");
		CollectionDocente.addDocente(docente);
		modelView.addObject("docentes", CollectionDocente.getDocentes());
		return modelView;
	}
	
	
	@GetMapping("/modificar/{legajo}")
	public String modificarDocentePage(Model model, @PathVariable(value="legajo") int legajo) {
		Docente docenteEncontrado = new Docente();
		boolean edicion=true;
		docenteEncontrado = CollectionDocente.searchDocente(legajo);
		model.addAttribute("titulo", "Modificar Docente");
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteEncontrado);
		return "docente";
	}
	
	
    @PostMapping("/modificar")
    public String modificarDocente(@ModelAttribute("docente") Docente docente) {
    	CollectionDocente.changeDocente(docente);
    	return "redirect:/docente/listado";
    }

    @GetMapping("/eliminar/{legajo}")
    public String eliminarDocente(@PathVariable("legajo") int legajo) {
    	CollectionDocente.deleteDocente(legajo);
    	return "redirect:/docente/listado";
    }
	

}
