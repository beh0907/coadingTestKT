package backjoon

//페그 솔리테어
//https://www.acmicpc.net/problem/9207

//4방향 인접
private val dirs = arrayOf(
    Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)
)

//핀의 최소 개수와 핀의 이동 횟수
private lateinit var result: Pair<Int, Int>

//맵 정보
private lateinit var map: Array<CharArray>

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val cases = readln().toInt()

    repeat(cases) { case ->
        map = Array(5) { CharArray(9) }
        val pins = ArrayList<Pair<Int, Int>>()

        //맵 정보 읽기
        repeat(5) { i ->
            map[i] = readln().toCharArray()

            //핀의 위치를 저장한다
            map[i].forEachIndexed { j, ch ->
                if (ch == 'o') pins.add(Pair(i, j))
            }
        }

        result = Pair(pins.size, 0)

        //모든 핀들을 하나씩 탐색한다
        for (pin in pins) {
            dfs(pin.first, pin.second, pins.size, 0)
        }

        //탐색 완료 후 결과 출력
        println("${result.first} ${result.second}")

        //공백 한줄 읽기
        if (case != cases - 1) readln()
    }
}


private fun dfs(x: Int, y: Int, remain: Int, move: Int) {
    //현재 탐색 중인 핀 수가 더 작다면
    if (result.first > remain) {
        result = Pair(remain, move)
    }

    for (dir in dirs) {
        val dx = x + dir.first
        val dy = y + dir.second

        //인접한 위치가 맵 밖이라면 continue
        if (dx !in map.indices || dy !in map[0].indices) continue

        //인접한 위치에 핀이 없다면 continue
        if (map[dx][dy] != 'o') continue

        //인접한 핀의 위치를 기준으로 이동할 주변의 빈공간을 찾는다
        //인접한 핀의 다음칸으로만 이동할 수 있기 때문에 같은 방향의 좌표값을 사용한다
        val ddx = dx + dir.first
        val ddy = dy + dir.second

        //다음 위치가 맵 밖이라면 continue
        if (ddx !in map.indices || ddy !in map[0].indices) continue

        //다음 위치가 빈공간이 아니라면 continue
        if (map[ddx][ddy] != '.') continue

        //핀을 제거하고 핀을 이동시킨다
        map[x][y] = '.'
        map[dx][dy] = '.'
        map[ddx][ddy] = 'o'

        //남은 핀들을 찾아 탐색한다
        repeat(5) { i ->
            repeat(9) { j ->
                if (map[i][j] == 'o')
                    dfs(i, j, remain - 1, move + 1)
            }
        }

        //조작된 핀들을 롤백시킨다
        map[x][y] = 'o'
        map[dx][dy] = 'o'
        map[ddx][ddy] = '.'
    }
}
