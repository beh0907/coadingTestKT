package backjoon

//로봇
//입력값들의 문제로 trim 추가
//https://www.acmicpc.net/problem/13901
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    //지도 사이즈를 얻어온다
    val (w, h) = readln().trim().split(" ").map { it.toInt() }

    //방해물 수
    val hurdles = readln().trim().toInt()

    //맵
    val map = Array(w) { BooleanArray(h) }

    repeat(hurdles) {
        //방해물의 위치를 가져온다
        val (x, y) = readln().trim().split(" ").map { it.toInt() }

        //방해물 위치에 값 설정
        map[x][y] = true
    }

    //로봇의 시작 위치 설정
    var (startX, startY) = readln().trim().split(" ").map { it.toInt() }
    map[startX][startY] = true

    //로봇을 이동 시킬 4개의 고정 명령
    val commands = readln().trim().split(" ").map { it.toInt() - 1 }
    val dx = arrayOf(-1, 1, 0, 0)
    val dy = arrayOf(0, 0, -1, 1)

    var command = 0
    var count = 0
    while (count < 4) {
        val moveX = startX + dx[commands[command]]
        val moveY = startY + dy[commands[command]]

        //이동할 위치가 맵 내부이며, 방문한 이력이 없다면
        if (moveX in 0..<w && moveY in 0..<h && !map[moveX][moveY]) {
            map[moveX][moveY] = true

            startX = moveX
            startY = moveY
            count = 0
        } else {
            count++
            command = (command + 1) % commands.size
        }

    }

    println("$startX $startY")
}
