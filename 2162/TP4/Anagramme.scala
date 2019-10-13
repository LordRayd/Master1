import scala.collection.mutable.HashSet
import scala.collection.mutable.StringBuilder

object Anagramme {

    def main(args: Array[String] ):Unit = {
        println("Entrez le mot dont vous souhaitez trouver les anagrammes")
        val search = scala.io.StdIn.readLine()
        val result = trouveAnagrammesRec(search)
        //val result = anagrammeDe(search)
        println("Nombre de mot : " + result.size)
        println("Afficher tout les nombres (y/n)")
        val affiche = scala.io.StdIn.readChar()
        if(affiche == 'y'){
            println("Mots")
            for(e <-  result){println(e)}
        }

    }

    def anagrammeDe(mot_ : String): HashSet[String] = {
        return mot_.permutations.toSet
    }

    def trouveAnagrammesRec(mot_ : String): HashSet[String] = {
        var retour : HashSet[String] = new HashSet[String]()

        var nb = 0
        for(c <- mot_){
            var sb = new StringBuilder(mot_);
            var motRestant = sb.deleteCharAt(nb).toString()
            if(motRestant.length < 1){
                retour += s"$c$motRestant"
                return retour
            }
                
            var rets = trouveAnagrammesRec(motRestant)
            for(ret <- rets){
                retour += s"$c$ret"          
            }
            nb = nb + 1;
        }
        return retour
    }
}