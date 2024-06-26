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
	
	public static boolean addDocente(Docente docente) {
		return docentes.add(docente) ? true : false;
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
		
		if(!encontrado)
			System.out.println("No existe docente con ese legajo" + legajo);
	}
	
	/**
	 * Modificar el docente enviado por parametro
	 * @param docente
	 */
	public static void changeDocente(Docente docente) throws Exception {
		boolean encontrado=false;
		try {
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
		    	 throw new Exception ("No existe ninguno docente con el legajo " + docente.getLegajo());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
		
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
