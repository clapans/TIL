import sys

player = []
host = []
row = []
column = []
daegak = [0,0]

for t in range(5):
    player.append(list(map(int,sys.stdin.readline().split())))

for t in range(5):
    host.append(list(map(int,sys.stdin.readline().split())))

for t in player:
    row.append(sum(t))

for t in zip(*player):
    column.append(sum(list(t)))

for t in range(1,6):
    daegak[0] += player[t-1][t-1]
    daegak[1] += player[t-1][-t]

def find(v):
    for x in range(5):
        for y in range(5):
            if player[x][y] == v:
                return [x,y]

for i in range(5):
    for j in range(5):
        tmp = find(host[i][j])
        row[tmp[0]] -= host[i][j]
        column[tmp[1]] -= host[i][j]
        if tmp[0] == tmp[1]:
            daegak[0] -= host[i][j]
        if tmp[0] + tmp[1] == 4:
            daegak[1] -= host[i][j]
        if row.count(0) + column.count(0) + daegak.count(0) >= 3:
            print(5*i+j+1)
            quit(0)

