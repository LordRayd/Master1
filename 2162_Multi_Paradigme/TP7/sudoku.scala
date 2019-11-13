/**
    https://github.com/Package/Sudoku-Scala/blob/master/src/main/scala/Sudoku.scala

*/

class Sudoku(startConfig_ : Array[Array[Int]]){
    private val nbCote : Int = 9
    def solver(): Array[Array[Int]] = {
        var workGrid : Array[Array[Int]] = startConfig_

        def dansLigne(number_ : Int, x_ : Int) : Boolean = {
            workGrid(x_).contains(number_)
        }

        def dansColonne(number_ : Int, y_ : Int) : Boolean = {
            var ret = false
            for(i <- 0 to nbCote - 1){
                if(!ret)
                    ret = (workGrid(i)(y_) == number_)
            }
            ret
        }

        def dansCarre(number_ : Int, x_ : Int, y_ : Int) : Boolean = {
            var ret = false;
            val r = (x_ / 3) * 3
            val c = (y_ / 3) * 3
            for (i <- r to r + 2; j <- c to c + 2) {
                if (workGrid(i)(j) == number_) {
                    ret =  true
                }
            }
            return ret;
        }
        def dejaRemplit(x_ : Int, y_ : Int) : Boolean = {
            workGrid(x_)(y_) != 0
        } 

        def isPossibleAt(number_ : Int, x_ : Int, y_ : Int) : Boolean  = {
            (!dejaRemplit(x_,y_) && !dansLigne(number_,x_) && !dansColonne(number_,y_) && !dansCarre(number_,x_,y_))
        }

        def fillXY(x_ : Int, y_ : Int) : Unit = {

        }
        println("return True  possible 1 en 0 , 2 : " +isPossibleAt(1,0,2))
        println("return False possible 7 en 0 , 2 : " +isPossibleAt(7,0,2))
        println("return False possible 3 en 1 , 1 : " +isPossibleAt(3,1,1))
        println("return False possible 6 en 1 , 6 : " +isPossibleAt(6,1,6))
        return workGrid
    }

    override def toString() : String = {

        var ret : String = "  "
        for (i <- 0 to 8) ret += s"  $i"
        ret += "\n\n"
        for(i <- 0 to 8){
            ret += s"$i  "
            for(j <- 0 to 8){
                var valeur : Int = startConfig_(i)(j)
                ret += s" $valeur "
            }
            ret += "\n"
        }
        return ret
    } 
}

object Main {
    def main(args: Array[String]): Unit ={

        var grille : Array[Array[Int]] = Array(
            Array(5,3,0,0,7,0,0,0,0),
            Array(6,0,0,1,9,5,0,0,0),
            Array(0,9,8,0,0,0,0,6,0),
            Array(8,0,0,0,6,0,0,0,3),
            Array(4,0,0,8,0,3,0,0,1),
            Array(7,0,0,0,2,0,0,0,6),
            Array(6,0,0,0,0,0,2,8,0),
            Array(0,0,0,4,1,9,0,0,5),
            Array(0,0,0,0,8,0,0,7,9)
        )
        var sudo : Sudoku = new Sudoku(grille)
        val t0 = System.currentTimeMillis()
        sudo.solver()
        println(sudo)
        val t1 = System.currentTimeMillis()
        println("Elapsed time: " + (t1 - t0) + "ms")
    }
}