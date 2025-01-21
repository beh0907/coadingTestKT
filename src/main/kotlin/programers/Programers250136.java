package programers;

import java.util.*;

//석유시추
//https://school.programmers.co.kr/learn/courses/30/lessons/250136
public class Programers250136 {
    class Solution {
        int[] dirX = {-1, 1, 0, 0};
        int[] dirY = {0, 0, -1, 1};

        class Point {
            int x;
            int y;
            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int solution(int[][] land) {
            int n = land.length;
            int m = land[0].length;
            // 각 열별 석유량을 저장할 배열
            int[] columnOil = new int[m];
            // 석유 덩어리의 ID를 저장할 배열
            int[][] oilGroupId = new int[n][m];
            // 각 석유 덩어리의 크기를 저장할 Map
            Map<Integer, Integer> oilSizes = new HashMap<>();

            int groupId = 1;

            // 전체 맵을 한 번만 탐색하면서 모든 석유 덩어리를 찾아냄
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(land[i][j] == 1 && oilGroupId[i][j] == 0) {
                        // 새로운 석유 덩어리를 발견하면 BFS로 탐색
                        bfs(land, i, j, groupId, oilGroupId, oilSizes);
                        groupId++;
                    }
                }
            }

            // 각 열별로 포함된 석유 덩어리들의 크기를 합산
            for(int j = 0; j < m; j++) {
                Set<Integer> usedGroups = new HashSet<>();
                for(int i = 0; i < n; i++) {
                    int currentGroupId = oilGroupId[i][j];
                    if(currentGroupId > 0 && !usedGroups.contains(currentGroupId)) {
                        columnOil[j] += oilSizes.get(currentGroupId);
                        usedGroups.add(currentGroupId);
                    }
                }
            }

            // 최대값 찾기
            int maxOil = 0;
            for(int oil : columnOil) {
                maxOil = Math.max(maxOil, oil);
            }

            return maxOil;
        }

        private void bfs(int[][] land, int startX, int startY, int groupId,
                         int[][] oilGroupId, Map<Integer, Integer> oilSizes) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(startX, startY));
            oilGroupId[startX][startY] = groupId;
            int size = 0;

            while(!queue.isEmpty()) {
                Point curr = queue.poll();
                size++;

                for(int i = 0; i < 4; i++) {
                    int newX = curr.x + dirX[i];
                    int newY = curr.y + dirY[i];

                    if(newX < 0 || newX >= land.length || newY < 0 || newY >= land[0].length) continue;
                    if(land[newX][newY] == 0 || oilGroupId[newX][newY] != 0) continue;

                    queue.add(new Point(newX, newY));
                    oilGroupId[newX][newY] = groupId;
                }
            }
            oilSizes.put(groupId, size);
        }
    }
}
