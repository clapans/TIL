from collections import deque
import sys
import copy

a,b = map(int,sys.stdin.readline().split())
area = []
dx = [1,-1,0,0]
dy = [0,0,1,-1]

for i in range(a):
    area.append(list(map(int,sys.stdin.readline().split())))

for i in range(a):
    for j in range(b):
        if area[i][j] == 0:
            queue = deque([[i,j]])
            while queue:
                tmp = queue.popleft()
                for t in range(4):
                    nx = tmp[0] + dx[t]
                    ny = tmp[1] + dy[t]
                    if 0 <= nx < a and 0 <= ny < b and area[nx][ny] > 0:
                        area[nx][ny] -= 1
                        queue.append([nx,ny])
                        