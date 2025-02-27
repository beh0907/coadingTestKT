package backjoon

//문자열 게임 2
//https://www.acmicpc.net/problem/20437
fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    repeat(readLine().toInt()) {
        val W = readLine()
        val N = readLine().toInt()

        var min = Int.MAX_VALUE
        var max = -1

        val map = mutableMapOf<Char, MutableList<Int>>()
        W.forEachIndexed { i, ch ->
            if (!map.containsKey(ch)) map[ch] = mutableListOf()
            map[ch]?.add(i)
        }

        map.forEach { (key, value) ->
            if (value.size >= N) {
                for (i in 0..value.size - N) {
                    val length = value[i + N - 1] - value[i] + 1
                    min = minOf(min, length)
                    max = maxOf(max, length)
                }
            }
        }

        if (max == -1) bw.write("-1\n")
        else bw.write("$min $max\n")
    }

    bw.flush()
    bw.close()
}
