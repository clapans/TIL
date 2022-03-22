import sys

dir_dict = {0: 1, 1: 0, 2: 3, 3: 2}

r,c,m = map(int,sys.stdin.readline().split())
shark = []

for _ in range(m):
    x,y,v,d,z = map(int,sys.stdin.readline().split())
    shark.append([x-1,y-1,v,d-1,z])

def move(x,y,v,d):
    nr = r - 1
    nc = c - 1
    if d > 1:
        v_ = v % (2*nc)
        if d == 2:
            if y + v_ <= nc:
                y += v_
            elif nc < y + v_ <= 2*nc:
                y = 2*nc - y - v_
                d = dir_dict[d]
            else:
                y += v_ - 2*nc
        else:
            if y - v_ >= 0:
                y -= v_
            elif -nc <= y - v_ < 0:
                y = v_ - y
                d = dir_dict[d]
            else:
                y += 2*nc - v_  
    else:
        v_ = v % (2*nr)
        if d == 1:
            if x + v_ <= nr:
                x += v_
            elif nr < x + v_ <= 2*nr:
                x = 2*nr - x - v_
                d = dir_dict[d]
            else:
                x += v_ - 2*nr
        else:
            if x - v_ >= 0:
                x -= v_
            elif -nr <= x - v_ < 0:
                x = v_ - x
                d = dir_dict[d]
            else:
                x += 2*nr - v_
    return [x,y,v,d] 

cnt = 0
res = 0
shark.sort(key = lambda x: (x[0],x[1]))

while cnt < c and shark:
    for t in shark:
        if t[1] == cnt:
            res += t[-1]
            shark.remove(t)
            break
    for i in range(len(shark)):
        tmp = move(shark[i][0],shark[i][1],shark[i][2],shark[i][3])
        shark[i] = tmp + [shark[i][-1]]
    shark.sort(key = lambda x: (x[0],x[1]))
    if shark:
        tmp = [shark[0]]
        for i in range(1,len(shark)):
            if shark[i][0] == tmp[-1][0] and shark[i][1] == tmp[-1][1]:
                if shark[i][-1] > tmp[-1][-1]:
                    tmp[-1] = shark[i]
            else:
                tmp.append(shark[i])
    shark = tmp
    cnt += 1

print(res)