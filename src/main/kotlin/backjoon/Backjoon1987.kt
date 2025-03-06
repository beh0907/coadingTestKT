package backjoon

//알파벳
//https://www.acmicpc.net/problem/1987
fun main() = with(System.`in`.bufferedReader()) {
    val dirX = listOf(0, 0, 1, -1)
    val dirY = listOf(1, -1, 0, 0)

    val (N, M) = readLine().split(' ').map { it.toInt() }
    val map = Array(N) { readLine().toCharArray() }
    var result = 1
    val visitedWord = BooleanArray(26).apply { this[map[0][0] - 'A'] = true }

    fun dfs(x: Int, y: Int, depth: Int) {
        result = maxOf(result, depth)

        // 현재 깊이가 이미 26(알파벳 개수)이면 더 이상 진행할 필요 없음
        if (depth == 26 || result == 26) return

        for (i in 0 until 4) {
            val nx = x + dirX[i]
            val ny = y + dirY[i]

            // 범위 체크를 간결하게 수정
            if (nx !in 0 until N || ny !in 0 until M) continue

            val wordIdx = map[nx][ny] - 'A'

            // 이미 방문한 알파벳이면 건너뜀
            if (visitedWord[wordIdx]) continue

            visitedWord[wordIdx] = true
            dfs(nx, ny, depth + 1)
            visitedWord[wordIdx] = false
        }
    }

    dfs(0, 0, 1)

    println(result)
}
