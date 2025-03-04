package backjoon

import java.util.PriorityQueue

val queue = PriorityQueue<String>()
val operators = listOf('-', '+', ' ')

//0 만들기
//https://www.acmicpc.net/problem/7490
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val bw = System.out.bufferedWriter()

    repeat(N) {
        dfs7490(readLine().toInt(), 1, "1")

        while (queue.isNotEmpty()) {
            bw.write("${queue.poll()}\n")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}

fun dfs7490(n: Int, cnt: Int, str: String) {
    if (n == cnt) {
        if (calculateZero(str)) queue.add(str)
        return
    }

    for (operator in operators) dfs7490(n, cnt + 1, "$str$operator${cnt + 1}")
}

fun calculateZero(str: String): Boolean {
    var sum = 0
    var operator = '+'
    var temp = 0

    // 문자열 끝에 가상의 연산자를 추가하여 마지막 숫자 처리를 단순화
    val expression = "$str+"

    for (i in expression.indices) {

        when {
            //숫자라면 저장
            expression[i].isDigit() -> temp = (temp * 10) + expression[i].digitToInt()

            // + 또는 - 연산자를 만났을 때
            expression[i] == '+' || expression[i] == '-' -> {
                // 이전 연산자에 따라 누적된 숫자를 합산
                if (operator == '+') sum += temp else sum -= temp

                // 다음 숫자를 위한 초기화
                temp = 0
                operator = expression[i]
            }
        }
    }

    return sum == 0
}
