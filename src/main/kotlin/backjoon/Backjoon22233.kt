package backjoon

//가희와 키워드
//https://www.acmicpc.net/problem/22233
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(' ').map(String::toInt)
    val set = HashSet<String>()
    val result = StringBuilder()

    repeat(N) {
        set.add(readLine())
    }

    repeat(M) {
        readLine().split(",").map { set.remove(it) }

        result.append(set.size).append("\n")
    }

    println(result)
}
