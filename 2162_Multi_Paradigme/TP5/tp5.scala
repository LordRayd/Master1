import scala.io.AnsiColor
import scala.reflect.ClassTag

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

class Echiquier[P <: Piece: ClassTag](cote_  :  Int){

    private var plateau : Array[Array[Option[P]]] = Array.ofDim[Option[P]](cote_,cote_)

    for( i <- 0 to cote_ -1 ; j <- 0 to cote_ -1) this.vider(i,j)

    def this() = this(8)

    def placerEn(piece_ : Option[P], x_ : Int, y_ : Int) : Unit = this.plateau(x_).update(y_,piece_)

    def update(coupleXY_ : Tuple2[Int,  Int], piece_ : P): Unit = this.placerEn(Some(piece_), coupleXY_._1, coupleXY_._2)

    def vider(x_ : Int, y_ : Int) : Unit = this.placerEn(None,x_,y_)

    def apply(x_ : Int, y_ : Int) : Option[P] = this.plateau(x_)(y_)

    def apply(coupleXY_ : Tuple2[Int, Int]) : Option[P] = this.plateau(coupleXY_._1)(coupleXY_._2)

    override def toString() : String = {
        var ret : String = "";
        for (i <- 0 to cote_ -1) { ret += " " * 5 + i}
        ret += "\n  " + Ansi.fBlue
        ret += " " * 6 * cote_ + " "
        ret += Ansi.reset + "\n" 
        for(i <- 0 to cote_ -1){
            ret += s"$i "+ Ansi.fBlue +" ";
            for(j <- 0 to cote_ -1){
                if(this.plateau(i)(j) == None){
                    ret += Ansi.fBlack + Ansi.white + " " * 5;
                } else {
                    val Some(piece) = this.plateau(i)(j)
                    ret += Ansi.fBlack + Ansi.white + s"$piece";
                }
                ret += Ansi.fBlue+ " "
            }
            ret += Ansi.reset + "\n" + "  " + Ansi.fBlue
            ret += " " * 6 * cote_ + " "
            ret += Ansi.reset + "\n"
        }
        return ret
    }
}

trait Piece {
    def length: Int // donne la longueur du texte de la piece (hors codes ANSI) 
}

class PieceCol(etiquette_ :  String,  codeAnsi_ :  String) extends Piece{
    private var etiquette : String = etiquette_
    private var codeAnsi : String = codeAnsi_
    def this(etiquette_ : String) = this(etiquette_, Ansi.fBlack + Ansi.white)
    override def toString : String = {
        var ret = codeAnsi_ 
        var etiq = etiquette_.slice(0,this.length)
        etiq += " " * (this.length - etiq.length)
        ret += etiq + Ansi.reset
        ret
    } 
    def length : Int = 5
}

object PieceCol{
    def apply(etiquette_ : String, codeAnsi_ : String ) : PieceCol = new PieceCol(etiquette_, codeAnsi_)
    def apply(etiquette_ : String ) : PieceCol = new PieceCol(etiquette_)
}

object Cavalier{
    def apply() : PieceCol = PieceCol("Caval", Ansi.yellow)
}

object Dame{
    def apply() : PieceCol = PieceCol("Dame", Ansi.red)
}

object Fou{
    def apply() : PieceCol = PieceCol("Fou", Ansi.green)
}

object Pion{
    def apply() : PieceCol = PieceCol("Pion", Ansi.cyan)
}

object Rien{
    def apply() = None 
}

object Main {   
    def main(args: Array[String]): Unit ={     
        val echiquier = new Echiquier[PieceCol]()     
        echiquier((0, 6)) = Cavalier()   // indice : utilisez un object Cavalier et un apply()     
        echiquier((3, 5)) = Dame()     
        echiquier((6, 3)) = Fou()     
        echiquier((5, 2)) = Pion()     
        //echiquier((5, 2)) = Rien() // on vide la case     
        print(echiquier)     
        println(echiquier(5,2))     
        println(echiquier(3,5))
    }
}