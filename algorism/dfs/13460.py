import sys
import copy

def up(b,r,board):
    board[b[0]][b[1]] = '.'
    board[r[0]][r[1]] = '.'
    if b[0] < r[0]:
        for t in range(b[0]-1,0,-1):
            if board[t][b[1]] == '.':
                b = [t,b[1]]
            elif board[t][b[1]] == 'O':
                b = [t,b[1]]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
        for t in range(r[0]-1,0,-1):
            if board[t][r[1]] == '.':
                r = [t,r[1]]
            elif board[t][r[1]] == 'O':
                r = [t,r[1]]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
    else:
        for t in range(r[0]-1,0,-1):
            if board[t][r[1]] == '.':
                r = [t,r[1]]
            elif board[t][r[1]] == 'O':
                r = [t,r[1]]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
        for t in range(b[0]-1,0,-1):
            if board[t][b[1]] == '.':
                b = [t,b[1]]
            elif board[t][b[1]] == 'O':
                b = [t,b[1]]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
    return board

def down(b,r,board):
    board[b[0]][b[1]] = '.'
    board[r[0]][r[1]] = '.'
    if b[0] > r[0]:
        for t in range(b[0]+1,n-1):
            if board[t][b[1]] == '.':
                b = [t,b[1]]
            elif board[t][b[1]] == 'O':
                b = [t,b[1]]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
        for t in range(r[0]+1,n-1):
            if board[t][r[1]] == '.':
                r = [t,r[1]]
            elif board[t][r[1]] == 'O':
                r = [t,r[1]]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
    else:
        for t in range(r[0]+1,n-1):
            if board[t][r[1]] == '.':
                r = [t,r[1]]
            elif board[t][r[1]] == 'O':
                r = [t,r[1]]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
        for t in range(b[0]+1,n-1):
            if board[t][b[1]] == '.':
                b = [t,b[1]]
            elif board[t][b[1]] == 'O':
                b = [t,b[1]]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
    return board

def left(b,r,board):
    board[b[0]][b[1]] = '.'
    board[r[0]][r[1]] = '.'
    if b[1] < r[1]:
        for t in range(b[1]-1,0,-1):
            if board[b[0]][t] == '.':
                b = [b[0],t]
            elif board[b[0]][t] == 'O':
                b = [b[0],t]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
        for t in range(r[1]-1,0,-1):
            if board[r[0]][t] == '.':
                r = [r[0],t]
            elif board[r[0]][t] == 'O':
                r = [r[0],t]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
    else:
        for t in range(r[1]-1,0,-1):
            if board[r[0]][t] == '.':
                r = [r[0],t]
            elif board[r[0]][t] == 'O':
                r = [r[0],t]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
        for t in range(b[1]-1,0,-1):
            if board[b[0]][t] == '.':
                b = [b[0],t]
            elif board[b[0]][t] == 'O':
                b = [b[0],t]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
    return board

def right(b,r,board):
    board[b[0]][b[1]] = '.'
    board[r[0]][r[1]] = '.'
    if b[1] > r[1]:
        for t in range(b[1]+1,m-1):
            if board[b[0]][t] == '.':
                b = [b[0],t]
            elif board[b[0]][t] == 'O':
                b = [b[0],t]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
        for t in range(r[1]+1,m-1):
            if board[r[0]][t] == '.':
                r = [r[0],t]
            elif board[r[0]][t] == 'O':
                r = [r[0],t]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
    else:
        for t in range(r[1]+1,m-1):
            if board[r[0]][t] == '.':
                r = [r[0],t]
            elif board[r[0]][t] == 'O':
                r = [r[0],t]
                break
            else:
                break
        board[r[0]][r[1]] = 'R'
        for t in range(b[1]+1,m-1):
            if board[b[0]][t] == '.':
                b = [b[0],t]
            elif board[b[0]][t] == 'O':
                b = [b[0],t]
                break
            else:
                break
        board[b[0]][b[1]] = 'B'
    return board

n,m = map(int,sys.stdin.readline().split())
board = []
b = []
r = []
o = []
res = 11

for t in range(n):
    board.append(list(sys.stdin.readline().rstrip()))

for i in range(n):
    for j in range(m):
        if board[i][j] == 'O':
            o = [i,j]

def game(cnt,state,arr):
    global res
    if cnt <= 10:
        save = copy.deepcopy(arr)
        for i in range(n):
            for j in range(m):
                if save[i][j] == 'B':
                    b = [i,j]
                elif save[i][j] == 'R':
                    r = [i,j]
        if save[o[0]][o[1]] == 'R':
            save[o[0]][o[1]] = 'O'
            if state == 0:
                tmp = up(b,b,copy.deepcopy(save))
                if tmp[o[0]][o[1]] == 'O':
                    res = min(res,cnt)
            elif state == 1:
                tmp = down(b,b,copy.deepcopy(save))
                if tmp[o[0]][o[1]] == 'O':
                    res = min(res,cnt)
            elif state == 2:
                tmp = left(b,b,copy.deepcopy(save))
                if tmp[o[0]][o[1]] == 'O':
                    res = min(res,cnt)
            else:
                tmp = right(b,b,copy.deepcopy(save))
                if tmp[o[0]][o[1]] == 'O':
                    res = min(res,cnt)
        elif save[o[0]][o[1]] == 'B':
            pass
        else:
            if state > 1:
                for t in range(2):
                    if t == 0:
                        tmp = up(b,r,copy.deepcopy(save))
                        if tmp[b[0]][b[1]] != save[b[0]][b[1]] or tmp[r[0]][r[1]] != save[r[0]][r[1]]:
                            game(cnt+1,0,tmp)
                    else:
                        tmp = down(b,r,copy.deepcopy(save))
                        if tmp[b[0]][b[1]] != save[b[0]][b[1]] or tmp[r[0]][r[1]] != save[r[0]][r[1]]:
                            game(cnt+1,1,tmp)               
            else:
                for t in range(2):
                    if t == 0:
                        tmp = left(b,r,copy.deepcopy(save))
                        if tmp[b[0]][b[1]] != save[b[0]][b[1]] or tmp[r[0]][r[1]] != save[r[0]][r[1]]:
                            game(cnt+1,2,tmp)
                    else:
                        tmp = right(b,r,copy.deepcopy(save))
                        if tmp[b[0]][b[1]] != save[b[0]][b[1]] or tmp[r[0]][r[1]] != save[r[0]][r[1]]:
                            game(cnt+1,3,tmp)
                    

game(0,1,board)
game(0,2,board)

if res <= 10:
    print(res)
else:
    print(-1)