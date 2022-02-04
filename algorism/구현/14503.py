import sys
import pprint

n,m = map(int,sys.stdin.readline().split())
pos = [0,0]
pos[0],pos[1],dir = map(int,sys.stdin.readline().split())
dirty = []
for t in range(n):
    dirty.append(list(map(int,sys.stdin.readline().split())))

visit = [[0]*m for t in range(n)]
dx = [0,-1,0,1]
dy = [-1,0,1,0]
res = 1

def clean(x,y,dir):
    global res
    res += 1
    visit[x][y] = 1
    ch = 1
    for t in range(4):
        new_x = x + dx[dir]
        new_y = y + dy[dir]
        dir = dir-1 if dir >= 1 else 3
        if 0 <= new_x < n and 0 <= new_y < m:
            if dirty[new_x][new_y] == 0 and visit[new_x][new_y] == 0:
                clean(new_x,new_y,dir)
                ch = 0
                break
    if ch:
        dir = dir+2 if dir < 2 else dir-2
        new_x = x + dx[dir]
        new_y = y + dy[dir]
        if 0 <= new_x < n and 0 <= new_y < m and dirty[new_x][new_y] == 0:
            clean(new_x,new_y,dir)
        else:
            print(res)
            quit(0)

clean(pos[0],pos[1],dir)
