#include <bits/stdc++.h>
using namespace std;

string results[10001];
bool visited[10001];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		int a, b;
		cin >> a >> b;

		for (int j = 0; j < 10001; j++) {
			visited[j] = false;
			results[j] = "";
		}
		visited[a] = true;

		queue<int> q;
		q.push(a);

		while (!q.empty()) {
			int x = q.front();
			q.pop();

			int dx[] = { 'D', 'S', 'L', 'R' };

			for (int i = 0; i < 4; i++) {
				int nx;
				char ch;

				if (dx[i] == 'L') {
					nx = ((x * 10) % 10000) + x / 1000;
					ch = 'L';
				}
				else if (dx[i] == 'R') {
					nx = (x / 10) + ((x % 10) * 1000);
					ch = 'R';
				}
				else if (dx[i] == 'D') {
					nx = (x * 2) % 10000;
					ch = 'D';
				}
				else {
					nx = x - 1;
					if (nx < 0) nx += 10000;
					ch = 'S';
				}

				if (nx < 0 || nx > 10000) continue;
				if (!visited[nx]) {
					visited[nx] = true;
					results[nx] = results[x] + ch;
					q.push(nx);
				}
			}
		}

		cout << results[b] << '\n';
	}

	return 0;
}