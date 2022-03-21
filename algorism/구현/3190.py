import sys
from collections import deque

n = int(sys.stdin.readline())
k = int(sys.stdin.readline())
arr = [[0]*n for _ in range(n)]
for _ in range(k):
    i,j = map(int,sys.stdin.readline().split())
    arr[i-1][j-1] = 1

l = int(sys.stdin.readline())
rotate = deque([])
for _ in range(l):
    x,c = sys.stdin.readline().split()
    rotate.append([int(x),c])

snake = deque([[0,0]])
d = 0

dx = [0,1,0,-1]
dy = [1,0,-1,0]
cnt = 0

while True:
    x,y = snake[-1]
    nx = x + dx[d]
    ny = y + dy[d]
    if 0 <= nx < n and 0 <= ny < n and [nx,ny] not in snake:
        snake.append([nx,ny])
        if arr[nx][ny] == 1:
            arr[nx][ny] = 0
        else:
            snake.popleft()
    else:
        print(cnt+1)
        quit(0)
    cnt += 1
    if rotate and cnt == rotate[0][0]:
        tmp = rotate.popleft()
        if tmp[1] == 'D':
            d = d+1 if d < 3 else 0
        else:
            d = d-1 if d > 0 else 3