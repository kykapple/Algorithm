#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	vector<int> result;
	for (int i = 0; i < t; i++) {
		int m, n, x, y;
		cin >> m >> n >> x >> y;

		int plus_factor = 0, dx = 1, dy = 1, cnt = 0;
		if (m < n) {
			plus_factor = m;
			if (dy + (x - dx) == y) {
				result.push_back(x - dx + 1);
				continue;
			}
			dy = cnt = m + 1; 
			if (dy != n) dy %= n;

			while (true) {
				if ((dy + (x - dx)) % n == (y % n)) {
					result.push_back(cnt + (x - dx));
					break;
				}
				if (dy + (m - dx) == n) {
					result.push_back(-1);
					break;
				}
				dy = (dy - (n - m));
				if (dy <= 0) dy += n;
				cnt += plus_factor;
			}
		}
		else {
			plus_factor = n;
			if (dx + (y - dy) == x) {
				result.push_back(y - dy + 1);
				continue;
			}
			dx = cnt = n + 1;
			if (dx != m) dx %= m;

			while (true) {
				if ((dx + (y - dy)) % m == (x % m)) {
					result.push_back(cnt + (y - dy));
					break;
				}
				if (dx + (n - dy) == m) {
					result.push_back(-1);
					break;
				}
				dx = (dx - (m - n));
				if (dx <= 0) dx += m;
				cnt += plus_factor;
			}
		}
	}

	for (int i : result)
		cout << i << '\n';

	return 0;
}