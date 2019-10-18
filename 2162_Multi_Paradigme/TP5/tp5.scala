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

class Echiquier[Piece : ClassTag](cote_  :  Int){

    var plateau : Array[Array[Option[Piece]]] = Array.ofDim[Option[Piece]](cote_,cote_)

    def this() = this(8)

    def placerEn(piece_ : Option[Piece], x_ : Int, y_ : Int) : Unit = this.plateau(x_).update(y_,piece_)

    def update(coupleXY_ : Tuple2[Int,  Int], piece_ : Option[Piece]): Unit = placerEn(piece_, coupleXY_._1, coupleXY_._2)

    def apply(x_ : Int, y_ : Int) : Piece = {
        this.plateau(x_)(y_)
    }

    def apply(coupleXY_ : Tuple2[Int, Int]) : Piece = {
        this.plateau(coupleXY_._1)(coupleXY_._2)
    }
}

trait Piece {

}

class PieceCol(etiquette_ :  String,  codeAnsi_ :  String){
    private var etiquette : String = etiquette_
    private var codeAnsi : String = codeAnsi_
    def this(etiquette_ : String) = this(etiquette_, Ansi.fBlack + Ansi.white)
    override def toString : String =  codeAnsi_ + etiquette_ + Ansi.reset
}

object PieceCol{
    def apply(etiquette_ : String, codeAnsi_ : String ) : PieceCol = new PieceCol(etiquette_, codeAnsi_)
    def apply(etiquette_ : String ) : PieceCol = new PieceCol(etiquette_)
}



object tp5 {

    def main(args_ : Array[String] ):Unit = {
        println("coucou"+Ansi.fBlue+Ansi.red+" abcdef "+Ansi.reset)
        val cavalier = PieceCol("caval", Ansi.red)      
        println(cavalier) 
        //var echiquier = new Echiquier(8)
        //echiquier.placerEn(piece,3,3)

        var valeur: Option[Int] = Some(2)     
        println(valeur) // donne Some(2)     
        val Some(combien) = valeur     
        println(combien) // donne 2     
        valeur = None     
        println(valeur) // donne None
    }
}