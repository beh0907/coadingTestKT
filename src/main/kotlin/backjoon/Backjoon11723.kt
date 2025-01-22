package backjoon

//집합
//https://www.acmicpc.net/problem/11723
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var S = 0 // 비트마스크로 사용할 정수

    val bw = System.out.bufferedWriter()

    //명령어 처리
    repeat(n) {
        val str = readLine().split(" ")
        //배열이 아닌 비트마스크를 처리함으로 런타임 성능 향상
        when (str[0]) {
            "add" -> S = S or (1 shl (str[1].toInt() - 1))
            "remove" -> S = S and (1 shl (str[1].toInt() - 1)).inv()
            "check" -> bw.write(if (S and (1 shl (str[1].toInt() - 1)) != 0) "1\n" else "0\n")
            "toggle" -> S = S xor (1 shl (str[1].toInt() - 1))
            "all" -> S = (1 shl 20) - 1
            "empty" -> S = 0
        }
    }
    bw.flush()
    bw.close()
}
