package backjoon

//비슷한 단어
//https://www.acmicpc.net/problem/2179
fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val words = mutableListOf<String>()

    // 입력 받기
    repeat(N) {
        words.add(readLine())
    }

    // 정답 후보
    var result1 = ""
    var result2 = ""
    var maxPrefix = -1
    var firstIndex = Int.MAX_VALUE

    // 모든 단어 쌍에 대해 검사
    for (i in 0 until N) {
        for (j in i + 1 until N) {
            val word1 = words[i]
            val word2 = words[j]

            // 공통 접두사 길이 계산
            var prefixLen = 0
            for (k in 0 until minOf(word1.length, word2.length)) {
                if (word1[k] == word2[k]) prefixLen++
                else break
            }

            // 업데이트 조건 (더 긴 접두사 또는 같은 길이면서 더 먼저 등장한 단어)
            if (prefixLen > maxPrefix || (prefixLen == maxPrefix && i < firstIndex)) {
                maxPrefix = prefixLen
                result1 = word1
                result2 = word2
                firstIndex = i
            }
        }
    }

    println("$result1\n$result2")
}
