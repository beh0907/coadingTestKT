package backjoon

//컨베이어 벨트 위의 로봇
//https://www.acmicpc.net/problem/20055
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(' ').map(String::toInt)
    val belt = ArrayDeque<Node20055>(readLine().split(' ').map { Node20055(it.toInt(), false) })
    var result = 0

    while (true) {
        result++

        //1.벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
        belt.addFirst(belt.removeLast())
        belt[N - 1].isOnRobot = false

        //2.가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
        for (i in N - 2 downTo 0) {
            if (!belt[i].isOnRobot) continue

            //2-1.로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
            if (belt[i + 1].isOnRobot || belt[i + 1].durability == 0) continue

            //로봇 이동
            belt[i].isOnRobot = false
            belt[i + 1].isOnRobot = true
            belt[i + 1].durability--

            // 이동한 위치가 내리는 위치이면 로봇을 내린다
            if (i + 1 == N - 1) belt[i + 1].isOnRobot = false
        }

        //3.올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        if (belt[0].durability != 0) {
            belt[0].isOnRobot = true
            belt[0].durability--
        }

        //4.내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
        if (belt.count { it.durability == 0 } >= K) break
    }

    println(result)
}

data class Node20055(
    var durability: Int,
    var isOnRobot: Boolean
)
