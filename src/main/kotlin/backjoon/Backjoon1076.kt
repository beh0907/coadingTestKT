package backjoon

import java.util.HashMap

//촌수 계산
//https://www.acmicpc.net/problem/1076
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val map = HashMap<String, Int>()

    map["black"] = 0
    map["brown"] = 1
    map["red"] = 2
    map["orange"] = 3
    map["yellow"] = 4
    map["green"] = 5
    map["blue"] = 6
    map["violet"] = 7
    map["grey"] = 8
    map["white"] = 9

    val sum = "${map[readln().trim()]}${map[readln().trim()]}"
    var result: Long = sum.toLong()
    repeat(map[readln().trim()]!!) {
        result *= 10
    }

    println(result)
}
