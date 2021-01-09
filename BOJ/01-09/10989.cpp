#include <bits/stdc++.h>
using namespace std;

int arr[10001];

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	int max = 0;

	for (int i = 0; i < n; i++) {
		int val;
		cin >> val;
		if (max < val) max = val;
		arr[val]++;
	}

	for (int i = 0; i <= max; i++) {
		if (arr[i]) {
			for (int j = 0; j < arr[i]; j++) {
				cout << i << '\n';
			}
		}
	}

	return 0;
} 