package backjoon

import java.util.LinkedList

//에디터
//https://www.acmicpc.net/problem/1406
fun main() = with(System.`in`.bufferedReader()) {
    val str = readLine().toList()
    val N = readLine().toInt()
    val list = LinkedList(str)
    val iter = list.listIterator(list.size)

    repeat(N) {
        val command = readLine().split(" ")

        when (command[0]) {
            "L" -> if (iter.hasPrevious()) iter.previous()
            "D" -> if (iter.hasNext()) iter.next()
            "B" -> if (iter.hasPrevious()) {
                iter.previous()
                iter.remove()
            }

            "P" -> iter.add(command[1][0])
        }
    }

    println(list.toCharArray())
}
