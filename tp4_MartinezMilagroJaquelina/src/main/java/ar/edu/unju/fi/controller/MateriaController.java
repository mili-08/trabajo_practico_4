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
import ar.edu.unju.fi.collections.CollectionDocente;
import ar.edu.unju.fi.collections.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;


@Controller
@RequestMapping("/materia")
public class MateriaController {
	
	@Autowired 
    private Materia materia;
	
	@Autowired 
    private Docente docente;
	
	@Autowired 
    private Carrera carrera;
	
	
	@GetMapping("/listado")
	public String getMateriasPage(Model model) {
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo", "Materias");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		return "materias";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {
		boolean edicion=false;
		model.addAttribute("titulo", "Nueva Materia");
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materia);
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		return "materia";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute ("materia") Materia materia) {
		ModelAndView modelView = new ModelAndView("materias");
		carrera = CollectionCarrera.searchCarrera(materia.getCarrera().getCodigo());
		materia.setCarrera(carrera);
		docente = CollectionDocente.searchDocente(materia.getDocente().getLegajo());
		materia.setDocente(docente);
		String mensaje="";
		boolean exito = CollectionMateria.addMateria(materia);
		if(exito) 
			mensaje="Materia guardada exitosamente !!";
		else 
			mensaje="Materia no se guardo exitosamente";
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		modelView.addObject("materias", CollectionMateria.getMaterias());
		return modelView;
	}
	
	
	@GetMapping("/modificar/{codigo}")
	public String modificarMateriaPage(Model model, @PathVariable(value="codigo") int codigo) {
		Materia materiaEncontrada = new Materia();
		boolean edicion=true;
	    materiaEncontrada = CollectionMateria.searchMateria(codigo);
		model.addAttribute("titulo", "Modificar Materia");
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		return "materia";
	}
	
	
   @PostMapping("/modificar")
   public String modificarMateria(@ModelAttribute("materia") Materia materia, Model model) {
	boolean exito=false;
   	String mensaje="";
	carrera = CollectionCarrera.searchCarrera(materia.getCarrera().getCodigo());
	docente = CollectionDocente.searchDocente(materia.getDocente().getLegajo());
	materia.setCarrera(carrera);
	materia.setDocente(docente);
	try {
		CollectionMateria.changeMateria(materia);
		mensaje="La Materia con codigo " + materia.getCodigo() + " fue modificada exitosamente";
		exito=true;
	} catch (Exception e) {
		mensaje=e.getMessage();
		e.printStackTrace();
	}
	model.addAttribute("mensaje", mensaje);
	model.addAttribute("exito", exito);
	model.addAttribute("materias", CollectionMateria.getMaterias());
	model.addAttribute("titulo", "Materias");
   	return "materias";
   }

   @GetMapping("/eliminar/{codigo}")
   public String eliminarMateria(@PathVariable("codigo") int codigo) {
	CollectionMateria.deleteMateria(codigo);
   	return "redirect:/materia/listado";
   }

}
