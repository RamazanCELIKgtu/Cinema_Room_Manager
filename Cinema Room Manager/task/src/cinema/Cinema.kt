package cinema

import java.lang.IndexOutOfBoundsException

var row = 0
var rowSeat = 0
var rowNumber = 0
var seatNumber = 0
var ticketPrice = 0
var currentIncome = 0
var purchasedTickets = 0
var percentage = 0.0

fun seatPrinter(a:MutableList<MutableList<String>>){
    println("\nCinema:")
    print("  ")
    for (i in 1 .. rowSeat) print("$i ")
    println()
    for (i in 0 until row){
        for (j in 0 .. rowSeat){
            print("${a[i][j]} ")
        }
        println()
    }
}

fun buyTicket(){
    println("\nEnter a row number:")
    rowNumber = readln().toInt()
    println("Enter a seat number in that row:")
    seatNumber = readln().toInt()
    println()
    ticketPrice = if (row * rowSeat < 60 || rowNumber <= row / 2) 10 else 8
}

fun main() {
    println("Enter the number of rows:")
    row = readln().toInt()

    println("Enter the number of seats in each row")
    rowSeat = readln().toInt()

    val totalIncome  = if (row * rowSeat < 60) row * rowSeat * 10
    else (row / 2 * rowSeat * 10) + (row * rowSeat - (row / 2) * rowSeat) * 8

    val cinemaList = MutableList(row) { MutableList(rowSeat + 1) {"S"} }
    for (i in 0 until row) cinemaList[i][0] = "${i + 1}"

    while (true){
        println("""   
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""")
         when(readln().toInt()){
            1 -> seatPrinter(cinemaList)
            2 -> {
                while (true) {
                    try {
                        buyTicket()
                        if(cinemaList[rowNumber - 1][seatNumber] == "B") {
                            println ("That ticket has already been purchased!")
                            continue
                        }
                        else {
                            cinemaList[rowNumber - 1][seatNumber] = "B"
                            println("Ticket price: $$ticketPrice\n")
                            currentIncome += ticketPrice
                            purchasedTickets ++
                             percentage = purchasedTickets.toDouble() / (row * rowSeat).toDouble() * 100
                            break
                        }
                    } catch (e: IndexOutOfBoundsException){
                        println("Wrong input!")
                    }
                }
            }
            3 -> { println("""
                
                Number of purchased tickets: $purchasedTickets
                Percentage: ${"%.2f".format(percentage)}%
                Current income: ${'$'}$currentIncome
                Total income: ${'$'}$totalIncome
            """.trimIndent())
            }
            0 -> break
        }
    }
}



