import sys

n = int(sys.stdin.readline())
tree = [[]] + [[] for t in range(n)]

for t in range(n):
    tmp = list(map(int,sys.stdin.readline().split()))
    for v in range(1,len(tmp)-1,2):
        tree[tmp[0]].append(tmp[v:v+2])

def dfs(x,val):
    global res, ix
    ch = 0
    for t in tree[x]:
        if visit[t[0]] == 0:
            visit[t[0]] = 1
            dfs(t[0],val+t[1])
            visit[t[0]] = 0
            ch = 1
    if ch == 0:
        if val > res:
            res = val
            ix = x

res,ix = 0,0
for t in range(1,n+1):
    if len(tree[t]) == 1:
        visit = [0] * (n+1)
        visit[t] = 1
        dfs(t,0)
        break

visit = [0] * (n+1)
visit[ix] = 1
dfs(ix,0)
print(res)
    