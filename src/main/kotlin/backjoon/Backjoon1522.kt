package backjoon

//문자열 교환
//https://www.acmicpc.net/problem/1522
fun main() = with(System.`in`.bufferedReader()) {
    val str = readLine()
    val aCount = str.count { it == 'a' }

    val doubledString = str + str
    var minSwaps = Int.MAX_VALUE

    for (i in 0 until str.length) {
        val windowString = doubledString.substring(i, i + aCount)
        val bCount = windowString.count { it == 'b' }

        minSwaps = minOf(minSwaps, bCount)
    }

    println(minSwaps)
}
