package backjoon

import java.util.HashMap

//단축키 지정
//https://www.acmicpc.net/problem/1283
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val bw = System.out.bufferedWriter()
    val map = mutableSetOf<Char>()

    repeat(N) {
        val str = readLine()
        val split = str.split(" ")
        var keyIndex = -1

        for (word in split) {
            word[0].lowercaseChar()
        }

        bw.write("\n")
    }

    bw.flush()
    bw.close()
}

fun main2(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val split = readLine().split("-")

    for(str in split) bw.write("${str[0]}")

    bw.flush()
    bw.close()
}


