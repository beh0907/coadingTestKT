package backjoon

import kotlin.math.max

//KCPC
//https://www.acmicpc.net/problem/3758
fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    var result = ""

    repeat(T) {
        val (n, k, t, m) = readLine().split(' ').map(String::toInt)
        val teams = Array(n) { Team2(scores = IntArray(k)) }

        repeat(m) {
            val (i, j, s) = readLine().split(' ').map(String::toInt)
            val team = teams[i - 1]

            team.scores[j - 1] = max(team.scores[j - 1], s)
            team.summit++
            team.lastTime = it
        }

        val team = teams[t - 1]
        val teamScore = team.scores.sum()
        val count = teams.count {
            val score = it.scores.sum()
            when {
                score > teamScore -> true
                score < teamScore -> false
                else -> when {  // score == teamScore인 경우
                    team.summit > it.summit -> true
                    team.summit < it.summit -> false
                    else -> team.lastTime > it.lastTime  // summit이 같은 경우
                }
            }
        } + 1

        result += "$count\n"
    }

    println(result)
}

private data class Team2(
    var scores: IntArray,
    var summit: Int = 0,
    var lastTime: Int = 0,
)
