package backjoon

//올림픽
//https://www.acmicpc.net/problem/8979
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }
    val array = Array(N) { Medal() }
    lateinit var target: Medal

    repeat(N) {
        val (n, g, s, b) = readLine().split(" ").map { it.toInt() }
        array[n - 1] = Medal(n, g, s, b)

        if (n == K) target = array[n - 1]
    }
    array.sortDescending()

    var result = 1
    for (i in array.indices) {
        val medal = array[i]
        if (medal.gold == target.gold && medal.silver == target.silver && medal.bronze == target.bronze) break

        result++
    }

    println(result)
}

data class Medal(
    val num: Int = 0,
    val gold: Int = 0,
    val silver: Int = 0,
    val bronze: Int = 0
) : Comparable<Medal> {
    override fun compareTo(other: Medal): Int {
        if (gold != other.gold) return gold - other.gold
        if (silver != other.silver) return silver - other.silver
        if (bronze != other.bronze) return bronze - other.bronze

        return 0
    }
}
