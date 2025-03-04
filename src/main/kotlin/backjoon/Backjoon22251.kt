package backjoon

val digits = arrayOf(
    "1111110", // 0
    "0110000", // 1
    "1011011", // 2
    "1111001", // 3
    "0110101", // 4
    "1101101", // 5
    "1101111", // 6
    "1110000", // 7
    "1111111", // 8
    "1111101"  // 9
)

//빌런 호석
//https://www.acmicpc.net/problem/22251
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K, P, X) = readLine().split(' ').map { it.toInt() }

    // 각 숫자 간 변경에 필요한 LED 개수를 미리 계산
    val changes = Array(10) { i ->
        Array(10) { j ->
            calculateChanges(i, j)
        }
    }

    val currentFloor = getDigits(X, K)
    var result = 0

    for (i in 1..N) {
        //현재 층은 비교하지 않는다
        if (i == X) continue

        val targetFloor = getDigits(i, K)
        var totalChanges = 0

        //각 자리별 LED 변경이 필요한 수를 계산
        repeat(K) {
            totalChanges += changes[currentFloor[it]][targetFloor[it]]
        }

        //P 이하의 횟수라면 변경 가능한 층
        if (totalChanges <= P) result++
    }

    println(result)
}

// 숫자 간 변경에 필요한 LED 개수를 계산하는 함수
fun calculateChanges(from: Int, to: Int): Int {
    var count = 0
    for (i in 0 until 7) {
        if (digits[from][i] != digits[to][i]) {
            count++
        }
    }
    return count
}

// 현재 층수 x를 k자리 배열로 변환
fun getDigits(num: Int, len: Int): Array<Int> {
    val result = Array(len) { 0 }
    var temp = num
    for (i in len - 1 downTo 0) {
        result[i] = temp % 10
        temp /= 10
    }
    return result
}
