package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component
public class CollectionDocente {
	
	private static List<Docente> docentes = new ArrayList<Docente>();
	
	/**
	 * Devuelve un ArrayList de los docentes;
	 * @return
	 */
	
	public static List<Docente> getDocentes() {
		if(docentes.isEmpty()) {
			docentes.add(new Docente(1, "Rebeca Patricia", "Armstrong", "rebecapatricia12@gmail.com", "3886234123"));
			docentes.add(new Docente(2, "Martina", "Armeya", "martinaarmeya145@gmail.com", "3884678123"));
			docentes.add(new Docente(3, "Roberto Martin", "Bautista", "martinbautista78@gmail.com", "3884678987"));
		}
		return docentes;
	}
	
	/**
	 * Agrega el docente enviado por parametro a la ArrayList de docentes
	 * @param docente
	 */
	
	public static void addDocente(Docente docente) {
		docentes.add(docente);
	}
	
	/**
	 * Elimina el usuario enviado por parametro
	 * @param legajo
	 */
	
	public static void deleteDocente(int legajo) {
		boolean encontrado=false;
		Iterator<Docente> iterator = docentes.iterator();
		while(iterator.hasNext() && !encontrado) {
			if(iterator.next().getLegajo()==legajo) {
				iterator.remove();
				encontrado=true;
			}
		}
	}
	
	/**
	 * Modificar el docente enviado por parametro
	 * @param docente
	 */
	public static void changeDocente(Docente docente) {
		boolean encontrado=false;
		for(Docente doc:docentes) {
			if(doc.getLegajo()==docente.getLegajo()) {
				doc.setApellido(docente.getApellido());
				doc.setEmail(docente.getEmail());
				doc.setNombre(docente.getNombre());
				doc.setTelefono(docente.getTelefono());
				encontrado=true;
				break;
		    }
		 }
	     if(!encontrado)
	    	 System.out.println("No existe ninguno docente con el legajo " + docente.getLegajo());
	  }
	
	
	/**
	 * Busca un docente de acuerdo a su legajo y lo retorna si lo encuentra de lo contrario devuelve null
	 * @param legajo
	 * @return
	 */
	
	public static Docente searchDocente(int legajo) {
		Predicate<Docente> filterLegajo = d -> d.getLegajo()==legajo;
		Optional<Docente> docente = docentes.stream().filter(filterLegajo).findFirst();
		if(docente.isPresent()) 
			return docente.get();
		else 
			return null;
	}
	

}
