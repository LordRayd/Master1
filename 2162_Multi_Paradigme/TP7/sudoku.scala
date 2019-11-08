class Sudoku(startConfig_ : Array[Array[Int]]){

    def solver(): Array[Array[Int]] = {
        var workGrid : Array[Array[Int]] = startConfig_

        def isPossibleAt(number_ : Int, x_ : Int, y_ : Int) : Boolean  = {
            var ret : Boolean = false
            //ret = (workGrid(_)(y_).exists(number_) || workGrid(x_)(_).exists(number_))
            return ret
        }

        def fillXY(x_ : Int, y_ : Int) : Unit = {

        }
        
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
        println(sudo)
        val t1 = System.currentTimeMillis()
        println("Elapsed time: " + (t1 - t0) + "ms")
    }
}