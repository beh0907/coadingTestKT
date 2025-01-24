package backjoon

//스위치 켜고 끄기
//https://www.acmicpc.net/problem/1076
fun main() = with(System.`in`.bufferedReader()) {
    val switchCnt = readLine().toInt()
    val switches = readLine().split(" ").map { it == "1" }.toMutableList()
    val studentCnt = readLine().toInt()

    repeat(studentCnt) {
        val (gender, number) = readLine().split(" ").map(String::toInt)
        switches[number - 1] = !switches[number - 1]

        when (gender) {
            //남학생
            1 -> {
                for (i in (number * 2) - 1 until switchCnt step number)
                    switches[i] = !switches[i]
            }
            //여학생
            2 -> {
                var range = 1

                while (true) {
                    val prev = (number - 1) - range
                    val next = (number - 1) + range

                    if (prev in switches.indices && next in switches.indices && switches[prev] == switches[next]) range++
                    else break
                }

                for (i in 1 until range) {
                    switches[(number - 1) - i] = !switches[(number - 1) - i]
                    switches[(number - 1) + i] = !switches[(number - 1) + i]
                }
            }
        }
    }

    var result = ""
    repeat(switchCnt) {
        result += "${if (switches[it]) 1 else 0} "
        if ((it + 1) % 20 == 0 || it == switchCnt - 1) {
            println(result)
            result = ""
        }
    }
}
