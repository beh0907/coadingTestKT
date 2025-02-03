package backjoon

//볼 모으기
//https://www.acmicpc.net/problem/17615
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val balls = readLine()

    // 왼쪽으로 빨간 볼 모으기
    val redLeft = balls.trimStart('R').count { it == 'R' }

    // 오른쪽으로 빨간 볼 모으기
    val redRight = balls.trimEnd('R').count { it == 'R' }

    // 왼쪽으로 파란 볼 모으기
    val blueLeft = balls.trimStart('B').count { it == 'B' }

    // 오른쪽으로 파란 볼 모으기
    val blueRight = balls.trimEnd('B').count { it == 'B' }

    // 네 가지 경우 중 최소값 출력
    println(minOf(redLeft, redRight, blueLeft, blueRight))
}
