import java.util.HashSet;
import java.util.Objects;

public class Trajet{

	public Station depart;
	public Station arrive;
	
	public Ligne ligne;
    
    public Trajet(Ligne ligne_, Station depart_, Station arrive_){
        this.depart = depart_;
        this.arrive = arrive_;
        this.ligne = ligne_;
    }

    public Station getDepart(){
        return this.depart;
    }

    public Station getArrive(){
        return this.arrive;
    }

    public Ligne getLigne(){
        return this.ligne;
    }

    public String toString(){
        String toString = new String();
        toString += "Depart : ";
        toString += this.getDepart();
        toString += "Arrive : ";
        toString += this.getArrive();
        toString += this.getLigne();
        return toString;
    }
    
	@Override
	public boolean equals(Object o_) {
		if (o_ == this) return true;
        if (!(o_ instanceof Station)) {
            return false;
        }
        Trajet trajet = (Trajet) o_;
        return trajet.getArrive().getNom().equals(this.getArrive().getNom())
            && trajet.getDepart().getNom().equals(this.getDepart().getNom())
            && Integer.compare(trajet.getLigne().getNumero(),this.getLigne().getNumero()) == 0;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(this.getLigne().getNumero() 
            + this.getDepart().getNom().hashCode() 
            + this.getArrive().getNom().hashCode());
    }
}
