package backjoon

val arrayCompletion = arrayOf(
    intArrayOf(0, 1, 2),
    intArrayOf(3, 4, 5),
    intArrayOf(6, 7, 8),
    intArrayOf(0, 3, 6),
    intArrayOf(1, 4, 7),
    intArrayOf(2, 5, 8),
    intArrayOf(0, 4, 8),
    intArrayOf(2, 4, 6),
)

//틱택토
//https://www.acmicpc.net/problem/7682
fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    while (true) {
        val str = readLine()
        if (str == "end") break

        //X,O횟수 체크
        var (cntX, cntO, cntE) = listOf(0, 0, 0)
        for (ch in str) {
            when (ch) {
                'X' -> cntX++
                'O' -> cntO++
                '.' -> cntE++
            }
        }
        //X와 O의 완성 여부
        val (boolX, boolO) = isCompletion(str)

        when {
            //둘 다 완성된 형태는 나올 수 없다
            boolX && boolO -> bw.write("invalid\n")

            //X가 완성됐다면 O보다 1 많아야 한다
            boolX && !boolO -> {
                if (cntX - cntO == 1) bw.write("valid\n")
                else bw.write("invalid\n")
            }

            //O가 완성됐다면 X와 같아야 한다
            !boolX && boolO -> {
                if (cntX - cntO == 0) bw.write("valid\n")
                else bw.write("invalid\n")
            }

            //둘 다 완성되지 않은 경우에는 칸이 모두 꽉차야 한다
            else -> {
                if (cntE == 0 && cntX - cntO == 1) bw.write("valid\n")
                else bw.write("invalid\n")
            }
        }
    }

    bw.flush()
    bw.close()
}

fun isCompletion(str: String): Pair<Boolean, Boolean> {
    var (boolX, boolO) = false to false

    for ((numA, numB, numC) in arrayCompletion) {
        if (str[numA] != '.' && str[numA] == str[numB] && str[numA] == str[numC]) {
            when (str[numA]) {
                'X' -> boolX = true
                'O' -> boolO = true
            }
        }

        if (boolX && boolO) break
    }
    return boolX to boolO
}
