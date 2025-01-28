package backjoon

import kotlin.math.abs

//비슷한 단어
//https://www.acmicpc.net/problem/2607
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    val first = readLine()
    val firstAlphabet = getAlphabet(first)
    var result = 0

    repeat(N - 1) {
        val word = readLine()
        val wordAlphabet = getAlphabet(word)

        val lengthDiff = abs(first.length - word.length)
        if (lengthDiff > 1) return@repeat

        var alphabetDiff = 0
        var countDiff = 0
        repeat(26) {
            val diff = abs(firstAlphabet[it] - wordAlphabet[it])
            alphabetDiff += diff

            if (diff > 0) countDiff++
        }

        if (alphabetDiff == 0) result++
        else if (alphabetDiff == 1 && lengthDiff == 1) result++
        else if (alphabetDiff == 2 && countDiff == 2 && lengthDiff == 0) result++
    }

    println(result)
}

fun getAlphabet(str: String): IntArray {
    val array = IntArray(26) { 0 }

    for (ch in str) array[ch - 'A']++

    return array
}
