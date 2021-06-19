#include <bits/stdc++.h>
using namespace std;

long long p[101];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	p[1] = 1; p[2] = 1;
	p[3] = 1; p[4] = 2; p[5] = 2;

	for (int i = 0; i < t; i++) {
		int n;
		cin >> n;

		for (int j = 6; j <= n; j++) {
			p[j] = p[j - 1] + p[j - 5];
		}

		cout << p[n] << '\n';
	}

	return 0;
}
