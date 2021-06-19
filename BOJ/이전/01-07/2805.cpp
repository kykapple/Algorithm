#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m;
	cin >> n >> m;

	long long* trees = new long long[n];
	long long max = 0;

	for (int i = 0; i < n; i++) {
		cin >> trees[i];
		if (max < trees[i]) max = trees[i];
	}

	long long low = 0, high = max;
	long long hight = 0;

	while (low <= high) {
		long long mid = (high + low) / 2;

		long long sum = 0;
		for (int i = 0; i < n; i++) {
			if(trees[i] >= mid)
				sum += (trees[i] - mid);
		}
		if (sum >= m) {
			hight = mid;
			low = mid + 1;
		}
		else
			high = mid - 1;
	}

	cout << hight;

	return 0;
}
