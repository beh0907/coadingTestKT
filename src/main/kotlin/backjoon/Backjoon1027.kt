package backjoon

//줄세우기
//https://www.acmicpc.net/problem/2631
fun main() = with(System.`in`.bufferedReader()) {
    fun findLIS(arr: IntArray): Int {
        val n = arr.size
        val dp = IntArray(n) { 1 } // 각 위치에서 끝나는 LIS의 길이

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (arr[j] < arr[i]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }

        return dp.maxOrNull() ?: 1
    }

    val N = readLine().toInt()
    val children  = IntArray(N) { readLine().toInt() }

    // 최장 증가 부분 수열(LIS)의 길이를 구함
    val lisLength = findLIS(children)

    // 전체 어린이 수 - LIS 길이 = 최소 이동 횟수
    val minMoves = N - lisLength
    println(minMoves)
}
