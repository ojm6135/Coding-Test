import sys
from collections import deque

input = sys.stdin.readline

grid = []
cnt_prey = [0] * 7
shark_size = 2
queue = deque()

n = int(input())

for r in range(n):
    data = list(map(int, input().split()))
    grid.append(data)

    for c in range(n):
        if data[c] == 9:
            current = (r, c, 0)
            queue.append(current)

        elif 0 < data[c] < 9:
            prey_size = data[c]
            cnt_prey[prey_size] += 1

time = 0
cnt_ate = 0
d = [ (-1, 0), (1, 0), (0, -1), (0, 1) ]

visited = [ [False] * n for _ in range(n) ]
visited[current[0]][current[1]] = True

while queue and sum( cnt_prey[:shark_size] ) > 0:
    can_eat_list = set()

    for _ in range( len(queue) ):
        r, c, cnt_move = queue.popleft()

        for dr, dc in d:
            new_r = r + dr
            new_c = c + dc

            if 0 <= new_r < n and 0 <= new_c < n and not visited[new_r][new_c]:
                visited[new_r][new_c] = True

                prey_size = grid[new_r][new_c]

                if prey_size > shark_size:
                    continue

                elif prey_size == 0 or prey_size == shark_size:
                    queue.append( (new_r, new_c, cnt_move + 1) )

                elif 0 < prey_size < shark_size:
                    can_eat_list.add( (new_r, new_c, cnt_move + 1) )


    if can_eat_list:
        can_eat_list = list(can_eat_list)
        can_eat_list.sort()

        prey_r = 0
        prey_c = 0
        move = 0
        dist = 1000

        for rr, cc, cm in can_eat_list:
            temp = abs( rr - current[0] ) + abs( cc - current[1] )

            if temp < dist:
                prey_r = rr
                prey_c = cc
                move = cm
                dist = temp

        prey_size = grid[prey_r][prey_c]

        cnt_ate += 1

        if cnt_ate == shark_size:
            shark_size += 1
            cnt_ate = 0

        cnt_prey[prey_size] -= 1
        grid[prey_r][prey_c] = 9
        grid[ current[0] ][ current[1] ] = 0

        time += move
        current = (prey_r, prey_c, 0)

        queue.clear()
        queue.append(current)

        visited = [ [False] * n for _ in range(n) ]
        visited[current[0]][current[1]] = True


print(time)