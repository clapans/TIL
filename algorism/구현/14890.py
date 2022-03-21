import sys

def slope(way):
    tmp = way[0]
    cnt = 1
    use = [0]*n
    while cnt < n:
        h = abs(way[cnt] - tmp)
        if h > 1:
            return False
        elif h == 1:
            if way[cnt] < tmp:
                if use[cnt:cnt+l] != [0]*l or way[cnt:cnt+l] != [way[cnt]]*l:
                    return False
                use[cnt:cnt+l] = [1]*l
                tmp = way[cnt]
                cnt += l
                continue
            else:
                if use[cnt-l:cnt] != [0]*l or way[cnt-l:cnt] != [tmp]*l:
                    return False
                use[cnt-l:cnt] = [1]*l
                tmp = way[cnt]
        cnt += 1
    return True
    
n,l = map(int,sys.stdin.readline().split())
arr = []
for _ in range(n):
    arr.append(list(map(int,sys.stdin.readline().split())))

res = 0

for t in arr:
    if slope(t):
        res += 1

for t in zip(*arr):
    if slope(list(t)):
        res += 1

print(res)