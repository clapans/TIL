import sys
from collections import deque

dx = [1,-1,0,0]
dy = [0,0,1,-1]

def bfs(x,y):
    tmp = []
    queue = deque([[x,y]])
    while queue:
        ch = 0
        i,j = queue.popleft()
        for t in range(4):
            nx = i + dx[t]
            ny = j + dy[t]
            if 0 <= nx < n and 0 <= ny < m:
                if visit[nx][ny] == 0 and arr[nx][ny] == '1':
                    visit[nx][ny] = 1
                    queue.append([nx,ny])
                elif arr[nx][ny] == '0':
                    ch = 1
        if ch:
            tmp.append([i,j])
    return tmp

def compare(x,y):
    tmp = int(1e9)
    for i in x:
        for j in y:
            if i[0] == j[0] and abs(i[1] - j[1]) - 1 > 1:
                for k in range(min(i[1],j[1])+1,max(i[1],j[1])):
                    if arr[i[0]][k] == '1':
                        break
                else:
                    tmp = min(tmp,abs(i[1] - j[1]) - 1)
            elif i[1] == j[1] and abs(i[0] - j[0]) - 1 > 1:
                for k in range(min(i[0],j[0])+1,max(i[0],j[0])):
                    if arr[k][i[1]] == '1':
                        break
                else:
                    tmp = min(tmp,abs(i[0] - j[0]) - 1)
    return tmp

def pick(x,v):
    if x == len(able):
            
    else:    
        pick(x+1,v)
        v[able[x][1]] = able[x][2]
        pick(x+1,v)

n,m = map(int,sys.stdin.readline().split())
arr = []
for _ in range(n):
    arr.append(sys.stdin.readline().split())

border = []
visit = [[0]*m for _ in range(n)]

for i in range(n):
    for j in range(m):
        if arr[i][j] == '1' and visit[i][j] == 0:
            visit[i][j] = 1
            border.append(bfs(i,j))
able = []

for i in range(len(border)-1):
    for j in range(i+1,len(border)):
        tmp = compare(border[i],border[j])
        if tmp < int(1e9):
            able.append([tmp,i,j])

