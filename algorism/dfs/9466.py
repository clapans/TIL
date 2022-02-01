import sys

sys.setrecursionlimit(10**6)
for case in range(int(sys.stdin.readline())):
    student = int(sys.stdin.readline())
    students = [0] + [1 for t in range(student)]
    collect = [0] + list(map(int,sys.stdin.readline().split()))
    res = set()
    def dfs(x):
        tmp = collect[x]
        if students[tmp] == 1:
            students[tmp] = 0
            v.append(tmp)
            dfs(tmp)
        else:
            try:
                for t in v[v.index(tmp):]:
                    res.add(t)
            except:
                pass

    for i in range(1,student+1):
        if students[i] == 1:
            v = [i]
            students[i] = 0
            dfs(i)
    print(student-len(res))