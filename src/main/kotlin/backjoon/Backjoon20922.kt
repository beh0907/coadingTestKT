package backjoon

//겹치는 건 싫어
//https://www.acmicpc.net/problem/20922
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map(String::toInt)
    val array = readLine().split(" ").map(String::toInt)
    val hashMap = HashMap<Int, Int>()
    var left = 0
    var result = 0

    repeat(N) { right ->
        val num = array[right]
        while (hashMap.getOrDefault(num, 0) == K) {
            hashMap[array[left]] = hashMap.getOrDefault(array[left], 0) - 1
            left++
        }

        hashMap[num] = hashMap.getOrDefault(num, 0) + 1
        result = result.coerceAtLeast(right - left + 1)
    }

    println(result)
}
