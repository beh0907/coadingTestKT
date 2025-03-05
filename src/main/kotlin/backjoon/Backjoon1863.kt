package backjoon

import java.util.Stack

//스카이라인 쉬운거
//https://www.acmicpc.net/problem/1863
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val stack = Stack<Int>()
    var result = 0

    repeat(N) {
        val (x, y) = readLine().split(' ').map { it.toInt() }

        // 스택의 top보다 낮은 높이가 들어오면 그 위의 건물들 카운트
        while (stack.isNotEmpty() && stack.peek() > y) {
            result++
            stack.pop()
        }

        // 중복된 높이는 건너뛰기
        if (stack.isEmpty() || stack.peek() != y) {
            if (y > 0) {
                stack.push(y)
            }
        }
    }

    // 스택에 남아있는 건물들 카운트
    result += stack.size

    println(result)
}
