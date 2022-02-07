import sys

for case in range(4):
    a_0,a_1,b_0,b_1,c_0,c_1,d_0,d_1 = map(int,sys.stdin.readline().split())
    tmp = [min(b_0,d_0) - max(a_0,c_0),min(b_1,d_1) - max(a_1,c_1)]
    if tmp[0] > 0 and tmp[1] > 0:
        print('a')
    elif tmp[0] < 0 or tmp[1] < 0:
        print('d')
    elif tmp[0] == 0 and tmp[1] == 0:
        print('c')
    else:
        print('b')
    