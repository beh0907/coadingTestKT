package backjoon

import kotlin.math.max

//수열
//https://www.acmicpc.net/problem/2559
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map(String::toInt)
    val array = readLine().split(" ").map(String::toInt)

    var sum = array.slice(0 until K).sum()
    var max = sum

    for (i in K until N) {
        sum = sum + array[i] - array[i - K]
        max = max(max, sum)
    }

    println(max)
}
