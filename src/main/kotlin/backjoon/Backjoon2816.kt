package backjoon

//디지털 티비
//https://www.acmicpc.net/problem/2816
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var kbs1Index = 0
    var kbs2Index = 0
    var result = ""

    //채널 할당
    repeat(n) {
        val str = readLine()

        if (str == "KBS1") kbs1Index = it
        if (str == "KBS2") kbs2Index = it
    }

    //KBS1 찾으러 이동
    repeat(kbs1Index) { result += "1" }
    //KBS1 첫번째 칸으로 오도록 이동
    repeat(kbs1Index) { result += "4" }


    if (kbs1Index > kbs2Index) kbs2Index++;
    if (kbs2Index != 1) {
        //KBS2 찾으러 이동
        repeat(kbs2Index) { result += "1" }
        //KBS2 두번째 칸으로 오도록 이동
        repeat(kbs2Index - 1) { result += "4" }
    }

    println(result)
}
