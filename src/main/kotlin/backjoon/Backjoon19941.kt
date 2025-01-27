package backjoon

//햄버거 분배
//https://www.acmicpc.net/problem/19941
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map(String::toInt)
    val map = readLine().toCharArray()
    var result = 0

    repeat(N) {
        if (map[it] != 'P') return@repeat

        val range = (it - K..it + K)

        for (idx in range) {
            if (idx in map.indices && map[idx] == 'H') {
                map[idx] = 'X'
                result++
                break
            }
        }
    }

    println(result)
}
