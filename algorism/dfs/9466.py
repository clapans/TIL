import sys

sys.setrecursionlimit(10**6)
for case in range(int(sys.stdin.readline())):
    student = int(sys.stdin.readline())
    students = [0] + [0 for t in range(student)]
    collect = [0] + list(map(int,sys.stdin.readline().split()))
    def dfs(x):
        tmp = collect[x]
        if students[tmp] == 0:
            pass
        else:
            if students[tmp] == 0:
                v.append(tmp)
                dfs(v)
            else:
                for t in v[v.index(tmp):]:
                    students[t] = 0
    for i in range(1,student+1):
        if students[i] == 1:
            dfs([i])
    print(sum(students))