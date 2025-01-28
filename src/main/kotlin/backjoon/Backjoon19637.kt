package backjoon

//IF문 좀 대신 써줘
//https://www.acmicpc.net/problem/19637
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(' ').map(String::toInt)
    val result = StringBuilder()
    val powers = Array<Pair<String, Int>>(N) { "" to 0 }

    repeat(N) {
        val str = readLine().split(' ')
        powers[it] = str[0] to str[1].toInt()
    }

    repeat(M) {
        val power = readLine().toInt()
        var left = 0
        var right = powers.size - 1
        var title = ""

        while (left <= right) {
            val mid = (left + right) / 2

            when {
                powers[mid].second >= power -> {
                    right = mid - 1
                    title = powers[mid].first
                }
                else -> left = mid + 1
            }
        }

        result.append("$title\n")
    }

    print(result)
}
