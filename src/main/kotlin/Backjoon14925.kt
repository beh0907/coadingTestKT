import java.util.*
import kotlin.math.max
import kotlin.math.min

//목장 건설하기
//https://www.acmicpc.net/problem/14925
private val dirs = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))


fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (x, y) = readln().split(" ").map { it.toInt() }.toIntArray()
    val map = Array(x) { IntArray(y) }
    val dp = Array(x + 1) { IntArray(y + 1) }

    //맵 정보 삽입
    repeat(x) { i ->
        map[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    var result = 0

    for (i in 1..x) {
        for (j in 1..y) {
            if (map[i - 1][j - 1] == 0) {
                dp[i][j] = min(min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1
                result = max(result, dp[i][j])
            }
        }
    }

    //탐색 결과 출력
    println(result)
}
