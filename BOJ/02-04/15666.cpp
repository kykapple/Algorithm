#include <bits/stdc++.h>
using namespace std;

int n, m;
int values[8];
int arr[8];
bool visited[8];

void DFS(int start, int cnt) {
	if (cnt == m) {
		for (int i = 0; i < m; i++)
			cout << arr[i] << ' ';
		cout << endl;

		return;
	}

	int before = 0;
	for (int i = 0; i < n; i++) {
		bool flag = true;
		for (int j = 0; j < cnt; j++) {
			if (values[i] < arr[j]) {
				flag = false;
				break;
			}
		}

		if (flag && before != values[i]) {
			before = values[i];
			arr[cnt] = values[i];
			DFS(i + 1, cnt + 1);
		}
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
