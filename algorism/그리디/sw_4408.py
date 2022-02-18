for case in range(int(input())):
    n = int(input())
    greed = []
    arr = []
    for t in range(n):
        arr.append(sorted(list(map(int,input().split()))))
    arr.sort(key = lambda x : x[0])
    for t in arr:
        s = t[0] if t[0] % 2 else t[0] - 1
        e = t[1]+1 if t[1] % 2 else t[1]
        for i in range(len(greed)):
            for d in range(s,e+1):
                if d in greed[i]:
                    break
            else:
                ch = 0
                greed[i] += [_ for _ in range(s,e+1)]
                break
        else:
            greed.append([_ for _ in range(s,e+1)])
    print(greed)
    print(f'#{case+1} {len(greed)}')