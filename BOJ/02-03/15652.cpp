#include <bits/stdc++.h>
using namespace std;

int n, m;
int arr[9];

void DFS(int start, int cnt) {
	if (cnt == m) {
		for (int i = 0; i < m; i++)
			cout << arr[i] << ' ';
		cout << endl;

		return;
	}
	
	for (int i = start; i <= n; i++) {
		arr[cnt] = i;
		DFS(i, cnt + 1);
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	DFS(1, 0);

	return 0;
}
