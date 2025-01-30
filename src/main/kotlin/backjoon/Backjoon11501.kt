package backjoon

import java.util.StringTokenizer

//주식
//https://www.acmicpc.net/problem/11501
fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    val result = StringBuilder()

    repeat(T) {
        val N = readLine().toInt()
        val tokens = StringTokenizer(readLine())
        val prices = LongArray(N) { tokens.nextToken().toLong() }

        var maxPrice = 0L
        var profit = 0L

        for (i in N - 1 downTo 0) {
            if (prices[i] > maxPrice) maxPrice = prices[i]
            else profit += (maxPrice - prices[i])
        }

        result.append("$profit\n")
    }

    print(result)
}
