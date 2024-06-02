package ar.edu.unju.fi.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;

@Component
public class CollectionAlumno {
	
	private static List<Alumno> alumnos = new ArrayList<Alumno>();
	
	/**
	 * Devuelve una ArrayList de alumnos
	 * @return
	 */
	
	public static List<Alumno> getAlumnos() {
		if(alumnos.isEmpty()) {
			alumnos.add(new Alumno("44877071", "Milagro Jaquelina", "Martinez", "milijmartinez@gmail.com", "3885123456", LocalDate.of(2003, 8, 3), "Gorriti N° 123 - Monterrico", 5318));
			alumnos.add(new Alumno("43125678", "Juan Pedro", "Torres", "juanpedro123@gmail.com", "3885781902", LocalDate.of(2001, 12, 2), "23 de Agosto N° 34 - Perico", 5234));
			alumnos.add(new Alumno("33981781", "Marina", "Sarocha", "marinasarocha77@gmail.com", "3885980245", LocalDate.of(1998, 8, 8), "San Martin N° 58 - Monterrico", 4819));
		}
		return alumnos;
	}
	
	/**
	 * Agrega al ArrayList el alumno que recibe como parametro
	 * @param alumno
	 */
	
	public static void addAlumno(Alumno alumno) {
		alumnos.add(alumno);
	}
	
	/**
	 * Elimina el objeto alumno del ArrayList de acuerdo al libreta universitaria que recibe como parametro
	 * @param lu
	 */
	
	public static void deleteAlumno(int lu) {
		boolean encontrado=false;
		Iterator<Alumno> iterator = alumnos.iterator();
		while(iterator.hasNext() && !encontrado) {
			if(iterator.next().getLu()==lu) {
				iterator.remove();
				encontrado=true;
			}
		}
		if(!encontrado) 
			System.out.println("No existe alumno con la libreta universitaria" + lu);
	}
	
	/**
	 * Modifica un objeto del ArrayList de acuerdo al alumno enviado por parametro
	 * @param alumno
	 */
	
	public static void changeAlumno(Alumno alumno) {
		for(Alumno alu:alumnos) {
			if(alu.getLu()==alumno.getLu()) {
				alu.setApellido(alumno.getApellido());
				alu.setNombre(alumno.getNombre());
				alu.setDni(alumno.getDni());
				alu.setDomicilio(alumno.getDomicilio());
				alu.setEmail(alumno.getEmail());
				alu.setFechaNac(alu.getFechaNac());
				alu.setTelefono(alumno.getTelefono());
			}else 
				System.out.println("No existe el alumno con esa lu");
		}
	}
	
	/**
	 * Busca un alumno por su libreta universitaria y deveuelve el alumno si lo encuentra de lo contrario 
	 * devuelve null
	 * @param lu
	 * @return
	 */
	
	public static Alumno searchAlumno(int lu) {
		Predicate<Alumno> filterLU = a -> a.getLu() == lu;
		Optional<Alumno> alumno = alumnos.stream().filter(filterLU).findFirst();
		if(alumno.isPresent())
			return alumno.get();
		else 
			return null;
	}
}
