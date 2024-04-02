import kotlin.math.min

//꽃길
//https://www.acmicpc.net/problem/14620


private val dx = arrayOf(-1, 0, 1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private var result = Int.MAX_VALUE
private lateinit var map: Array<IntArray> // 맵
private lateinit var dp: Array<IntArray> // 씨앗 발아 비용
private lateinit var visited: Array<BooleanArray> // 방문

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val size = readln().toInt()
    map = Array(size) { IntArray(size) }
    dp = Array(size) { IntArray(size) { Int.MAX_VALUE } }
    visited = Array(size) { BooleanArray(size) }

    //맵 비용 할당
    repeat(size) { i ->
        map[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    //위치별 만개 비용
    for (i in 1..<size - 1) {
        for (j in 1..<size - 1) {
            var cost = map[i][j]

            repeat(4) {
                val moveX = i + dx[it]
                val moveY = j + dy[it]
                cost += map[moveX][moveY]
            }

            dp[i][j] = cost
        }
    }

    dfs(0, 0)

    println(result)
}

private fun dfs(cost: Int, count: Int) {
    //꽃을 3개 다 심었다면 최소값을 저장하고 종료
    if (count == 3) {
        result = min(result, cost)
        return
    }

    //반복 처리
    //테두리 부분은 꽃이 피어도 맵 밖으로 나가기 때문에 제외
    for (i in 1..<map.size - 1) {
        for (j in 1..<map.size - 1) {
            //주변에 이미 꽃이 피어있다면 취소한다
            if (!isPossible(i, j)) continue

            //방문처리
            checkVisited(i, j, true)

            //동작 수행
            dfs(cost + dp[i][j], count + 1)

            //방문처리 취소
            checkVisited(i, j, false)
        }
    }
}

//씨앗을 심을 수 있는지 확인한다
private fun isPossible(x: Int, y: Int): Boolean {
    repeat(4) { dir ->
        val moveX = x + dx[dir]
        val moveY = y + dy[dir]

        if (visited[moveX][moveY]) return false
    }

    return true
}

//꽃이 핀 영역을 설정한다
private fun checkVisited(x: Int, y: Int, value: Boolean) {
    //현재 위치 체크
    visited[x][y] = value

    //주변 영역 체크
    repeat(4) { dir ->
        val moveX = x + dx[dir]
        val moveY = y + dy[dir]

        visited[moveX][moveY] = value
    }
}
