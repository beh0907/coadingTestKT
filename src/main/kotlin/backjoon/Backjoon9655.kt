package backjoon

//돌게임
//https://www.acmicpc.net/problem/9655
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    //홀수라면 선공이 짝수라면 후공이 승리한다
    println(if (n % 2 == 0) "CY" else "SK")
}
