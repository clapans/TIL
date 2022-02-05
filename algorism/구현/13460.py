import sys
import pprint

def up(b,r):
    if b[0] < r[0]:
        for t in range(b[0]-1,0,-1):
            if board[t][b[1]] == '.':
                board[t][b[1]] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[t][b[1]] == 'O':
                return False
            else:
                break
        for t in range(r[0]-1,0,-1):
            if board[t][r[1]] == '.':
                board[t][r[1]] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[t][b[1]] == 'O':
                return True
            else:
                break
    else:
        ch = 0
        for t in range(r[0]-1,0,-1):
            if board[t][r[1]] == '.':
                board[t][r[1]] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[t][b[1]] == 'O':
                ch = 1
                break
            else:
                break
        for t in range(b[0]-1,0,-1):
            if board[t][b[1]] == '.':
                board[t][b[1]] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[t][b[1]] == 'O':
                return False
            else:
                break
        if ch == 1:
            return True
    return [b,r]

def down(b,r):
    if b[0] > r[0]:
        for t in range(b[0]+1,n-1):
            if board[t][b[1]] == '.':
                board[t][b[1]] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[t][b[1]] == 'O':
                return False
            else:
                break
        for t in range(r[0]+1,n-1):
            if board[t][r[1]] == '.':
                board[t][r[1]] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[t][b[1]] == 'O':
                return True
            else:
                break
    else:
        ch = 0
        for t in range(r[0]+1,n-1):
            if board[t][r[1]] == '.':
                board[t][r[1]] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[t][b[1]] == 'O':
                ch = 1
                break
            else:
                break
        for t in range(b[0]+1,n-1):
            if board[t][b[1]] == '.':
                board[t][b[1]] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[t][b[1]] == 'O':
                return False
            else:
                break
        if ch == 1:
            return True
    return [b,r]

def left(b,r):
    if b[1] < r[1]:
        for t in range(b[1]-1,0,-1):
            if board[b[0]][t] == '.':
                board[b[0]][t] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[b[0]][t] == 'O':
                return False
            else:
                break
        for t in range(r[1]-1,0,-1):
            if board[r[0]][t] == '.':
                board[r[0]][t] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[r[0]][t] == 'O':
                return True
            else:
                break
    else:
        ch = 0
        for t in range(r[1]-1,0,-1):
            if board[r[0]][t] == '.':
                board[r[0]][t] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[r[0]][t] == 'O':
                ch = 1
                break
            else:
                break
        for t in range(b[1]-1,0,-1):
            if board[b[0]][t] == '.':
                board[b[0]][t] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[b[0]][t] == 'O':
                return False
            else:
                break
        if ch == 1:
            return True
    return [b,r]

def right(b,r):
    if b[1] > r[1]:
        for t in range(b[1]+1,m-1):
            if board[b[0]][t] == '.':
                board[b[0]][t] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[b[0]][t] == 'O':
                return False
            else:
                break
        for t in range(r[1]+1,m-1):
            if board[r[0]][t] == '.':
                board[r[0]][t] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[r[0]][t] == 'O':
                return True
            else:
                break
    else:
        ch = 0
        for t in range(r[1]+1,m-1):
            if board[r[0]][t] == '.':
                board[r[0]][t] = 'R'
                board[r[0]][r[1]] = '.'
            elif board[r[0]][t] == 'O':
                ch = 1
                break
            else:
                break
        for t in range(b[1]+1,m-1):
            if board[b[0]][t] == '.':
                board[b[0]][t] = 'B'
                board[b[0]][b[1]] = '.'
            elif board[b[0]][t] == 'O':
                return False
            else:
                break
        if ch == 1:
            return True
    return [b,r]

n,m = map(int,sys.stdin.readline().split())
board = []
b = []
r = []
res = 11

for t in range(n):
    board.append(list(sys.stdin.readline().rstrip()))

for i in range(n):
    for j in range(m):
        if board[i][j] == 'B':
            b = [i,j]
        elif board[i][j] == 'R':
            r = [i,j]

def game(b,r,cnt):
    global res
    if cnt <= 10:
        for t in range(4):
            if t == 0:
                tmp = up(b,r)
            elif t == 1:
                tmp = down(b,r)
            elif t == 2:
                tmp = left(b,r)
            else:
                tmp = right(b,r)
            if tmp == True:
                res = min(res,cnt+1)
            elif tmp == False:
                pass
            else:
                game(tmp[0],tmp[1],cnt+1)
                board[b[0]][b[1]] = 'B'
                board[r[0]][r[1]] = 'R'
                board[tmp[0][0]][tmp[0][1]] = '.'
                board[tmp[1][0]][tmp[1][1]] = '.'

tmp = left(b,r)
board[tmp[0][0]][tmp[0][1]] = 'B'
board[tmp[1][0]][tmp[1][1]] = 'R'
pprint.pprint(board)