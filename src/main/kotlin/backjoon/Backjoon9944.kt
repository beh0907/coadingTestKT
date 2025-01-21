package backjoon//NxM 보드 완주하기
//https://www.acmicpc.net/problem/9944

import kotlin.math.min

//4방향 인접
private val dirs = arrayOf(
    Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)
)

//탐색 완료에 필요한 칸 수
private var check: Int = 0

//결과
private var result: Int = 0

fun main() {
    var case = 0
    var str: String?

    while (readLine().also { str = it } != null) {
        val (x, y) = str!!.split(" ").map { it.toInt() }
        val map = Array(x) { readln().toCharArray() }

        result = Int.MAX_VALUE

        check = 0

        //맵 정보 저장
        repeat(x) { i ->
            repeat(y) { j ->
                //탐색해야 할 빈칸 추가
                if (map[i][j] == '.') check++
            }
        }

        if (check == 1) { // 모든 점이 이어진 상태에서는 움직일 필요가 없음
            result = 0
        } else {
            //모든 칸에서 시작한다
            repeat(x) { i ->
                repeat(y) { j ->
                    //빈칸일 경우 탐색한다
                    if (map[i][j] == '.') {
                        //방문처리를 위해 벽으로 설정한다
                        map[i][j] = '*'

                        //인접한 4방향 탐색
                        for (index in dirs.indices) {
                            dfs(map, i, j, index, 1, 1)
                        }

                        //방문처리 롤백
                        map[i][j] = '.'
                    }
                }
            }
        }

        //결과값이 여전하다면 찾을 수 없다는 뜻
        if (result == Int.MAX_VALUE) result = -1

        //결과 출력
        println("Case ${++case}: $result")
    }
}

private fun dfs(map: Array<CharArray>, x: Int, y: Int, indexDir: Int, move: Int, cnt: Int) {
    //모든 칸을 탐색했다면 최소한의 진행 횟수를 저장한다
    if (cnt == check) {
        result = min(result, move)
        return
    }

    val dx = x + dirs[indexDir].first
    val dy = y + dirs[indexDir].second

    //현재 진행방향대로 진행할 수 있다면
    if (dx in map.indices && dy in map[0].indices && map[dx][dy] != '*') {
        //방문처리를 위해 벽으로 설정한다
        map[dx][dy] = '*'

        //탐색
        dfs(map, dx, dy, indexDir, move, cnt + 1)

        //방문처리 롤백
        map[dx][dy] = '.'
    } else {// 방향에 변화를 줘야 한다면
        for (index in dirs.indices) {
            //꺾기 위한 방향
            val ddx = x + dirs[index].first
            val ddy = y + dirs[index].second

            if (ddx in map.indices && ddy in map[0].indices && map[ddx][ddy] != '*') {
                //방문처리를 위해 벽으로 설정한다
                map[ddx][ddy] = '*'

                //방향을 꺾어 탐색
                dfs(map, ddx, ddy, index, move + 1, cnt + 1)

                //방문처리 롤백
                map[ddx][ddy] = '.'
            }
        }
    }
}
