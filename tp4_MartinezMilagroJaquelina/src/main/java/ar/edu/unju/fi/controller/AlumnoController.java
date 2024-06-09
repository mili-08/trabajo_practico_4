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

import ar.edu.unju.fi.collections.CollectionAlumno;
import ar.edu.unju.fi.model.Alumno;



@Controller
@RequestMapping("alumno")

public class AlumnoController {
	@Autowired 
    private Alumno alumno;
	
	@GetMapping("/listado")
	public String getAlumnosPage(Model model) {
		model.addAttribute("alumnos", CollectionAlumno.getAlumnos());
		model.addAttribute("titulo", "Alumnos");
		model.addAttribute("exito", false);
		model.addAttribute("mensaje", "");
		return "alumnos";
	}
	
	@GetMapping("/nuevo")
	public String getNuevoAlumnoPage(Model model) {
		boolean edicion=false;
		model.addAttribute("titulo", "Nueva Alumno");
		model.addAttribute("edicion", edicion);               
		model.addAttribute("alumno", alumno);
		return "alumno";
	}
	
	
	@PostMapping("/guardar")
	public ModelAndView guardarAlumno(@ModelAttribute ("alumno") Alumno alumno) {
		ModelAndView modelView = new ModelAndView("alumnos");
		/*carrera.setEstado(true);*/
		CollectionAlumno.addAlumno(alumno);
		modelView.addObject("alumnos", CollectionAlumno.getAlumnos());
		return modelView;
	}
	
	//creo que el codigo es la lu
	@GetMapping("/modificar/{lu}")
	public String modificarAlumnoPage(Model model, @PathVariable(value="lu") int lu) {
		Alumno alumnoEncontrado = new Alumno();
		boolean edicion=true;
		alumnoEncontrado=CollectionAlumno.searchAlumno(lu);
		model.addAttribute("titulo", "Modificar Carrera");
		model.addAttribute("edicion", edicion);
		model.addAttribute("alumno", alumnoEncontrado);
		return "alumno";
	}
	
	
   @PostMapping("/modificar")
   public String modificarAlumno(@ModelAttribute("alumno") Alumno alumno) {
	CollectionAlumno.changeAlumno(alumno);
   	return "redirect:/alumno/listado";
   }

   @GetMapping("/eliminar/{lu}")
   public String eliminarAlumno(@PathVariable("lu") int lu) {
	CollectionAlumno.deleteAlumno(lu);
   	return "redirect:/alumno/listado";
   }
}
