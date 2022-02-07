import sys

melon = int(sys.stdin.readline())
east_west = 0
north_south = 0
res = []

def rotate(a,b):
    if a + b < 6:
        return a+b
    return a+b-6
 
for t in range(6):
    a,b = map(int,sys.stdin.readline().split())
    if a <= 2:
        east_west = max(east_west,b)
    else:
        north_south = max(north_south,b)
    res.append(b)

row_ix = res.index(east_west)
column_ix = res.index(north_south)

big_rect = res[row_ix] * res[column_ix]
small_rect = res[rotate(row_ix,3)] * res[rotate(column_ix,3)]
print(melon * (big_rect - small_rect))