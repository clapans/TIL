import sys
import itertools

for T in range(int(sys.stdin.readline())):
    n = int(sys.stdin.readline())
    p = []
    res = (10**6) * 2**(1/2)
    for t in range(n):
        a,b = map(int,sys.stdin.readline().split())
        p.append([a,b])
    for t in itertools.combinations(p,n//2):
        tmp = [v for v in p if v not in t]
        for w in itertools.permutations(tmp):
            case = [0,0]
            for l,r in zip(t,w):
                case = [case[0]+r[0]-l[0],case[1]+r[1]-l[1]]
            res = min(res,pow(case[0]**2+case[1]**2,1/2))
    print(res)
