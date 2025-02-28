package backjoon

//빗물
//https://www.acmicpc.net/problem/14719
fun main() = with(System.`in`.bufferedReader()) {
    val (H, W) = readLine().split(' ').map { it.toInt() }
    val map = readLine().split(' ').map { it.toInt() }

    var result = 0

    val leftMax = IntArray(W)
    val rightMax = IntArray(W)

    //왼쪽에 높은 벽
    leftMax[0] = map[0]
    for (i in 1 until W) leftMax[i] = maxOf(leftMax[i - 1], map[i])

    //오른쪽에 높은 벽
    rightMax[W - 1] = map[W - 1]
    for (i in W - 2 downTo 0) rightMax[i] = maxOf(rightMax[i + 1], map[i])

    //양쪽 벽 중 낮은 벽의 높이에서 현재 높이를 뺀다
    repeat(W) {
        result += minOf(leftMax[it], rightMax[it]) - map[it]
    }

    println(result)
}
