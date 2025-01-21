package backjoon

//경로찾기
//https://www.acmicpc.net/problem/11403
//플로이드 와샬
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    //지도 사이즈를 얻어온다
    val n = readln().toInt()
    val graph = Array(n) { intArrayOf() }

    repeat(n) { i ->
        graph[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    repeat(n) { k ->
        repeat(n) { i ->
            repeat(n) { j ->
                // 단순히 갈 수 있는 경로가 있는지만 체크함.
                if (graph[i][k] == 1 && graph[k][j] == 1) {
                    graph[i][j] = 1;
                }
            }
        }
    }

    //결과 출력
    repeat(n) { i ->
        repeat(n) { j ->
            print("${graph[i][j]} ")
        }
        println()
    }
}
