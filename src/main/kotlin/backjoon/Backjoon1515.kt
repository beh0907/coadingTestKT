package backjoon

//수 이어 쓰기
//https://www.acmicpc.net/problem/1515
fun main() = with(System.`in`.bufferedReader()) {
    val str = readLine()
    var num = 1
    var index = 0

    while (true) {
        val current = num.toString()

        for (digit in current)
            if (index < str.length && digit == str[index]) index++


        if (index == str.length) break

        num++
    }

    println(num)
}
