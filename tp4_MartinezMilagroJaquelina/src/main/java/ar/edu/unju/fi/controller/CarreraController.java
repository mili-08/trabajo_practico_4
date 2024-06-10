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

import ar.edu.unju.fi.collections.CollectionCarrera;
import ar.edu.unju.fi.model.Carrera;


@Controller
@RequestMapping("/carrera")
public class CarreraController {
	
     @Autowired 
     private Carrera carrera;
	
	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		return "carreras";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion=false;
		model.addAttribute("titulo", "Nueva Carrera");
		model.addAttribute("edicion", edicion);
		carrera.setEstado(true);   //Se setea el estado de la carrera en true para que el radio del input se marque por defecto como activa                
		model.addAttribute("carrera", carrera);
		return "carrera";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute ("carrera") Carrera carrera) {
		ModelAndView modelView = new ModelAndView("carreras");
		carrera.setEstado(true);
		String mensaje="";
		boolean exito = CollectionCarrera.addCarrera(carrera);
		if(exito) 
			mensaje="Carrera guardada exitosamente !!";
		else 
			mensaje="Carrera no se guardo exitosamente";
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		modelView.addObject("carreras", CollectionCarrera.getCarreras());
		return modelView;
	}
	
	
	@GetMapping("/modificar/{codigo}")
	public String modificarCarreraPage(Model model, @PathVariable(value="codigo") int codigo) {
		Carrera carreraEncontrada = new Carrera();
		boolean edicion=true;
		carreraEncontrada = CollectionCarrera.searchCarrera(codigo);
		model.addAttribute("titulo", "Modificar Carrera");
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontrada);
		return "carrera";
	}
	
	
    @PostMapping("/modificar")
    public String modificarCarrera(@ModelAttribute("carrera") Carrera carrera, Model model) {
    	boolean exito=false;
    	String mensaje="";
    	try {
    		CollectionCarrera.changeCarrera(carrera);
    		mensaje="La carrera con codigo " + carrera.getCodigo() + " fue modificada exitosamente";
    		exito=true;
		} catch (Exception e) {
			mensaje=e.getMessage();
			e.printStackTrace();
		}
    	model.addAttribute("mensaje", mensaje);
    	model.addAttribute("exito", exito);
    	model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Carreras");
    	return "carreras";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarCarrera(@PathVariable("codigo") int codigo) {
    	CollectionCarrera.deleteCarrera(codigo);
    	return "redirect:/carrera/listado";
    }
}
