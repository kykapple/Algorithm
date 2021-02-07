#include <bits/stdc++.h>
using namespace std;

int t, n;
int dx[20], dy[20];
double result = numeric_limits<double>::max();
bool visited[20];

void solve(int start, int cnt) {
	if (cnt == n / 2) {
		double sum_x = 0, sum_y = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				sum_x -= dx[i];
				sum_y -= dy[i];
			}
			else {
				sum_x += dx[i];
				sum_y += dy[i];
			}
		}

		result = min(result, sqrt(sum_x * sum_x + sum_y * sum_y));

		return;
	}

	if (n - start < n / 2 - cnt) return;

	for (int i = start; i < n; i++) {
		visited[i] = true;
		solve(i + 1, cnt + 1);
		visited[i] = false;
	}
}

int main(void) {
	cin >> t;

	for (int i = 0; i < t; i++) {
		cin >> n;

		for (int j = 0; j < n; j++) {
			cin >> dx[j] >> dy[j];
		}

		visited[0] = true;
		solve(1, 1);

		printf("%.12lf\n", result);
		
		result = numeric_limits<double>::max();
	}

	return 0;
}
