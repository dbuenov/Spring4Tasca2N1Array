package cat.dbuenov.app.bean;

import java.util.Objects;

public class Empleat {

	private int id;
	private String nom;
	private Feina feina;
	
	public Empleat(){
		
	}	
	
	public Empleat(String nom, Feina feina) {
		this.nom= nom;
		this.feina= feina;			
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Feina getFeina() {
		return feina;
	}

	public void setFeina(Feina feina) {
		this.feina = feina;		
	}

	@Override
	public int hashCode() {
		return Objects.hash(feina, id, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleat other = (Empleat) obj;
		return Objects.equals(feina, other.feina) && Objects.equals(id, other.id) && Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Empleat2 [id=" + id + ", nom=" + nom + ", feina=" + feina + "]";
	}	
	
}
