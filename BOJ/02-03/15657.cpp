#include <bits/stdc++.h>
using namespace std;

int n, m;
int values[9];
int arr[9];
bool visited[9];

void DFS(int idx, int cnt) {
	if (cnt == m) {
		for (int i = 0; i < m; i++)
			cout << arr[i] << ' ';
		cout << '\n';

		return;
	}

	for (int i = idx; i < n; i++) {
		arr[cnt] = values[i];
		DFS(i, cnt + 1);
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	for (int i = 0; i < n; i++)
		cin >> values[i];

	sort(values, values + n);

	DFS(0, 0);

	return 0;
}