package backjoon

import kotlin.math.max

//회전 초밥
//https://www.acmicpc.net/problem/2531
fun main() = with(System.`in`.bufferedReader()) {
    val (N, d, k, c) = readLine().split(" ").map { it.toInt() }
    val array = IntArray(N)
    val sushi = IntArray(d + 1)

    repeat(N) {
        array[it] = readLine().toInt()
    }

    sushi[c]++
    for(i in 0 until k) {
        sushi[array[i]]++
    }

    var result = sushi.count { it > 0 }

    for (i in 0 until N) {
        sushi[array[i]]--

        sushi[array[(i + k) % N]]++

        result = max(result, sushi.count { it > 0 })
    }

    println(result)
}
