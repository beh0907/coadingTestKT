import java.util.*


//양
//https://www.acmicpc.net/problem/3085
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (sizeX, sizeY) = readln().split(" ").map { it.toInt() }
    val map = Array(sizeX) { CharArray(sizeY) }
    val visited = Array(sizeX) { BooleanArray(sizeY) }
    var (aliveSheep, aliveWolf) = Pair(0, 0)

    //맵 정보 추가
    //# = 울타리
    //o = 양
    //v = 늑대
    //. = 빈 공간
    repeat(sizeX) { i ->
        map[i] = readln().toCharArray()
    }

    repeat(sizeX) { x ->
        repeat(sizeY) { y ->
            //울타리가 아니며, 방문한 적이 없는 위치만
            if (map[x][y] != '#' && !visited[x][y]) {
                val result = bfs(map, visited, x, y)
                aliveSheep += result.first // 첫번째는 생존한 양의 수를 리턴
                aliveWolf += result.second // 두번째는 생존한 늑대의 수를 리턴
            }
        }
    }


    println("$aliveSheep $aliveWolf")
}

private fun bfs(map: Array<CharArray>, visited: Array<BooleanArray>, x: Int, y: Int): Pair<Int, Int> {
    //4방향 이동
    val dx = intArrayOf(1, 0, 0, -1)
    val dy = intArrayOf(0, 1, -1, 0)

    //출발지는 방문 처리
    visited[x][y] = true

    //영역 내 동물 수 체크
    var (sheep, wolf) = Pair(0, 0)
    if (map[x][y] == 'o') ++sheep // 출발지가 양이라면 추가
    if (map[x][y] == 'v') ++wolf // 출발지가 늑대라면 추가

    //큐 생성
    val queue: Queue<Point> = LinkedList()
    queue.offer(Point(x, y))

    while (queue.isNotEmpty()) {
        //큐에서 이동할 위치값을 가져온다
        val point = queue.poll()

        //4방향 이동
        for (i in 0..3) {
            val moveX = point.x + dx[i]
            val moveY = point.y + dy[i]

            //이동할 위치가 맵 밖이라면 취소
            if (moveX < 0 || moveY < 0 || moveX >= map.size || moveY >= map[0].size) continue

            //이동할 맵이 방문한 이력이 있다면 취소
            if (visited[moveX][moveY]) continue

            //이동할 맵이 울타리라면 취소
            if (map[moveX][moveY] == '#') continue

            //방문처리
            visited[moveX][moveY] = true

            //방문할 위치의 동물 체크
            if (map[moveX][moveY] == 'o') ++sheep // 출발지가 양이라면 추가
            if (map[moveX][moveY] == 'v') ++wolf // 출발지가 늑대라면 추가

            //큐에 이동할 위치 추가
            queue.offer(Point(moveX, moveY))
        }
    }

    //결과 정리
    if (sheep > wolf) wolf = 0 //양이 많다면 늑대를 다 쫓아낸다
    else sheep = 0 // 아니라면 양은 늑대한테 잡아먹힌다


    return Pair(sheep, wolf)
}

data class Point(val x: Int, val y: Int)
