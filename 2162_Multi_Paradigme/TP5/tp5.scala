import scala.io.AnsiColor

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

    def reset : String = fBlack + white
}

object tp5 {

    def main(args_ : Array[String] ):Unit = {
        println("coucou"+Ansi.fBlue+Ansi.red+" abcdef "+Ansi.reset)

    }
}