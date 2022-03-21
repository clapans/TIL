import sys

dx = [0,1,0,-1]
dy = [-1,0,1,0]
back = {0: 2, 1: 3, 2: 0, 3: 1}

def robot(x,y,dir):
    global res
    if not arr[x][y]:
        arr[x][y] = 2
        res += 1
    for t in range(1,5):
        ndir = dir+t if dir+t < 4 else dir+t-4
        nx = x + dx[ndir]
        ny = y + dy[ndir]
        if 0 <= nx < n and 0 <= ny < m and arr[nx][ny] == 0:
            robot(nx,ny,ndir)
            break
    else:
        nx = x + dx[back[dir]]
        ny = y + dy[back[dir]]
        if 0 <= nx < n and 0 <= ny < m and arr[nx][ny] != 1:
            robot(nx,ny,dir)
            
n,m = map(int,sys.stdin.readline().split())
r,c,d = map(int,sys.stdin.readline().split())

arr = []
res = 0
for _ in range(n):
    arr.append(list(map(int,sys.stdin.readline().split())))

robot(r,c,3-d)
print(res)