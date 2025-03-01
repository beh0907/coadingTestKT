package backjoon

import kotlin.math.abs

//용액
//https://www.acmicpc.net/problem/2467
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val array = readLine().split(" ").map { it.toInt() }

    var result = -1_000_000_000 to -1_000_000_000
    var (left, right) = 0 to N - 1

    while (left < right) {
        val sum = array[left] + array[right]

        if (abs(sum) < abs(result.first + result.second)) result = array[left] to array[right]

        if (sum < 0) left++
        else right--
    }

    println("${result.first} ${result.second}")
}
