package backjoon

//문자열 폭발
//https://www.acmicpc.net/problem/9935
fun main() = with(System.`in`.bufferedReader()) {
    val str = readLine()
    val bomb = readLine()

    val stack = ArrayDeque<Char>(str.length)
    val bombLength = bomb.length

    for (char in str) {
        stack.addLast(char)

        // 스택의 크기가 폭탄 문자열 길이 이상이고 현재 스택 끝부분이 폭탄과 일치하는지 확인
        if (stack.size >= bombLength) {
            var match = true

            // 스택의 마지막 부분이 폭탄 문자열과 일치하는지 확인
            for (i in 0 until bombLength) {
                if (stack[stack.size - bombLength + i] != bomb[i]) {
                    match = false
                    break
                }
            }

            // 일치한다면 폭탄 길이만큼 스택에서 제거
            if (match) {
                repeat(bombLength) {
                    stack.removeLast()
                }
            }
        }
    }

    // 결과 구성
    val result = if (stack.isEmpty()) "FRULA" else stack.joinToString("")
    println(result)
}
