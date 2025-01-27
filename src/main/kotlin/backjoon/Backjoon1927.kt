package backjoon

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.PriorityQueue

//최소 힙
//https://www.acmicpc.net/problem/1927
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()

    val queue = PriorityQueue<Int>()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(N) {
        val value = readLine().toInt()

        if (value == 0) {
            if (queue.isEmpty()) bw.write("0\n")
            else bw.write("${queue.poll()}\n")
        } else queue.offer(value)
    }
    bw.flush()
    bw.close()
}
