package backjoon

import java.util.StringTokenizer

//크로스 컨트리
//https://www.acmicpc.net/problem/9017
fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()

    repeat(T) {
        var N = readLine().toInt()
        var cnt = 0
        var array = Array(N) { 0 }
        var teamMap = mutableMapOf<Int, Team>()

        val tokens = StringTokenizer(readLine())
        while (tokens.hasMoreTokens()) {
            val token = tokens.nextToken().toInt()
            array[cnt++] = token

            if (teamMap.containsKey(token)) teamMap[token]?.size = teamMap[token]!!.size.plus(1)
            else teamMap[token] = Team()
        }

        teamMap = teamMap.filter { it.value.size == 6 }.toMutableMap()
        array = array.filter { teamMap.containsKey(it) }.toTypedArray()

        array.forEachIndexed { index, item ->
            teamMap[item]!!.scores.add(index + 1)
        }
        teamMap.toList().sortedBy { it.second }[0].first

        println(teamMap.toList().sortedBy { it.second }[0].first)
    }
}

private data class Team(
    var size: Int = 1,
    var scores: ArrayList<Int> = arrayListOf(),
) : Comparable<Team> {
    override fun compareTo(other: Team): Int {
        val thisSum = scores.slice(0..3).sum()
        val otherSum = other.scores.slice(0..3).sum()

        return if (thisSum == otherSum) scores[4] - other.scores[4]
        else thisSum - otherSum
    }
}
