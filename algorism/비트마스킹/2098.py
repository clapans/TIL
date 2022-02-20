import sys

n = int(sys.stdin.readline())
cost = []
res = 0
for t in range(n):
    lst = list(map(int,sys.stdin.readline().split()))
    cost.append(lst)
    res += sum(lst)

def dfs(x,visit):
    if visit == 2**n - 1:
        if cost[x][0]:
            return cost[x][0]
        return res
    if dp[x][visit] != res:
        return dp[x][visit]
    for t in range(n):
        if cost[x][t] and not visit & (1 << t):
            dp[x][visit] = min(dp[x][visit],dfs(t,visit | (1 << t)) + cost[x][t])
    return dp[x][visit]

dp = [[res]*(2**n) for _ in range(n)]
print(dfs(0,1))