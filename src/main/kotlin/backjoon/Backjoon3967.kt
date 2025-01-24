package backjoon

//매직스타
//https://www.acmicpc.net/problem/3967

private val blankList = ArrayList<Pair<Int, Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val map = Array(5) { CharArray(9) }

    val visited = BooleanArray(12)

    repeat(5) { i ->
        map[i] = readln().toCharArray()

        repeat(9) { j ->

            //값이 빈 위치라면 리스트에 추가한다
            if (map[i][j] == 'x') blankList.add(Pair(i, j))

            //값이 이미 있다면 방문 처리
            else if (map[i][j] != '.') {
                visited[map[i][j] - 'A'] = true
            }
        }
    }

    dfs(map, visited, 0)
}

private fun dfs(map: Array<CharArray>, visited: BooleanArray, cnt: Int) {
    if (cnt == blankList.size) {
        if (!checkLines(map)) return

        //모든 라인이 26임이 확인 됐다면 맵을 출력한다
        map.forEach {
            println(it)
        }

        //출력이 완료 되었다면 프로그램을 종료
        System.exit(0)
    }

    val blank = blankList[cnt]

    val x = blank.first
    val y = blank.second

    for (i in 0..11) {
        //이미 사용한 값이라면 continue
        if (visited[i]) continue

        //방문 처리 및 맵에 값 저장
        visited[i] = true
        map[x][y] = 'A' + i

        //backjoon.dfs 재귀
        dfs(map, visited, cnt + 1)

        //저장된 값 롤백
        visited[i] = false
        map[x][y] = 'x'
    }
}

//모든 라인의 합이 26인지 확인한다
fun checkLines(map: Array<CharArray>): Boolean {
    val sumLines = IntArray(6) { 0 }

    for (i in 0..<4) {
        sumLines[0] += (map[i][4 - i] - 'A') + 1
    }
    for (i in 0..<4) {
        sumLines[1] += (map[i][4 + i] - 'A') + 1
    }

    for (i in 0..<4) {
        sumLines[2] += (map[4 - i][4 - i] - 'A') + 1
    }
    for (i in 0..<4) {
        sumLines[3] += (map[4 - i][4 + i] - 'A') + 1
    }

    for (i in 0..<4) {
        sumLines[4] += (map[1][1 + 2 * i] - 'A') + 1
    }

    for (i in 0..<4) {
        sumLines[5] += (map[3][1 + 2 * i] - 'A') + 1
    }

    //하나의 라인이라도 26이 되지 않는 다면 답이 아니다
    for (i in 0..<6) {
        if (sumLines[i] != 26) return false
    }

    return true
}
