package backjoon

import kotlin.io.println
import kotlin.system.exitProcess

private val visited = Array(9) { false }
private val values = Array(9) { 0 }

//백설 공주와 일곱 난쟁이
//https://www.acmicpc.net/problem/3040
fun main(): Unit = with(System.`in`.bufferedReader()) {
    repeat(9) { i ->
        values[i] = readln().toInt()
    }

    repeat(9) { i ->
        visited[i] = true
        dfs(values[i], arrayListOf(values[i]))
        visited[i] = false
    }
}

fun dfs(sum: Int, result: ArrayList<Int>) {
    if (result.size > 7) return

    if (result.size == 7 && sum == 100) {
        result.reversed().forEach {
            println(it)
        }
        exitProcess(0)
    }

    repeat(9) { i ->
        //이미 방문한 난쟁이는 리턴
        if (visited[i]) return

        visited[i] = true
        result.add(values[i])

        dfs(sum + values[i], result)

        visited[i] = false
        result.removeLast()
    }

}
