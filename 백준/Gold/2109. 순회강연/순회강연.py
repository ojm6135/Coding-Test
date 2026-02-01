import sys

In = sys.stdin.readline

cnt = [0] * 10000
data = []

n = int(In())

for _ in range(n):
    data.append(tuple(map(int, In().split())))

data.sort(reverse = True)

price = 0
for k in data:
    for i in  reversed(range(k[1])):
        if cnt[i] == 0:
            cnt[i] = 1
            price += k[0]
            break


print(price)