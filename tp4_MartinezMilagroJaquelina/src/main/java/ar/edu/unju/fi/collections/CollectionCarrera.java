package ar.edu.unju.fi.collections;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Carrera;

@Component
public class CollectionCarrera {
	
	private static List<Carrera> carreras = new ArrayList<Carrera>();
	
	/**
	 * @return una ArrayList de carreras
	 */
	
	public static List<Carrera> getCarreras() {
		if(carreras.isEmpty()) {
			carreras.add(new Carrera(1, "APU", 3, true));
			carreras.add(new Carrera(2, "Ing. Informatica", 5, false));
			carreras.add(new Carrera(3, "Ing. Quimica", 5,true));
		}
		return carreras;
	}
	
	/**
	 * Agrega al ArrayList la carrera que recibe como parametro
	 * @param carrera
	 */
	
	public static void addCarrera(Carrera carrera) {
		carreras.add(carrera);
	}
	
	/**
	 * Elimina el objeto carrera del ArrayList dependiendo del codigo que recibe como parametro
	 * @param codigoCarrera
	 */
	
	public static void deleteCarrera(int codigoCarrera) {
		Iterator<Carrera> iterator = carreras.iterator();
		boolean encontrado=false;
		while(iterator.hasNext() && !encontrado) {
			if(iterator.next().getCodigo()==codigoCarrera) {
				iterator.remove();
				encontrado=true;
			}
		}
		if(!encontrado)
			System.out.println("No existe el carrera con el codigo" + codigoCarrera);
	}
	
	/**
	 * Modifica un objeto del ArrayList de acuerdo a la carrera enviada por parametro
	 * @param carrera
	 */
	
	
	public static void changeCarrera(Carrera carrera) {
		boolean encontrado=false;
		for(Carrera carre : carreras) {
			if(carre.getCodigo() == carrera.getCodigo()) {
				carre.setNombre(carrera.getNombre());
				carre.setCantAños(carrera.getCantAños());
				carre.setEstado(carrera.isEstado());
				encontrado=true;
				break;	
			}
		}
		if(!encontrado)
			System.out.println("No existe el carrera con el codigo" + carrera.getCodigo());
	}
	
	/**
	 * Busca una carrera por su codigo y deveuelve la carrera si la encuentra de lo contrario 
	 * devuelve null
	 * @param codigoCarrera
	 * @return
	 */
	
	public static Carrera searchCarrera(int codigoCarrera) {
	    Predicate<Carrera> filterCodigo = c -> c.getCodigo() == codigoCarrera;
	    Optional<Carrera> carrera= carreras.stream().filter(filterCodigo).findFirst(); 
	    if(carrera.isPresent()) 
	        return carrera.get();
	    else 
	        return null;
	}

}
