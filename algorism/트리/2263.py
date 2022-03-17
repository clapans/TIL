import sys

sys.setrecursionlimit(10**6)

def pre_order(in_end,in_start,end,start):
    if end < start or in_end < in_start:
        return
    left = get_idx[postorder[end]] - in_start
    right = in_end - get_idx[postorder[end]]
    print(postorder[end],end=' ')
    pre_order(in_start+left-1,in_start,start+left-1,start)
    pre_order(in_end,in_end-right+1,end-1,end-right)

n = int(sys.stdin.readline())
inorder = list(map(int,sys.stdin.readline().split()))
postorder = list(map(int,sys.stdin.readline().split()))
get_idx = [0] * (n+1)

for t in range(n):
    get_idx[inorder[t]] = t

pre_order(n-1,0,n-1,0)