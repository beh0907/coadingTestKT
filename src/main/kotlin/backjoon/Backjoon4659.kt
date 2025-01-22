package backjoon

//비밀번호 발음하기
//https://www.acmicpc.net/problem/4659
fun main() = with(System.`in`.bufferedReader()) {
    val vowels = arrayOf('a', 'e', 'i', 'o', 'u') //모음 배열

    while (true) {
        val str = readLine()

        if (str == "end") return

        var condition1 = false // 모음 한개 이상 포함
        var condition2 = true // 자음 혹은 모음은 3개 연속일 수 없다
        var condition3 = true // 같은 문자는 연속 불가 (ee,oo 허용)

        var temp1 = str[0] to 0
        var temp2 = true to 0

        for (index in 0 until str.length) {
            val char = str[index]
            val isVowels = char in vowels

            if (char != temp1.first) temp1 = char to index
            if (isVowels != temp2.first) temp2 = isVowels to index

            //조건 처리
            if (isVowels) condition1 = true
            if (temp1.second != index && temp1.first != 'e' && temp1.first != 'o') {
                condition2 = false
                break
            }
            if (index >= temp2.second + 2) {
                condition3 = false
                break
            }
        }

        println(
            "<$str> is " +
                    if (!condition1 || !condition2 || !condition3) "not acceptable." else "acceptable."
        )
    }
}
