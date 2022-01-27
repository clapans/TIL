import sys

a,b = map(int,sys.stdin.readline().split())

arr = []
for i in range(a):
    arr.append(list(sys.stdin.readline().rstrip()))

res = 0
dir = [-1,0,1]
visit = [[0 for t in range(b)] for v in range(a)]

def dfs(x,y):
    if y == b-1:
        return True
    ny = y + 1
    for i in range(3):
        nx = x + dir[i]
        if ny < b and 0 <= nx < a and visit[nx][ny] == 0 and arr[nx][ny] == '.':
            visit[nx][ny] = 1
            if dfs(nx,ny):
                return True
    return False

for i in range(a):
    if dfs(i,0):
        res += 1

print(res)
