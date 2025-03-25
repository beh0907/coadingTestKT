package backjoon

//하늘에서 별똥별이 빗발친다
//https://www.acmicpc.net/problem/14658
fun main() = with(System.`in`.bufferedReader()) {
    // 입력 받기
    val (N, M, L, K) = readLine().split(' ').map(String::toInt)

    // 별똥별 좌표 저장
    val stars = Array(K) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        x to y
    }

    // 최대로 막을 수 있는 별똥별 개수 계산
    var maxCovered = 0

    // 가능한 모든 별똥별을 트램펄린의 좌상단으로 삼아 확인
    for ((x1, y1) in stars) {
        for ((x2, y2) in stars) {
            var sx = minOf(x1, x2)
            var sy = minOf(y1, y2)

            // (x, y)를 트램펄린의 우하단으로 했을 때 덮을 수 있는 별똥별 수 계산
            var covered = stars.count { (ex, ey) -> ex in (sx..sx + L) && ey in (sy..sy + L) }

            maxCovered = maxOf(maxCovered, covered)
        }
    }

    // 전체 별똥별에서 최대 덮을 수 있는 별똥별을 제외한 나머지가 맞는 별똥별 수
    println(K - maxCovered)
}
