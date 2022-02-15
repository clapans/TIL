import sys

s1 = sys.stdin.readline().rstrip()
s2 = sys.stdin.readline().rstrip()

dp = [[0]*(len(s2)+1) for t in range(len(s1)+1)]

for i in range(1,len(s1)+1):
    for j in range(1,len(s2)+1):
        if s1[i-1] == s2[j-1]:
            dp[i][j] = dp[i-1][j-1] + 1

print(max([max(t) for t in dp]))