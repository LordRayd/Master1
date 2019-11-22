import scala.{Array => $}

object Ansi{

    def black : String = "\u001B[30m"
    def fBlack: String = "\u001B[40m"

    def red: String = "\u001B[31m"
    def fRed: String = "\u001B[41m"

    def green: String = "\u001B[32m"
    def fGreen: String = "\u001B[42m"

    def yellow: String = "\u001B[33m"
    def fYellow: String = "\u001B[43m"

    def blue: String = "\u001B[34m"
    def fBlue: String = "\u001B[44m"

    def magenta: String = "\u001B[35m"
    def fMagenta: String = "\u001B[45m"

    def cyan: String = "\u001B[36m"
    def fCyan: String = "\u001B[46m"

    def white: String = "\u001B[37m"
    def fWhite: String = "\u001B[47m"

    def reset : String = "\u001B[0m"
}

class Labyrinthe(init_ : Array[Array[Int]]){
    def getAnsiCode(valeur_ : Int) : String = {
        valeur_ match{
            case 0 => Ansi.fBlack
            case 1 => Ansi.fYellow
            case 9 => Ansi.fRed 
        }
    }
    override def toString = {  
        init_.map( _.map( _ match{
            case 0 => Ansi.fBlack
            case 1 => Ansi.fYellow
            case 9 => Ansi.fRed 
        } ).mkString(" " + Ansi.reset)).mkString(Ansi.reset+ "\n" )
        /*
        e.mkString(
            _ match {
                case 1 => Ansi.fYellow + " " + Ansi.reset
                case 0 => Ansi.fBlack + " " + Ansi.reset
                case 9 => Ansi.fRed + " " + Ansi.reset
            }
        ) ).mkString("\n")*/

        /*
            e match {
               case 0 => e.mkString(Ansi.fBlack + " " + Ansi.reset)
               case 1 => e.mkString(Ansi.fYellow + " " + Ansi.reset)
               case 9 => e.mkString(Ansi.fRed + " "+ Ansi.reset)
            }  ).mkString("\n"))
        //??? 
        // utilisez seulement les opérateurs map, match et mkString (plusieurs fois si nécessaire)  */ 
    } 
    // toString 
    /*def cheminSortieAPartirDe(pos_ : Tuple2[Int, Int], lab_ : Array[Array[Int]] = init_) : Set[Array[Array[Int]]]= {     
        pos_ match {  
            //?????    
        }   
    }*/
}

object Main {
    def main(args: Array[String]): Unit ={
        val t0 = System.currentTimeMillis()

        val laby = new Labyrinthe($( // 1 = mur, 0 = vide et 9 = sortie 
        $(1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1), 
        $(1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1), 
        $(1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1), 
        $(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1), 
        $(1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1), 
        $(1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1), 
        $(1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1), 
        $(1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1) )) 
        
        println(laby)
        val t1 = System.currentTimeMillis()
        println("Elapsed time: " + (t1 - t0) + "ms")
    }
}