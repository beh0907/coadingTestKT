package backjoon

//줄세우기
//https://www.acmicpc.net/problem/10431
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    repeat(n) {
        val str = readLine().split(" ")
        val array = str.drop(1).map { it.toInt() }
        var cnt = 0

        for (i in array.indices) {
            for (j in 0 until i) {
                if (array[i] < array[j]) cnt++
            }
        }

        println("${str[0]} $cnt")
    }
}
