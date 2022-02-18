import heapq

for case in range(int(input())):
    n = int(input())
    arr = []
    for t in range(n):
        arr.append(sorted(list(map(int,input().split()))))
    arr.sort(key = lambda x : x[0])
    greed = []
    heapq.heappush(greed,arr[0][1])

    for t in arr[1:]:
        s = t[0] if t[0] % 2 else t[0] - 1
        e = t[1]+1 if t[1] % 2 else t[1]
        if s < greed[0]:
            heapq.heappush(greed,e)
        else:
            heapq.heappop(greed)
            heapq.heappush(greed,e)

    print(f'#{case+1} {len(greed)}')