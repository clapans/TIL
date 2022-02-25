import sys

n,m,k = map(int,sys.stdin.readline().split())
num = [int(sys.stdin.readline()) for _ in range(n)]
seg = [0]*(4*pow(int(n**(1/2))+1,2))

def sub_sum(s,e,ix):
    if seg[ix]:
        return seg[ix]
    mid = (s+e)//2
    if s == e:
        seg[ix] = num[s]
        return seg[ix]
    seg[ix] = sub_sum(s,mid,ix*2) + sub_sum(mid+1,e,ix*2+1)
    return seg[ix]

def get_sum(s,e,l,r,ix):
    if l > e or r < s:
        return 0
    if l <= s and r >= e:
        return seg[ix]
    mid = (s+e)//2
    return get_sum(s,mid,l,r,ix*2) + get_sum(mid+1,e,l,r,ix*2+1)

def update(s,e,edit,ix):
    if s <= edit <= e:
        seg[ix] += tmp
        if s < e:
            mid = (s+e)//2
            update(s,mid,edit,ix*2)
            update(mid+1,e,edit,ix*2+1)

sub_sum(0,n-1,1)
for t in range(m+k):
    a,b,c = map(int,sys.stdin.readline().split())
    if a == 1:
        tmp = c - num[b-1]
        num[b-1] = c
        update(0,n-1,b-1,1)
    elif a == 2:
        print(get_sum(0,n-1,b-1,c-1,1))