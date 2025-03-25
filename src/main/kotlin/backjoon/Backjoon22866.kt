package backjoon

import java.util.Stack

//탑 보기
//https://www.acmicpc.net/problem/22866
fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()
    val N = readLine().toInt()
    val buildings = readLine().split(" ").map { it.toInt() }

    val leftStack = Stack<Int>()
    val leftVisible = IntArray(N) { 0 }
    val leftNearest = IntArray(N) { -1 }

    // 왼쪽에서 오른쪽으로 스�닝 (왼쪽에 보이는 건물들 확인)
    for (i in 0 until N) {
        while (leftStack.isNotEmpty() && buildings[leftStack.peek()] <= buildings[i]) {
            leftStack.pop()
        }
        leftVisible[i] = leftStack.size
        if (leftStack.isNotEmpty()) {
            leftNearest[i] = leftStack.peek()
        }
        leftStack.push(i)
    }

    val rightStack = Stack<Int>()
    val rightVisible = IntArray(N) { 0 }
    val rightNearest = IntArray(N) { -1 }

    // 오른쪽에서 왼쪽으로 스캔 (오른쪽에 보이는 건물들 확인)
    for (i in N - 1 downTo 0) {
        while (rightStack.isNotEmpty() && buildings[rightStack.peek()] <= buildings[i]) {
            rightStack.pop()
        }
        rightVisible[i] = rightStack.size
        if (rightStack.isNotEmpty()) {
            rightNearest[i] = rightStack.peek()
        }
        rightStack.push(i)
    }

    //결과 출력
    for (i in 0 until N) {
        val total = leftVisible[i] + rightVisible[i]
        bw.write("$total")

        if (total > 0) {
            val leftDist = if (leftNearest[i] != -1) i - leftNearest[i] else Int.MAX_VALUE
            val rightDist = if (rightNearest[i] != -1) rightNearest[i] - i else Int.MAX_VALUE

            when {
                leftNearest[i] == -1 -> bw.write(" ${rightNearest[i] + 1}")
                rightNearest[i] == -1 -> bw.write(" ${leftNearest[i] + 1}")
                leftDist <= rightDist -> bw.write(" ${leftNearest[i] + 1}")
                else -> bw.write(" ${rightNearest[i] + 1}")
            }
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}
