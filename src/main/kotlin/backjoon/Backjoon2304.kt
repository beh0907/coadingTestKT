package backjoon

//창고 다각형
//https://www.acmicpc.net/problem/2304
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val array = Array<Pair<Int, Int>>(N) {
        val (L, H) = readLine().split(' ').map(String::toInt)
        L to H
    }.sortedBy { it.first }

    var max = array.withIndex()
        .maxBy { it.value.second }
        .let { it.index to it.value.second }

    var result = max.second
    var prev = array[0]
    for (i in 1..max.first) {
        val item = array[i]

        if (item.second >= prev.second) {
            result += (item.first - prev.first) * prev.second
            prev = item
        }
    }

    prev = array[N - 1]
    for (i in N - 2 downTo max.first) {
        val item = array[i]

        if (item.second >= prev.second) {
            result += (prev.first - item.first) * prev.second
            prev = item
        }
    }

    println(result)
}
