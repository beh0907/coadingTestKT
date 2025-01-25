package backjoon

//블로그
//https://www.acmicpc.net/problem/21921
fun main() = with(System.`in`.bufferedReader()) {
    val (N, X) = readLine().split(" ").map(String::toInt)
    val array = readLine().split(" ").map(String::toInt)

    var sum = array.slice(0 until X).sum()
    var max = sum
    var cnt = 1

    for (i in X until N) {
        sum = sum - array[i - X] + array[i]

        if (sum > max) {
            max = sum
            cnt = 1
        } else if (sum == max) cnt++
    }

    print(if (max == 0) "SAD" else "$max\n$cnt")
}
