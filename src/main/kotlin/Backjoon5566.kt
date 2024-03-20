fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val map = IntArray(N)
    var start = 1

    //맵 초기화
    repeat(N) {
        map[it] = readln().toInt()
    }

    //주사위 롤
    repeat(M) {
        val dice = readln().toInt()

        //주사위에서 나온 값만큼 이동한다
        start += dice

        //주사위로 이동한 위치가 도착시점 이상이라면 탈출
        if (start >= N) {
            println("${it + 1}") //주사위를 던진 수만큼 출력
            return@with
        }

        //맵에 나온 지시사항만큼 이동한다
        start += map[start - 1]

        //지시사항으로 이동한 위치가 도착시점 이상이라면 탈출
        if (start >= N) {
            println("${it + 1}") //주사위를 던진 수만큼 출력
            return@with
        }
    }
}
