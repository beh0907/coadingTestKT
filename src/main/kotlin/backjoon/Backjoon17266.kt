package backjoon

import java.util.StringTokenizer
import kotlin.math.ceil
import kotlin.math.max

//어두운 굴다리
//https://www.acmicpc.net/problem/17266
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val M = readLine().toInt()
    val tokens = StringTokenizer(readLine())

    var value = tokens.nextToken().toInt()
    var result = value

    while (tokens.hasMoreTokens()) {
        val next = tokens.nextToken().toInt()
        result = max(result, ceil((next - value).toFloat() / 2f).toInt())

        value = next
    }

    result = max(result, (N - value))
    print(result)
}
