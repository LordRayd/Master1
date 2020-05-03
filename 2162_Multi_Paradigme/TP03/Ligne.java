import java.util.Objects;

public class Ligne{

		public int numero;
		
		public Ligne(int numero_) {
			this.numero = numero_;
		}

		public int getNumero() {
			return this.numero;
		}

		public void setNumero(int numero_) {
			this.numero = numero_;
		}
		
		@Override
		public boolean equals(Object o_) {
			if (o_ == this) return true;
	        if (!(o_ instanceof Ligne)) {
	            return false;
	        }
	        Ligne ligne = (Ligne) o_;
	        return ligne.getNumero() == this.numero;
		}
		
		@Override
	    public int hashCode() {
	        return Objects.hash(this.numero);
	    }
		
		public String toString() {			
			return new String("Ligne n°: "+this.numero);
		}
}
