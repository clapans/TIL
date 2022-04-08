import sys

n,m = map(int,sys.stdin.readline().split())
num = []

for t in range(n):
    num.append(int(sys.stdin.readline()))

min_seg = [0] * (4*pow(int(n**(1/2))+1,2))
max_seg = [0] * (4*pow(int(n**(1/2))+1,2))

def max_tree(s,e,ix):
    if max_seg[ix]:
        return max_seg[ix]
    if s == e:
        return max_seg[ix]
    mid = (s+e)//2
    max_seg[ix] = max(max_tree(s,mid,ix*2),max_tree(mid+1,e,ix*2+1))
    return max_seg[ix]

def min_tree(s,e,ix):
    if min_seg[ix]:
        return min_seg[ix]
    if s == e:
        min_seg[ix] = num[s]
        return min_seg[ix]
    mid = (s+e)//2
    min_seg[ix] = min(min_tree(s,mid,ix*2),min_tree(mid+1,e,ix*2+1))
    return min_seg[ix]

def get_sub(s,e,l,r,ix):
    if l > e or r < s:
        return [int(1e9),0]
    if l <= s and r >= e:
        return [min_seg[ix],max_seg[ix]]
    mid = (s+e)//2
    tmp1 = get_sub(s,mid,l,r,ix*2)
    tmp2 = get_sub(mid+1,e,l,r,ix*2+1)
    return [min(tmp1[0],tmp2[0]),max(tmp1[1],tmp2[1])]

max_tree(0,n-1,1)
min_tree(0,n-1,1)

for _ in range(m):
    a,b = map(int,sys.stdin.readline().split())
    print(*get_sub(0,n-1,a-1,b-1,1))