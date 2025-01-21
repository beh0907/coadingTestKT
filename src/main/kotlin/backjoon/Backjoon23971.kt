package backjoon

//ZOAC 4
//https://www.acmicpc.net/problem/23971
fun main() = with(System.`in`.bufferedReader()) {
    val (H, W, N, M) = readLine().split(" ").map { it.toInt() }

    //거리두기를 위한 빈공간
    val emptySpace = 1

    //빈공간은 반영한 가로 세로 할당 공간
    //나머지가 발생하면 강제로 올림 처리
    val valRow = (H + (N + emptySpace) - 1) / (N + emptySpace)
    val valCol = (W + (M + emptySpace) - 1) / (M + emptySpace)

    println(valRow * valCol)
}
