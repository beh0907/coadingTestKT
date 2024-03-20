import kotlin.math.max

fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val size = readln().toInt()
    val map = Array(size) { charArrayOf() }

    //맵 정보 추가
    for (i in 0..<size) map[i] = readLine().toCharArray()

    //결과
    var result = 1

    repeat(size) { x ->
        repeat(size) { y ->
            //x가 밖으로 나가지 않았다면
            if (x + 1 < size) {
                //스왑
                swap(map, x, y, x + 1, y)

                //가장 긴 사탕 길이 구하기
                result = max(result, getLongestCandy(map))

                //스왑한 맵 원상복귀
                swap(map, x + 1, y, x, y)
            }


            //y가 밖으로 나가지 않았다면
            if (y + 1 < size) {
                //스왑
                swap(map, x, y, x, y + 1)

                //가장 긴 사탕 길이 구하기
                result = max(result, getLongestCandy(map))

                //스왑한 맵 원상복귀
                swap(map, x, y + 1, x, y)
            }

        }
    }


    println(result)
}

fun swap(map: Array<CharArray>, x1: Int, y1: Int, x2: Int, y2: Int) {
    val temp = map[x1][y1]
    map[x1][y1] = map[x2][y2]
    map[x2][y2] = temp
}

fun getLongestCandy(map: Array<CharArray>): Int {
    var result = 0

    //행 탐색
    repeat(map.size) { x ->
        var count = 1
        repeat(map.size - 1) { y ->
            if (map[x][y] == map[x][y + 1]) {
                count++
                result = max(count, result)
            } else {
                count = 1
            }
        }
    }

    //열 탐색
    repeat(map.size) { y ->
        var count = 1
        repeat(map.size - 1) { x ->
            if (map[x][y] == map[x + 1][y]) {
                count++
                result = max(count, result)
            } else {
                count = 1
            }
        }
    }

    return result
}
