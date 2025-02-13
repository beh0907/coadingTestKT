package backjoon

//서로 다른 부분 문자열의 개수
//https://www.acmicpc.net/problem/11478
fun main() = with(System.`in`.bufferedReader()) {
    val set = HashSet<String>()
    val str = readLine()

    for (i in 0 until str.length)
        for (j in i until str.length) set.add(str.substring(i, j + 1))

    println(set.size)
}
