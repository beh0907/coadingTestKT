//이동하기
//https://www.acmicpc.net/problem/11048
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    //지도 사이즈를 얻어온다
    val (x, y) = readln().split(" ").map { it.toInt() }
    val map = Array(x) { IntArray(y) }
    val dp = Array(x + 1) { IntArray(y + 1) }

    //값 저장
    repeat(x) { i ->
        map[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    for (i in 1..x) {
        for (j in 1..y) {
            //3 경로 중 최다 사탕 수를 저장한다
            dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + map[i - 1][j - 1]
        }
    }

    //마지막 정보 출력
    println(dp[x][y])
}
