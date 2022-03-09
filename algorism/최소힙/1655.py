import sys
import heapq

n = int(sys.stdin.readline())
minheap = []
for i in range(n):
    heapq.heappush(minheap,int(sys.stdin.readline()))
    print(min(heapq.heapify(minheap,i//2),heapq.heapify(minheap,(i+1)//2)))
