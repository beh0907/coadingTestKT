package backjoon

import java.io.BufferedWriter
import java.io.OutputStreamWriter

//영단어 암기는 괴로워
//https://www.acmicpc.net/problem/20920
fun main() = with(System.`in`.bufferedReader()) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = readLine().split(' ').map(String::toInt)
    val words = HashMap<String, Int>()

    repeat(N) {
        val word = readLine()
        if (word.length >= M) words[word] = words.getOrDefault(word, 0) + 1
    }

    words.entries
        .sortedWith(
            compareByDescending<Map.Entry<String, Int>> { it.value }
                .thenByDescending { it.key.length }
                .thenBy { it.key }
        )
        .forEach { bw.write("${it.key}\n") }

    bw.flush()
    bw.close()
}
