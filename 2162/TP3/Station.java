import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Station implements Iterable<Ligne>{

	public String nom;
	
	public Collection<Ligne> lignes;
	
	public Station(String nom_) {
		this.lignes = new HashSet<Ligne>();
		this.nom = nom_;
	}
	
	public void ajouteLigne(Ligne ligne_) { 
        this.lignes.add(ligne_); 
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom_) {
		this.nom = nom_;
	}

	public Collection<Ligne> getLignes() {
		return this.lignes;
	}

	public void setLignes(Collection<Ligne> lignes_) {
		this.lignes = lignes_;
	}
	
	public String toString() {
		String toString = new String("Nom station : "+this.nom+" Ligne : ");
		for(Ligne ligne : this.lignes) {
			toString += ligne.toString()+",";
		}
		return toString;
	}

	@Override
	public Iterator<Ligne> iterator() {
		return this.lignes.iterator();	
	}
	
	@Override
	public boolean equals(Object o_) {
		if (o_ == this) return true;
        if (!(o_ instanceof Station)) {
            return false;
        }
        Station station = (Station) o_;
        return station.getNom().equals(this.nom);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(this.nom);
    }
	
	
}
