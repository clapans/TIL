import sys

s = sys.stdin.readline().rstrip()
res,cnt = 0,0

while cnt < len(s):
    j = cnt
    tmp = s[cnt:]
    arr = [0] * len(tmp)
    for i in range(1,len(tmp)):
        while j > 0 and tmp[i] != tmp[j]:
            j = arr[j-1]
        if tmp[i] == tmp[j]:
            j += 1
            arr[i] = j
    res = max(res,max(arr))
    cnt += 1

print(res)