package backjoon


//타노스
//https://www.acmicpc.net/problem/20310
fun main() = with(System.`in`.bufferedReader()) {
    val str = readLine()
    val result = StringBuilder()

    var cntZero = str.count { it == '0' } / 2
    var cntOne = str.count { it == '1' } / 2

    for (ch in str) {
        if (cntZero > 0 && ch == '0' || cntOne == 0 && ch == '1') result.append(ch)

        when {
            ch == '0' && cntZero > 0 -> cntZero--
            ch == '1' && cntOne > 0 -> cntOne--
        }
    }

    println(result)
}
