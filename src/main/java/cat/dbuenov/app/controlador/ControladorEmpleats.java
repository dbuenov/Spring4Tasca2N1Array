package cat.dbuenov.app.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.dbuenov.app.bean.*;
import cat.dbuenov.app.dades.BaseDeDades;
import cat.dbuenov.app.errors.EmpleatNotFoundException;
import cat.dbuenov.app.errors.FeinaNotFoundException;

@RestController
public class ControladorEmpleats {
	
	private final BaseDeDades baseDeDades;
	
	public ControladorEmpleats(BaseDeDades baseDeDades) {
		this.baseDeDades= baseDeDades;
	}
	
	// Mostra tots els empleats
	
	@GetMapping("/empleats")
	public List<Empleat> mostraTots(){
		return baseDeDades.getEmpleats();		
	}
	
	// Crea un empleat

	
	@PostMapping("/empleats")
	public Empleat nouEmpleat(@RequestBody Empleat nouEmpleat) {
		baseDeDades.add(nouEmpleat);
		return nouEmpleat;
				
	}
	
	/*
	@PostMapping("/empleats")
	public Empleat nouEmpleat(@RequestParam(name="nom",required=true) String nom, @RequestParam(name="feina",required=true) String feina) {
		Empleat empleat = new Empleat(nom, new Feina(feina));
		baseDeDades.add(empleat);
		return empleat;
		
	}
	*/
	
	// Mostra un empleat
	
	@GetMapping("/empleats/{id}")
	public Empleat mostraEmpleat(@PathVariable int id) {

		Empleat empleat = baseDeDades.buscaId(id);
		
		if (empleat == null) {
			throw new EmpleatNotFoundException(id);
		}
		return empleat;
	}
	
	// Mostra empleats per feina
	
	@GetMapping("/feines/{feina}")
	public List<Empleat> mostraEmpleatsFeina(@PathVariable String feina){
		
		ArrayList<Empleat> empleatsFeina = baseDeDades.buscaPerFeina(feina);
		
		if (empleatsFeina.size()==0) {
			throw new FeinaNotFoundException(feina);
		}		
		return empleatsFeina; 
		
	}	

	// Actualitza un empleat
	@PutMapping("/empleats/{id}")
	public Empleat canviarEmpleat(@RequestBody Empleat nouEmpleat, @PathVariable int id) {
		
		Empleat empleat = baseDeDades.buscaId(id);
				
		if (empleat != null) {
			empleat.setNom(nouEmpleat.getNom());
			empleat.setFeina(nouEmpleat.getFeina());
		}else {
			baseDeDades.add(nouEmpleat);
		}
		
		return nouEmpleat;	
		
	}

	/*	
	@PutMapping("/empleats/{id}")
	public Empleat canviarEmpleat(	@RequestParam(name="nom",required=true) String nom, 
									@RequestParam(name="feina",required=true) String feina, 
									@PathVariable int id) {
		
		Empleat empleat = baseDeDades.buscaId(id);
		Empleat nouEmpleat = new Empleat(nom, new Feina(feina));
		
		
		if (empleat != null) {
			empleat.setNom(nouEmpleat.getNom());
			empleat.setFeina(nouEmpleat.getFeina());
		}else {
			baseDeDades.add(nouEmpleat);
		}
		
		return nouEmpleat;	
		
	}
	*/

	// Esborra un empleat
	
	@DeleteMapping("/empleats/{id}")
	public void esborraEmpleat(@PathVariable int id) {
		baseDeDades.esborraPerId(id);
	}

}
