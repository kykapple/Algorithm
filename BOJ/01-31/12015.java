#include <bits/stdc++.h>
using namespace std;

int values[1000000];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	vector<int> upper(n);
	for (int i = 0; i < n; i++)
		cin >> values[i];

	int idx = 0;
	upper[idx] = values[0];

	for (int i = 1; i < n; i++) {
		if (upper[idx] < values[i]) {
			upper[++idx] = values[i];
		}
		else {
			int start = 0, end = idx;
			while (start < end) {
				int mid = (start + end) / 2;

				if (upper[mid] >= values[i])
					end = mid;
				else
					start = mid + 1;
			}

			upper[end] = values[i];
		}
	}
	
	cout << idx + 1;

	return 0;
}