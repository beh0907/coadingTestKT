package backjoon

//List of Unique Numbers
//https://www.acmicpc.net/problem/13144
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }

    var result = 0L //N = 100,000 - 1,2,3....100,000일 경우 Int로 표현할 수 없다
    var left = 0

    // 각 숫자의 등장 여부를 체크할 배열 (문제 조건: 숫자 범위는 1부터 100,000까지)
    val check = BooleanArray(100001)

    for (right in 0 until N) {
        // 현재 right가 가리키는 숫자가 이미 등장했다면
        while (check[arr[right]]) {
            // left를 오른쪽으로 이동시키면서 중복을 제거
            check[arr[left]] = false
            left++
        }

        // 현재 right가 가리키는 숫자를 등장했다고 표시
        check[arr[right]] = true

        // left부터 right까지의 모든 연속 부분 수열 개수 더하기
        result += right - left + 1
    }

    println(result)
}
