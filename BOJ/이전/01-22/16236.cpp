#include <bits/stdc++.h>
using namespace std;
#define MIN 987654321;

int graph[20][20];
int visited[20][20];
int n, x, y, shark = 2, dist, min_x, min_y, sec, eat_cnt;
bool flag;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

void BFS(int x, int y) {
	dist = min_x = min_y = MIN;
	flag = false;

	queue<pair<int, int>> q;
	q.push({ x,y });

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			else if (graph[nx][ny] > shark) continue;
			else if ((graph[nx][ny] == 0 || shark >= graph[nx][ny]) && !visited[nx][ny]) {
				visited[nx][ny] = visited[x][y] + 1;
				
				if (graph[nx][ny] != 0 && graph[nx][ny] < shark) {
					if (dist == visited[nx][ny]) {
						if (min_x == nx) {
							if (min_y > ny) {
								min_x = nx;
								min_y = ny;
							}
						}
						else if (min_x > nx) {
							min_x = nx;
							min_y = ny;
						}
					}
					else if (dist > visited[nx][ny]) {
						dist = visited[nx][ny];

						min_x = nx;
						min_y = ny;
					}

					flag = true;
				}
				q.push({ nx, ny });
			}

		}

	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> graph[i][j];

			if (graph[i][j] == 9) {
				x = i, y = j;
				graph[i][j] = 0;
			}
		}
	}

	while (true) {
		BFS(x, y);

		if (!flag) break;

		graph[min_x][min_y] = 0;
		sec += dist;
		eat_cnt++;

		if (eat_cnt == shark) {
			shark++;
			eat_cnt = 0;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				visited[i][j] = 0;
		}

		x = min_x;
		y = min_y;
	}
	
	cout << sec;

	return 0;
}