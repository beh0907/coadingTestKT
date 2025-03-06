package backjoon

//공유기 설치
//https://www.acmicpc.net/problem/2110
fun main() = with(System.`in`.bufferedReader()) {
    val (N, C) = readLine().split(' ').map { it.toInt() }
    val array = IntArray(N) { readLine().toInt() }.sorted()

    // 주어진 거리로 C개 이상의 공유기를 설치할 수 있는지 확인하는 함수
    fun isPossible(houses: List<Int>, distance: Int, count: Int): Boolean {
        var installedCount = 1  // 첫 번째 집에는 무조건 설치
        var lastPosition = houses[0]

        for (i in 1 until houses.size) {
            if (houses[i] - lastPosition >= distance) {
                installedCount++
                lastPosition = houses[i]

                if (installedCount >= count) {
                    return true
                }
            }
        }

        return false
    }

    // 이분 탐색을 위한 최소, 최대 거리 설정
    var low = 1  // 최소 거리
    var high = array[N-1] - array[0]  // 최대 거리
    var result = 0

    while (low <= high) {
        val mid = (low + high) / 2  // 현재 기준 거리

        if (isPossible(array, mid, C)) {
            result = mid  // 가능한 경우 결과 갱신
            low = mid + 1  // 더 큰 거리 탐색
        } else {
            high = mid - 1  // 더 작은 거리 탐색
        }
    }

    println(result)
}
