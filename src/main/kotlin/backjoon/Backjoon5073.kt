package backjoon

//삼각형과 세 변
//https://www.acmicpc.net/problem/5073
fun main() = with(System.`in`.bufferedReader()) {

    while (true) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        val sum = a + b + c
        val max = maxOf(a, b, c)
        val min = minOf(a, b, c)

        //모두 0이라면 종료
        if (sum == 0) break

        //한변의 최대 길이가 나머지 두 변보다 크다면 성립X
        if (max >= sum - max) {
            println("Invalid")
            continue
        }

        //최대와 최소 길이가 같다면 정삼각형
        if (max == min) {
            println("Equilateral")
            continue
        }

        //모든 변의 크기가 다르다면
        if (a != b && a != c && b != c) {
            println("Scalene")
            continue
        }

        //나머지 이등변 삼각형
        println("Isosceles")
    }
}
