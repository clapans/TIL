import sys
from collections import deque

for case in range(int(sys.stdin.readline())):
    n,k = map(int,sys.stdin.readline().split())
    spent = [0] + list(map(int,sys.stdin.readline().split()))
    sequence = [[] for t in range(n+1)]
    
    for i in range(k):
        a,b = map(int,sys.stdin.readline().split())
        sequence[b].append(a)
    num = int(sys.stdin.readline())
    queue = deque([[num,0]])
    save = []

    while queue:
        tmp = queue.popleft()
        try:
            save[tmp[1]]
        except: 
            save.append([])
        for t in sequence[tmp[0]]:
            queue.append([t,tmp[1]+1])
            save[tmp[1]].append(t)
    while len(save[-1]) == 0:
        save.pop()
    res = 0
    use = []
    for i in save[::-1]:
        dict_ = {}
        for j in i:
            dict_[spent[j]] = j
        tmp = max([spent[t] for t in i if t not in use])
        res += tmp
        use.append(dict_[tmp])
    print(res+spent[num])