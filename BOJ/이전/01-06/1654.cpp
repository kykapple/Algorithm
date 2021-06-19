#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int k, n, cnt = 0;
	long long* line;
	long long max = 0, len = 0;
	
	cin >> k >> n;
	line = new long long[k];

	for (int i = 0; i < k; i++) {
		cin >> line[i];
		if (max < line[i]) max = line[i];
	}

	long long low = 1, high = max;
	while (low <= high) {
		long long mid = (high + low) / 2;

		cnt = 0;

		for (int i = 0; i < k; i++) {
			cnt += line[i] / mid;
		}

		if (cnt < n) {
			high = mid - 1;
		}
		else {
			len = mid;
			low = mid + 1;
		}
	}

	cout << len;

	return 0;
}
