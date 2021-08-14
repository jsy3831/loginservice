import sys
from collections import deque

n = int(sys.stdin.readline().strip())
height = [int(i) for i in sys.stdin.readline().strip().split()]
height = height[::-1]
stack = deque([])
ans = [str(0) for i in range(n)]

for i in range(len(height)):
    while len(stack) > 0 and height[i] > stack[0][0]:
        ans[stack[0][1]] = str(n - i)
        stack.remove(stack[0])
    stack.insert(0, (height[i], n - i - 1))

print(" ".join(ans))