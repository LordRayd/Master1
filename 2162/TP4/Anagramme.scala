import scala.collection.mutable.HashSet
import scala.collection.mutable.StringBuilder

object Anagramme {

    def main(args: Array[String] ):Unit = {
        println("Entrez le mot dont vous souhaitez trouver les anagrammes")
        val search = scala.io.StdIn.readLine()
        val result = trouveAnagrammesRec(search)
        println("Nombbre de mot : " + result.size)
        println("Afficher tout les nombres (y/n)")
        val affiche = scala.io.StdIn.readChar()
        if(affiche == 'y'){
            println("Mots")
            for(e <-  result){println(e)}
        }

    }

    def trouveAnagrammesRec(mot: String): HashSet[String] = {
        var retour : HashSet[String] = new HashSet[String]()

        var nb = 0
        for(c <- mot){
            var sb = new StringBuilder(mot);
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