package backjoon

// A와 B 2
// https://www.acmicpc.net/problem/12919
fun main() = with(System.`in`.bufferedReader()) {
    val S = readLine()
    val T = readLine()

    println(if (dfs(S, T)) 1 else 0)
}

private fun dfs(s: String, t: String): Boolean {
    if (s == t) return true
    if (t.length <= s.length) return false

    // T에서 S로 가는 방향으로 역으로 접근
    if (t.last() == 'A' && dfs(s, t.substring(0, t.length - 1))) return true
    if (t.first() == 'B' && dfs(s, t.substring(1).reversed())) return true

    return false
}
