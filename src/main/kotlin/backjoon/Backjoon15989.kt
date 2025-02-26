package backjoon

//1, 2, 3 더하기 4
//https://www.acmicpc.net/problem/15989
fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    // 최대 N값에 대한 DP 배열을 미리 계산
    val MAX = 10000
    val dp = Array(MAX + 1) { IntArray(4) }

    // 초기값 설정
    dp[0][1] = 1

    // DP 테이블 채우기 (미리 한 번만 계산)
    for (i in 1..MAX) {
        dp[i][1] = dp[i-1][1]                       // 1만 사용
        if (i >= 2) dp[i][2] = dp[i-2][1] + dp[i-2][2]  // 1,2만 사용
        if (i >= 3) dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3]  // 1,2,3 모두 사용
    }

    // 쿼리 처리
    repeat(readLine().toInt()) {
        val n = readLine().toInt()
        val result = dp[n][1] + dp[n][2] + dp[n][3]
        bw.write("$result\n")
    }

    bw.flush()
    bw.close()
}
