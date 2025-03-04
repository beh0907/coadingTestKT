package backjoon

//전구와 스위치
//https://www.acmicpc.net/problem/2138
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val initial = readLine().toCharArray().map { it == '0' }.toBooleanArray()
    val target = readLine().toCharArray().map { it == '0' }.toBooleanArray()

    val cnt = minOf(getPressCount(initial.copyOf(), target, true), getPressCount(initial.copyOf(), target, false))

    println(
        if (cnt == Int.MAX_VALUE) - 1
        else cnt
    )
}

fun getPressCount(switches: BooleanArray, target: BooleanArray, start: Boolean): Int {
    var cnt = 0

    if (start) {
        pressSwitch(switches, 0)
        cnt++
    }

    for (i in 1 until switches.size) {
        // 이전 전구가 목표 상태와 다르면 스위치를 눌러야 함
        if (switches[i - 1] != target[i-1]) {
            pressSwitch(switches, i)
            cnt++
        }
    }

    //목표 값과 일치하다면 횟수 체크
    return if (switches.contentEquals(target)) cnt
    else Int.MAX_VALUE
}

//스위치 누르기
fun pressSwitch(array: BooleanArray, num: Int) {
    for (i in num - 1..num + 1) {
        if (i in array.indices) array[i] = !array[i]
    }
}
