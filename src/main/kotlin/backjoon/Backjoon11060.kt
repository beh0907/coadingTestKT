package backjoon

import kotlin.math.min

//점프점프
//https://www.acmicpc.net/problem/11060
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val Max = 999999

    //지도 사이즈를 얻어온다
    val len = readln().toInt()
    val map = readln().split(" ").map { it.toInt() }.toIntArray()

    val dp = IntArray(len) { Max }
    dp[0] = 0

    repeat(len) { i ->
        for (j in i + 1..<i + 1 + map[i]) {
            if (j >= len) break // 맵 밖으로 나가는 경우에는 종료

            dp[j] = min(dp[j], dp[i] + 1)
        }
    }

    if (dp[len - 1] == Max) println(-1)
    else println(dp[len - 1])
}
