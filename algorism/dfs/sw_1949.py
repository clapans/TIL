dx = [1,-1,0,0]
dy = [0,0,1,-1]

def dfs(x,y,use,cnt):
    global res
    ch = 1
    for t in range(4):
        nx = x + dx[t]
        ny = y + dy[t]
        if 0 <= nx < n and 0 <= ny < n and visit[nx][ny] == 0:
            if arr[x][y] > arr[nx][ny]:
                visit[nx][ny] = 1
                ch = 0
                dfs(nx,ny,use,cnt+1)
                visit[nx][ny] = 0
            else:
                if use:
                    if arr[nx][ny] - k < arr[x][y]:
                        tmp = arr[nx][ny]
                        arr[nx][ny] = arr[x][y] - 1
                        visit[nx][ny] = 1
                        ch = 0
                        dfs(nx,ny,0,cnt+1)
                        visit[nx][ny] = 0
                        arr[nx][ny] = tmp
    if ch:
        res = max(res,cnt)

for case in range(int(input())):
    n,k = map(int,input().split())
    arr = []
    m_ = 0
    res = 0
    for _ in range(n):
        tmp = list(map(int,input().split()))
        arr.append(tmp)
        m_ = max(m_,max(tmp))
    for i in range(n):
        for j in range(n):
            if arr[i][j] == m_:
                visit = [[0]*n for _ in range(n)]
                visit[i][j] = 1
                dfs(i,j,1,1)
    print(f'#{case+1} {res}')