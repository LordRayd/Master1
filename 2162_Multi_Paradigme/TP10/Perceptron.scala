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
    }
    poidsHasard()

    def calculErreur(attendu_ : Array[Double], arrivee_ : Array[Double]) : Double = {
        require(attendu_.length == arrivee_.length, "pour l'erreur les vecteurs doivent avoir la même taille")  
        var erreur : Double = 0.0
        for( i <- attendu_.indices)
            erreur = erreur + (attendu_(i) - arrivee_(i))
        erreur
    }

    def erreurTest(observe_ : Double , attendu_ : Array[Double]) : Double = {
        var err : Double = 0.0
        for(i <- attendu_.indices){
            err = err + ( attendu_(i) - observe_)
        }
        err
    }

    /**
     * Je n'ai pas réussit à faire la réétropopagation j'y ai pourtant passé plusieurs heures mais je n'y arrive pas
     * Je comprend la theorie mais n'arrive pas à la mettre en pratique
     */

    def retroPropag(observe_ : Array[Double], souhaite_ : Array[Double], pas_ : Double = 0.1): Unit = { // 25 lignes maxi 
        var observe = observe_
        var iteration : Int = 0
        var erreur = calculErreur(souhaite_, observe)
        while(iteration < 20){
            
            for( i <- poids.length - 1 to 0){
                for(j <- poids(i).length - 1 to 0){
                    erreur = erreurTest(outputI(i)(j), outputI(couches_.length - 1))
                    println(erreur)
                    for(k <- poids(i)(j).length to 0){
                        poids(i)(j)(k) = poids(i)(j)(k) - (pas_ * erreur)
                    }
                    apply(outputI(0))
                }
            }
            

            iteration = iteration + 1
        }
    }

    def affichePoids() : Unit = {
        for( c <- poids.indices)  // chaque couche       
            for (n <- poids(c).indices)  // chaque neurone         
                for(p <- poids(c)(n).indices)
                    println(poids(c)(n)(p))
    }
    
    def apply(in_ : Array[Double]) : Array[Double] = { // 12 lignes maxi 
        for(i <- in_.indices){
            outputI(0)(i) = in_(i)
        }
        
        for( c <- poids.indices; n <- poids(c).indices ){  // chaque couche // chaque neurone
            inputI(c+1)(n) = prod(poids(c)(n), outputI(c))
            outputI(c+1)(n) = f(inputI(c+1)(n))
        }
        outputI(couches_.length - 1)
    }

    def apply(in_ : Array[Double], souhaite_ : Array[Double]) : Array[Double] = {
        retroPropag(apply(in_), souhaite_)
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
        val perceptron = Perceptron($(2, 1))
        //perceptron($(0.0, 0.0), $(0.0)).foreach(println)
        //perceptron($(0.0, 1.0), $(0.0)).foreach(println)
        perceptron($(1.0, 0.0), $(0.0)).foreach(println)
        //perceptron($(1.0, 1.0), $(1.0)).foreach(println)
    }
}
case class X( x_ : Array[Double]) // entree 
case class Y( y_ : Array[Double]) // sortie