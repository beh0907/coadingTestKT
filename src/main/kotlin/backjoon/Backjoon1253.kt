package backjoon

//좋다
//https://www.acmicpc.net/problem/1253
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val array = readLine().split(" ").map { it.toInt() }.sorted()
    var result = 0

    for (i in array.indices) {
        val target = array[i]
        var (left, right) = 0 to N - 1

        while (left < right) {
            val sum = array[left] + array[right]

            //타겟 값을 중복으로 사용하지 않도록 방지
            when (i) {
                left -> {
                    left++
                    continue
                }
                right -> {
                    right--
                    continue
                }
            }

            //두 수의 값과 타겟을 비교한다
            when {
                sum == target -> {
                    result++
                    break
                }
                sum > target -> right--
                sum < target -> left++
            }
        }
    }

    println(result)
}
