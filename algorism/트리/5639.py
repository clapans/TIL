import sys

sys.setrecursionlimit(10**6)

def order(start,end):
    if start > end:
        return
    idx = start + 1
    while idx <= end:
        if node[idx] > node[start]:
            break
        idx += 1
    order(start + 1,idx - 1)
    order(idx,end)
    print(node[start])

node = []
while True:
    try:
        node.append(int(sys.stdin.readline()))
    except:
        break

order(0,len(node)-1)