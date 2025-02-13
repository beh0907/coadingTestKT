package backjoon

//단축키 지정
//https://www.acmicpc.net/problem/1283
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val bw = System.out.bufferedWriter()

    val set = HashSet<Char>()
    set.add(' ')

    repeat(N) {
        val str = readLine()
        val spaceIndex = listOf(0) + str.withIndex()
            .filter { it.value == ' ' }
            .map { it.index + 1 }
        var keyIndex = -1

        for (i in spaceIndex) {
            val word = str[i].lowercaseChar()

            if (word !in set) {
                set.add(word)
                keyIndex = i
                break
            }
        }

        repeat(str.length) {
            val word = str[it].lowercaseChar()

            if (keyIndex == -1) {
                if (word !in set) {
                    set.add(word)
                    keyIndex = it
                }
            }

            if(it == keyIndex) bw.write("[${str[it]}]")
            else bw.write("${str[it]}")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}

