package backjoon

//한 줄로 서기
//https://www.acmicpc.net/problem/1138
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val array = readLine().split(" ").map(String::toInt)
    val result = mutableListOf<Int>()

    // 뒤에서부터 순회하며 적절한 위치에 삽입
    for (i in N - 1 downTo 0) {
        result.add(array[i], i + 1)
    }

    println(result.joinToString(" "))
}
