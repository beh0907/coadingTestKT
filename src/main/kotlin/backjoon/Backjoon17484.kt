package backjoon

import java.util.StringTokenizer
import kotlin.math.min

private lateinit var map: Array<IntArray>
private lateinit var dp: Array<IntArray>

//진우의 달 여행 (Small)
//https://www.acmicpc.net/problem/17484
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map(String::toInt)
    map = Array(N) { IntArray(M) { 0 } }
    dp = Array(N) { IntArray(M) { Int.MAX_VALUE } }

    for (i in map.indices) {
        val tokens = StringTokenizer(readLine())

        for (j in 0 until tokens.countTokens()) map[i][j] = tokens.nextToken().toInt()
    }
    dp[0] = map[0]

    for (i in map[0].indices) dfs(0, i, 0, map[0][i])

    println(dp[N - 1].min())
}

private fun dfs(x: Int, y: Int, prevDir: Int, prevValue: Int) {
    for (dir in -1..1) {
        if (x != 0 && dir == prevDir) continue

        val dirX = x + 1
        val dirY = y + dir

        if (dirX !in map.indices || dirY !in map[0].indices) continue

        val value = prevValue + map[dirX][dirY]
        dp[dirX][dirY] = min(dp[dirX][dirY], value)

        dfs(dirX, dirY, dir, value)
    }
}
