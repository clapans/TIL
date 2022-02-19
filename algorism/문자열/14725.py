import sys

foods = []
res = 0

for _ in range(int(sys.stdin.readline())):
    food = sys.stdin.readline().rstrip()
    foods.append(food[2:])

foods.sort()

tmp = foods[0].split()
for t in range(len(tmp)):
    print('-'*2*t + tmp[t])

for t in range(1,len(foods)):
    nw_tmp = foods[t].split()
    for v in range(len(nw_tmp)):
        try:
            if nw_tmp[v] != tmp[v]:
                cnt = v
                break
        except:
            cnt = v
            break
    for v in range(len(nw_tmp)):
        if v >= cnt:
            print('-'*2*v + nw_tmp[v])
    tmp = nw_tmp[:]