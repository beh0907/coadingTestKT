package backjoon

//쿠키의 신체 측정
//https://www.acmicpc.net/problem/20125
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val map = Array<CharArray>(N) { CharArray(N) { ' ' } }

    repeat(N) {
        map[it] = readLine().toCharArray()
    }

    val heart = getHeartPosition(map)
    val leftArm = getLength(map, heart.first, heart.second, 0, -1)
    val rightArm = getLength(map, heart.first, heart.second, 0, 1)
    val west = getLength(map, heart.first, heart.second, 1, 0)
    val leftLeg = getLength(map, heart.first + west, heart.second - 1, 1, 0)
    val rightLeg = getLength(map, heart.first + west, heart.second + 1, 1, 0)

    println("${heart.first + 1} ${heart.second + 1}")
    println("$leftArm $rightArm $west $leftLeg $rightLeg")
}

fun getHeartPosition(map: Array<CharArray>): Pair<Int, Int> {
    for (i in 0 until map.size) {
        for (j in 0 until map.size) {
            if (map[i][j] == '*') return Pair(i + 1, j)
        }
    }

    return Pair(0, 0)
}

fun getLength(map: Array<CharArray>, startX: Int, startY: Int, dirX: Int, dirY: Int): Int {
    var newX = startX + dirX
    var newY = startY + dirY
    var result = 0

    while (true) {
        if (newX !in map.indices || newY !in map.indices) break
        if (map[newX][newY] != '*') break

        newX += dirX
        newY += dirY
        result++
    }
    return result
}
