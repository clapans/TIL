import sys

s1 = sys.stdin.readline().strip()
s2 = sys.stdin.readline().strip()
s3 = sys.stdin.readline().strip()

dp = [[[0 for i in range(len(s3)+1)] for j in range(len(s2)+1)] for k in range(len(s1)+1)]
res = 0

for i in range(1,len(s1)+1):
    for j in range(1,len(s2)+1):
        for k in range(1,len(s3)+1):
            if s1[i-1] == s2[j-1] and s2[j-1] == s3[k-1]:
                dp[i][j][k] = dp[i-1][j-1][k-1] + 1
                res = max(res,dp[i][j][k])
            else:
                dp[i][j][k] = max(dp[i-1][j][k],dp[i][j-1][k],dp[i][j][k-1]) 
print(res)