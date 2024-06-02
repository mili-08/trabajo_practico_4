package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.constantes.Modalidad;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Component
public class CollectionMateria {
	
	private static List<Materia> materias = new ArrayList<Materia>();
	
	/**
	 * Devuelve una ArrayList de materias
	 * @return
	 */

	public static List<Materia> getMaterias() {
		if(materias.isEmpty()) {
			materias.add(new Materia(1, "Programación Visual", "cuatrimestral", 6, Modalidad.VIRTUAL, new Docente(1, "Rebeca Patricia", "Armstrong", "rebecapatricia12@gmail.com", "3886234123"), new Carrera(1, "APU", 3, true)));
			materias.add(new Materia(2, "Base de Datos", "cuatrimestral", 6, Modalidad.VIRTUAL, new Docente(2, "Martina", "Armeya", "martinaarmeya145@gmail.com", "3884678123"), new Carrera(1, "APU", 3, true)));
			materias.add(new Materia(3, "Analisis Matematico", "anual", 6, Modalidad.PRESENCIAL, new Docente(3, "Roberto Martin", "Bautista", "martinbautista78@gmail.com", "3884678987"), new Carrera(2, "Ing. Informatica", 5, false)));
		}
		return materias;
	}
	
	
	/**
	 * Agrega la materia que recibe como parametro
	 * @param materia
	 */
	
	public static void addMateria(Materia materia) {
		materias.add(materia);
	}
	
	/**
	 * Elimina la materia que recibe como parametro
	 * @param codigoMateria
	 */
	
	public static void deleteMateria(int codigoMateria) {
		boolean encontrado=false;
		Iterator<Materia> iterator = materias.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getCodigo()==codigoMateria) {
				iterator.remove();
				encontrado=true;
				break;
			}
		}
		
		if(!encontrado)
		    System.out.println("No existe materia con el codigo " + codigoMateria);
	} 
	
	/**
	 * Modifica la cadena enviada por parametro
	 * @param materia
	 */
	
	public static void changeMateria(Materia materia) {
		boolean encontrado=false;
		for(Materia mate: materias) {
			if(mate.getCodigo()== materia.getCodigo()) {
				mate.setCantHoras(materia.getCantHoras());
				mate.setCarrera(materia.getCarrera());
				mate.setCurso(materia.getCurso());
				mate.setDocente(materia.getDocente());
				mate.setModalidad(materia.getModalidad());
				mate.setNombre(materia.getNombre());
				encontrado=true;
				break;
			}
		}
		
		if(!encontrado)
			System.out.println("No eiste materia con el codigo " + materia.getCodigo());
	}
	
	/**
	 * Busca una materia de acuerdo al parametro que recibe, devuelve la materia si es que la encunetra
	 * de lo contrario devuelve null
	 * @param codigoaMateria
	 * @return
	 */
	
	public static Materia searchMateria(int codigoaMateria) {
		Predicate<Materia> filterCodigo= m -> m.getCodigo()==codigoaMateria;
		Optional<Materia> materia = materias.stream().filter(filterCodigo).findFirst();
		if(materia.isPresent())
			return materia.get();
		else 
			return null;
	}
}
