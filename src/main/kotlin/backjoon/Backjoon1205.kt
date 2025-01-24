package backjoon

//등수 구하기
//https://www.acmicpc.net/problem/1076
fun main() = with(System.`in`.bufferedReader()) {
    val (N, score, P) = readLine().split(" ").map(String::toInt)
    var result = 1

    if (N > 0) {
        val scores = readLine().split(" ").map(String::toInt).filter { it >= score }

        if (P > scores.size) {
            val sameValue = scores.count { it == score }
            result = scores.size + 1 - sameValue
        }
        else result = -1
    }

    println(result)
}
