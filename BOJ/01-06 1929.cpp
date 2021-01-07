#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int m, n;
	cin >> m >> n;
	int* arr = new int[n + 1];

	for (int i = 1; i <= n; i++)
		arr[i] = i;

	arr[1] = 0;
	for (int i = 2; i <= n; i++) {
		if (arr[i] == 0) continue;

		for (int j = 2; i * j <= n; j++) {
			arr[i * j] = 0;
		}
	}

	for (int i = m; i <= n; i++) {
		if (arr[i])
			cout << arr[i] << '\n';
	}

	return 0;
}