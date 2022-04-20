from collections import deque
import sys

n,k = map(int,sys.stdin.readline().split())
arr = []
dx = [1,-1,0,0]
dy = [0,0,1,-1]

def bfs():
    while queue:
        tmp = queue.popleft()
        if tmp[2] <= s:
            for t in range(4):
                nx = tmp[0] + dx[t]
                ny = tmp[1] + dy[t]
                if 0 <= nx < n and 0 <= ny < n:
                    if visit[nx][ny] == tmp[2] + 1:
                        if arr[tmp[0]][tmp[1]] < arr[nx][ny]:
                            arr[nx][ny] = arr[tmp[0]][tmp[1]]
                            visit[nx][ny] = tmp[2] + 1
                            queue.append([nx,ny,tmp[2]+1])
                    elif visit[nx][ny] > tmp[2] + 1:
                        arr[nx][ny] = arr[tmp[0]][tmp[1]]
                        visit[nx][ny] = tmp[2] + 1
                        queue.append([nx,ny,tmp[2]+1])

for _ in range(n):
    arr.append(list(map(int,sys.stdin.readline().split())))

s,x,y = map(int,sys.stdin.readline().split())
visit = [[int(1e9)]*n for _ in range(n)]
queue = deque([])
for i in range(n):
    for j in range(n):
        if arr[i][j] > 0:
            queue.append([i,j,1])
            visit[i][j] = 1

bfs()
print(arr[x-1][y-1])