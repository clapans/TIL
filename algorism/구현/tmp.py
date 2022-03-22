import sys

dx = [-1,1,0,0]
dy = [0,0,1,-1]
dir_dict = {0: 1, 1: 0, 2: 3, 3: 2}

r,c,m = map(int,sys.stdin.readline().split())
arr = [[0]*c for _ in range(r)]
shark = []

for _ in range(m):
    x,y,v,d,z = map(int,sys.stdin.readline().split())
    shark.append([x-1,y-1,v,d,z])

def move(x,y,v,d):
    if d > 1:
        v = v % (2*(c-1))
    else:
        v = v % (2*(r-1))
    for _ in range(v):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < r and 0 <= ny < c:
            x,y = nx,ny
        else:
            d = dir_dict[d] 
            x += dx[d]
            y += dy[d]
    return [x,y,v,d] 

cnt = 0
res = 0

while cnt < c:
    for i in range(len(shark)):
        tmp = move(shark[i][0],shark[i][1],shark[i][2],shark[i][3]-1)
        shark[i] = tmp + [shark[i][-1]]
    
    shark.sort(key = lambda x: (x[0],x[1]))
    tmp = [shark[0]]
    for i in range(1,len(shark)):
        if shark[i][0] == tmp[-1][0] and shark[i][1] == tmp[i-1][1]:
            if shark[i][-1] > tmp[-1][-1]:
                tmp[-1] = shark[i]
        else:
            tmp.append(shark[i])
    for t in shark:
        if t[1] == cnt:
            res += t[-1]
            shark.remove(t)
            break
    cnt += 1

print(res)