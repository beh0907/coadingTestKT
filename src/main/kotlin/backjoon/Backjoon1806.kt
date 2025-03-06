package backjoon

//부분합
//https://www.acmicpc.net/problem/1806
fun main() = with(System.`in`.bufferedReader()) {
    val (N, S) = readLine().split(' ').map { it.toInt() }
    val array = readLine().split(' ').map { it.toInt() }

    var (left, right) = 0 to 0
    var sum = 0
    var result = Int.MAX_VALUE

    while (true) {
        // 현재 합이 목표값보다 작고 right가 배열 끝에 도달하지 않았다면
        // right를 오른쪽으로 이동하며 값을 더함
        if (sum < S) {
            if (right >= N) break
            sum += array[right++]
        }
        // 현재 합이 목표값 이상이라면
        // 현재 길이를 계산하고, left를 오른쪽으로 이동하며 값을 뺌
        else {
            result = minOf(result, right - left)
            sum -= array[left++]
        }
    }

    println(
        if (result == Int.MAX_VALUE) 0
        else result
    )
}
