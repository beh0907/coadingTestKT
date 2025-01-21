package backjoon

//스타트링크
//https://www.acmicpc.net/problem/9372
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val T = readln().toInt() // 테이스 케이스

    repeat(T) {
        val (country, plane) = readln().split(" ").map { it.toInt() }

        repeat(plane) { readln() }

        //비행기 수만 구하면 된다
        println(country - 1)
    }
}
