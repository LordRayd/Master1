import scala.io.AnsiColor
import scala.reflect.ClassTag
import scala.collection._

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

class Echiquier[P <: Piece: ClassTag](cote_  :  Int = 8){

    private var plateau : Array[Array[Option[P]]] = Array.fill(cote_,cote_)(None)

    def placerEn(piece_ : Option[P], x_ : Int, y_ : Int) : Unit = this.plateau(x_).update(y_,piece_)

    def update(coupleXY_ : Tuple2[Int,  Int], piece_ : P): Unit = this.update(coupleXY_, Some(piece_))

    def update(coupleXY_ : Tuple2[Int,  Int], piece_ : Option[P]): Unit = this.placerEn(piece_, coupleXY_._1, coupleXY_._2)

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

class CavalierEuler(cote_ : Int = 8){
    private var vue : Echiquier[PieceCol] = new Echiquier[PieceCol](cote_)
    private var modele : Array[Array[Int]] = Array.fill(cote_,cote_)(0)
    private val heuristique = Array.fill(cote_, cote_)(0)

    def controleur(x_ : Int, y_ : Int): Unit = {
        def trouveDeplacementsCavalier(xy_ : Tuple2[Int, Int]): List[Tuple2[Int, Int]] = {
            var list : List[Tuple2[Int, Int]] = List()

            list = list:+(xy_._1 -2, xy_._2 + 1)
            list = list:+(xy_._1 -1, xy_._2 + 2)
            list = list:+(xy_._1 +1, xy_._2 + 2)
            list = list:+(xy_._1 +2, xy_._2 + 1)
            list = list:+(xy_._1 -2, xy_._2 - 1)
            list = list:+(xy_._1 -1, xy_._2 - 2)
            list = list:+(xy_._1 +1, xy_._2 - 2)
            list = list:+(xy_._1 +2, xy_._2 - 1)


            list.filter((xy : Tuple2[Int,  Int]) =>  xy._1 >= 0 && xy._1 < cote_  && xy._2 >= 0 && xy._2 < cote_)
                .filter((xy : Tuple2[Int,  Int]) => this.modele(xy._1)(xy._2) == 0)
                .sortBy((xy : Tuple2[Int,  Int]) => heuristique(xy._1)(xy._2))
            
        }

        def trouvePositions(xy_ : Tuple2[Int,  Int], etape_ : Int): Boolean = {
            var positionPossible = trouveDeplacementsCavalier(xy_)
            modele(xy_._1)(xy_._2) = etape_
            if(etape_ == ((cote_ * cote_ )) ){
                return true;
            }
            for( pos <- positionPossible){
                miseAJourHeuristique()
                if(trouvePositions(pos, etape_ + 1)){
                    return true
                }
            }
            modele(xy_._1)(xy_._2) = 0
            return false;
        }

        def synchroniseVueAuModele() : Unit = {
            var end : Int = cote_ - 1
            for(i <- 0 to end; j <- 0 to end){
                var valeur = modele(i)(j)
                vue((i,j)) = PieceCol(valeur.toString)
            }
        }

        def miseAJourHeuristique() : Unit = {
            var end : Int = cote_ - 1
            for(i <- 0 to end; j <- 0 to end){
                heuristique(i)(j) = trouveDeplacementsCavalier((i,j)).size
            }
        }
        
        trouvePositions((x_,y_), 1)
        synchroniseVueAuModele()
    }

    override def toString() : String = {
        vue.toString
    }
}

object Main {
    def main(args: Array[String]): Unit ={
        val euler = new CavalierEuler(6)
        val t0 = System.currentTimeMillis()
        euler.controleur(0,0)
        val t1 = System.currentTimeMillis()
        println(euler) 
        println("Elapsed time: " + (t1 - t0) + "ms")

    }
}