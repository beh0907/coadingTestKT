import java.util.*

//촌수 계산
//https://www.acmicpc.net/problem/2644
fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val (person1, person2) = readln().split(" ").map { it.toInt() - 1 }
    val m = readln().toInt()

    val families = Array(n) { Person(null, ArrayList()) }
    repeat(m) { i ->
        val (parent, child) = readln().split(" ").map { it.toInt() - 1 }
        families[child].parent = parent
        families[parent].child.add(child)
    }

    println(bfs(families, person1, person2))
}

private fun bfs(families: Array<Person>, start: Int, end: Int): Int {
    val visited = BooleanArray(families.size)
    visited[start] = true

    val queue: Queue<Pair<Int, Int>> = LinkedList()

    //first = 사람 위치, second = 촌 수
    queue.offer(Pair(start, 0))
    while (queue.isNotEmpty()) {
        val pair = queue.poll()
        val person = families[pair.first]

        //부모 정보 추가
        if (person.parent != null && !visited[person.parent!!]) {
            if (person.parent == end) return pair.second + 1

            //방문 처리
            visited[person.parent!!] = true

            //위치와 촌수를 추가한다
            queue.offer(Pair(person.parent!!, pair.second + 1))
        }


        //자식 정보 추가
        person.child.forEach { pos ->
            if (pos == end) return pair.second + 1

            //방문한 이력이 있다면 continue
            if (visited[pos]) return@forEach

            //방문 처리
            visited[pos] = true

            //위치와 촌수를 추가한다
            queue.offer(Pair(pos, pair.second + 1))
        }
    }

    return -1
}

data class Person(var parent: Int?, var child: ArrayList<Int> = arrayListOf())
