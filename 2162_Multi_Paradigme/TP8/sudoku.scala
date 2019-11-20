import scala.{Array => $}

class Sudoku(startConfig_ : Array[Array[Int]]){

    def parcours_1(i_ : Int = 0) : Tuple2[Int, Int] = (i_ / 9, i_ % 9)

    def parcours_2(i_ : Int = 0) : Unit = {       
        parcours_1(i_) match {         
            case(8, 8 ) => println("derniere case")         
            case( x, y) => {           
                println(x + " " + y)           
                parcours_2(i_ +1)         
            }       
        }     
    }

    def parcours_3(i_ : Int = 0) : Unit = {       
        parcours_1(i_) match {         
            case(9, 0) => println("on arrete, cette case ne doit pas être analysée")         
            case( x, y) => {           
                println(x + " " + y)           
                parcours_3(i_ +1)         
            }
        }
    }

    def parcours_5(i_ : Int = 0) : Unit = { 
        parcours_1(i_) match {         
            case(9,0) => println("stop ! une case de trop ")         
            case(x,y) => { 
                println(x + " " + y)      
                def indicesAVoir(j_ : Int) = List((x, j_), (j_,y), (3*(x/3)+j_ / 3, 3*(y/3)+j_ %3))           
                println((0 until 9).flatMap(indicesAVoir).toSet)           
                parcours_5(i_ +1) 
            }        
        }       
    }

    def parcours_7(i_ : Int = 0) : Unit = {       
        parcours_1(i_) match {         
            case(9,0) => println("stop")         
            case(x,y) => {           
                println(x + " " + y)           
                def nombresDejaPris(j_ : Int) = List((x, j_), (j_,y), (3*(x/3)+j_ / 3, 3*(y/3)+j_ %3)).map((x) => startConfig_( x._1)(x._2 ))     
                println("deja pris "+(0 until 9).flatMap(nombresDejaPris).toSet)
                parcours_7(i_ +1)         
            }       
        }     
    } 

    def parcours_9(i_ : Int = 0) : Unit = { 
        parcours_1(i_) match { 
            case(9,0)  => println("derniere case")         
            case(x,y) => {           
                println(x + " " + y)           
                def nombresDejaPris(j_ : Int) = List((x, j_), (j_,y), (3*(x/3)+j_ / 3, 3*(y/3)+j_ %3)).map((x) => startConfig_( x._1)(x._2 ))        
                val dejaPris = (0 until 9).flatMap(nombresDejaPris).toSet 
                val aEssayer = (1 to 9).toSeq.diff(dejaPris.toSeq)          
                println("a essayer : "+aEssayer)           
                parcours_9(i_ +1)         
            }       
        }     
    }


    def parcours_11(t_ : Array[Array[Int]], i_  : Int = 0) : Option[Array[Array[Int]]] = {       
        parcours_1(i_) match { 
            case(9,0)  => Some(t_)       
            case(x,y) => {           
                parcours_11( t_.updated(x,t_(x).updated(y,i_)) , i_ +1)         
            }       
        }     
    } 

    def parcours_12(t_ : Array[Array[Int]], i_  : Int = 0) : Option[Array[Array[Int]]] = {       
        (i_ / 9, i_ % 9) match {         
            case(9, 0) => Some(t_)         
            case(x,y) if t_(x)(y) != 0 => parcours_12(t_, i_ +1)         
            case(x,y) => {           
                def nombresDejaPris(j_ : Int) = List((x, j_), (j_,y), (3*(x/3)+j_ / 3, 3*(y/3)+j_ %3)).map((x) => t_( x._1)(x._2 ))           
                val dejaPris = (0 until 9).flatMap(nombresDejaPris).toSet           
                val aEssayer = (1 to 9).toSeq.diff(dejaPris.toSeq)           
                // si on place le nombre j_ sur la case courante et qu'on avance ?           
                def placer(j_ : Int) = parcours_12( t_.updated(x,t_(x).updated(y,j_)), i_ +1)           
                if (aEssayer.isEmpty) None // pas de solution (rien à essayer)           
                else {             
                    val ts = aEssayer.map(placer).filterNot(_ == None)             
                    if (ts.length > 0) ts(0) 
                    else None           
                }         
            }       
        }     
    }

}

object Main {
    def main(args: Array[String]): Unit ={

        /*var grille : Array[Array[Int]] = Array(
            Array(5,3,0,0,7,0,0,0,0),
            Array(6,0,0,1,9,5,0,0,0),
            Array(0,9,8,0,0,0,0,6,0),
            Array(8,0,0,0,6,0,0,0,3),
            Array(4,0,0,8,0,3,0,0,1),
            Array(7,0,0,0,2,0,0,0,6),
            Array(0,6,0,0,0,0,2,8,0),
            Array(0,0,0,4,1,9,0,0,5),
            Array(0,0,0,0,8,0,0,7,9)
        )*/
        val table = $(
            $(5, 3, 0,  0, 7, 0,  0, 0, 0),
             
            $(6, 0, 0,  1, 9, 5,  0, 0, 0),
            $(0, 9, 8,  0, 0, 0,  0, 6, 0),       
            
            $(8, 0, 0,  0, 6, 0,  0, 0, 3),
            $(4, 0, 0,  8, 0, 3,  0, 0, 1),
            $(7, 0, 0,  0, 2, 0,  0, 0, 6),
            
            $(0, 6, 0,  0, 0, 0,  2, 8, 0),
            $(0, 0, 0,  4, 1, 9,  0, 0, 5),      
            $(0, 0, 0,  0, 8, 0,  0, 7, 9)
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
        var sudo : Sudoku = new Sudoku(table)
        val t0 = System.currentTimeMillis()
        //var soluce : Array[$[Int]] = sudo.solver()
        //println(sudo.toString)

        /*
        var test : String = table.map(_.mkString(" ")).mkString("\n")
        println(test)
        println("\n")
        */

        /*
        val up = table.updated(4,table(4).updated(5,4))
        println(up.map(_.mkString(" ")).mkString("\n"))
        */


        //for(i <- 0 to 80) println(sudo.parcours_1(i))

        //sudo.parcours_2(0)

        //sudo.parcours_3(0)

        //println((0 until 9).map( v => (3, v) ) )
        //println((0 until 9).map( v => (v, 3) ) )
        //println((0 until 9).map(v => (v/3, v%3)))

        //sudo.parcours_5()

        //sudo.parcours_7()

        //sudo.parcours_9()

        /*
        sudo.parcours_11(table) match {          
            case Some(res) => println("11: \n"+ res.map( _.mkString(" ") ).mkString("\n"))          
            case None => println("pas de solution") 
        }
        */

        
        sudo.parcours_12(table) match {          
            case Some(res) => println("12: \n"+ res.map( _.mkString(" ") ).mkString("\n"))          
            case None => println("pas de solution") 
        }

        val t1 = System.currentTimeMillis()
        println("Elapsed time: " + (t1 - t0) + "ms")
    }
}