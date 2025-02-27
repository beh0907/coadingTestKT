package backjoon

import java.util.Stack

//탑
//https://www.acmicpc.net/problem/2493
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val tops = readLine().split(" ").map { it.toInt() }
    val result = IntArray(N)

    // 스택에는 [인덱스, 높이] 쌍을 저장
    val stack = Stack<Pair<Int, Int>>()

    for (i in 0 until N) {
        val top = tops[i]

        // 현재 탑보다 낮은 탑들은 스택에서 제거
        while (stack.isNotEmpty() && stack.peek().second < top) {
            stack.pop()
        }

        // 스택이 비어있지 않다면, 스택의 top이 현재 탑의 신호를 수신할 수 있는 탑
        if (stack.isNotEmpty()) {
            result[i] = stack.peek().first + 1 // 인덱스는 0부터 시작하므로 +1
        }

        // 현재 탑을 스택에 추가
        stack.push(Pair(i, top))
    }

    println(result.joinToString(" "))
}
