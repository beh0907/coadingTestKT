package backjoon

import java.util.StringTokenizer
import kotlin.math.max

//예산
//https://www.acmicpc.net/problem/2512
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val tokens = StringTokenizer(readLine())
    val array = IntArray(N)
    val money = readLine().toInt()

    var left = 0
    var right = -1

    repeat(N) {
        array[it] = tokens.nextToken().toInt()
        right = max(right, array[it])
    }

    while (left <= right) {
        var mid = (left + right) / 2

        var budget = 0
        repeat(N) {
            budget += if (array[it] > mid) mid
            else array[it]
        }

        if (budget <= money) left = mid + 1
        else right = mid - 1
    }


    println(right)
}
