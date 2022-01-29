import sys

n = int(sys.stdin.readline())
arr = [[] for t in range(n)]

for i in range(n):
    tmp = list(map(int,sys.stdin.readline().split()))
    for j in range(n):
        if tmp[j] == 1:
            arr[i].append(j)

def dfs(x):
    for t in arr[x]:
        if visit[t] == 0:
            visit[t] = 1
            dfs(t)

for i in range(n):
    visit = [0]*n
    dfs(i)
    print(*visit)