import scala.{Array => $} 

class Perceptron(couches_ : Array[Int]){

    // pour rester très proche du cours   
    var outputI = (0 until couches_.length).map( i => new Array[Double](couches_(i))).toArray  
    var inputI  = (0 until couches_.length).map( i => new Array[Double](couches_(i))).toArray 
    var dI = (0 until couches_.length).map( i => new Array[Double](couches_(i))).toArray 
    var poids = (1 until couches_.length).map( i => Array.ofDim[Double](couches_(i), couches_(i-1))).toArray

    // f fonction de transfert
    def f(x_ : Double) : Double = 1/(1+Math.exp(-x_))   
    // f'
    def fp(x_ : Double) : Double = f(x_)*(1-f(x_))

    // produit scalaire   
    def prod(p1_ : Array[Double], p2_ : Array[Double]) : Double = {     
        require(p1_.length == p2_.length, "pour le produit les vecteurs doivent avoir la même taille")     
        p1_.zip(p2_).map{ case (a,b) => a*b }.sum   
    }

    // calcul d'erreur quadratique   
    def errQuad(p1_ : Array[Double], p2_ : Array[Double]) : Double = {     
        require(p1_.length == p2_.length, "pour l'erreur quadratique les vecteurs doivent avoir la même taille")     
        p1_.zip(p2_).map{ case (a,b) => (a-b)*(a-b)}.sum   
    }

    def poidsHasard() : Unit = { // met au hasard les poids du réseau     
        for( c <- poids.indices)  // chaque couche       
            for (n <- poids(c).indices)  // chaque neurone         
                for(p <- poids(c)(n).indices) 
                    poids(c)(n)(p) = 1-2*Math.random()   
                    //[-1.0, 1.0]   
    } // poidsHasard

    def retroPropag(observe_ : Array[Double], souhaite_ : Array[Double], pas_ : Double = 0.1): Unit = { // 25 lignes maxi 

    }
    
    def apply(in_ : Array[Double]) : Array[Double] = { // 12 lignes maxi 
        for(i <- in_.indices){
            outputI(0)(i) = in_(i)
        }
        
        for( c <- poids.indices)  // chaque couche       
            for (n <- poids(c).indices)  // chaque neurone         
                for(p <- poids(c)(n).indices) 
                    inputI(c)(p) += poids(c)(n)(p) * outputI(c-1)(n)
        
        outputI(couches_.length - 1)
    }
}

object Perceptron{
    def apply( couches_ : Int* ): Perceptron = {     
        new Perceptron( couches_.toArray )   
    }
    def apply(couches_ : Array[Int]) : Perceptron = {
        new Perceptron(couches_)
    }
}

object Main {
    def main(args: Array[String]): Unit ={
        val perceptron = Perceptron($(4, 1))
        val reponse = perceptron($(1.0, 0.0))
        println(reponse)
    }
}