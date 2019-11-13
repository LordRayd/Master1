/**
    https://github.com/Package/Sudoku-Scala/blob/master/src/main/scala/Sudoku.scala

*/

class Sudoku(startConfig_ : Array[Array[Int]]){
    private val nbCote : Int = 9
    def solver():  Array[Array[Int]] = {
        var matriceSuddoku : List[Array[Array[Int]]] = List()

        var workGrid : Array[Array[Int]] = startConfig_.clone

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
            val ligne = (x_ / 3) * 3
            val colonne = (y_ / 3) * 3
            for (i <- ligne to ligne + 2; j <- colonne to colonne + 2) {
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

        def estFinie() : Boolean = {
            var ret : Boolean = true
            for (i <- 0 to nbCote - 1; j <- 0 to nbCote - 1) {
                if(workGrid(i)(j) == 0) {
                    ret =  false
                }
            }
            return ret
        }

        def fillXY(x_ : Int, y_ : Int) : Unit = {
            val ini : Array[Array[Int]] = workGrid.clone
            var ret : Array[Array[Int]] = Array.fill(nbCote,nbCote)(0)
            if(x_ == nbCote-1 && y_ == nbCote-1){
                if(!dejaRemplit(x_,y_)){
                    for(i <- 1 to nbCote){
                        if(isPossibleAt(i,x_,y_)){
                            workGrid(x_)(y_) = i
                        }
                    }
                }
                ret = workGrid.clone
            } else {
                var nextX : Int = x_
                var nextY : Int = y_
                if(y_ < 8){
                    nextX = x_
                    nextY = y_ + 1
                }else if(x_ < 8){
                    nextX = x_ + 1
                    nextY = 0
                }
                if(!dejaRemplit(x_,y_)){
                    for(i <- 1 to nbCote){
                        if(isPossibleAt(i,x_,y_)){
                            workGrid(x_)(y_) = i
                            fillXY(nextX,nextY)
                            if(estFinie()){
                                ret = workGrid.clone
                            }else{
                                workGrid(x_)(y_) = 0
                            }
                        }
                    }
                }else{
                    fillXY(nextX,nextY)
                    if(estFinie()){
                        ret = workGrid.clone
                    }
                }
            }
            if(estFinie()){
                workGrid = ret.clone
            }else{
                workGrid = ini.clone
            }
        }
        fillXY(0,0)
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
            Array(0,6,0,0,0,0,2,8,0),
            Array(0,0,0,4,1,9,0,0,5),
            Array(0,0,0,0,8,0,0,7,9)
        )
        /*var grilleAlmostDone : Array[Array[Int]] = Array(
            Array(5,3,4,6,7,8,9,1,2),
            Array(6,7,2,1,9,5,3,4,8),
            Array(1,9,8,3,4,2,5,6,7),
            Array(8,5,9,7,6,1,4,2,3),
            Array(4,2,6,8,5,3,7,0,1),
            Array(7,1,3,9,2,4,8,5,6),
            Array(9,6,1,5,3,7,2,8,4),
            Array(2,8,7,4,1,9,6,3,5),
            Array(3,4,5,2,8,6,1,7,9)
        )*/
        var sudo : Sudoku = new Sudoku(grille)
        val t0 = System.currentTimeMillis()
        var soluce : Array[Array[Int]] = sudo.solver()
        for(i <- 0 to 8){
            for(j <- 0 to 8){
                print(soluce(i)(j))
            }
            print("\n")
        }
        //println(sudo)
        val t1 = System.currentTimeMillis()
        println("Elapsed time: " + (t1 - t0) + "ms")
    }
}