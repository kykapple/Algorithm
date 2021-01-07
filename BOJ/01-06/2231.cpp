#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, i;
	cin >> n;

	for (i = 1; i <= n; i++) {
		int temp = i, sum = temp;
		while (temp) {
			sum += temp % 10;
			temp /= 10;
		}
		if (sum == n) {
			cout << i;
			break;
		}
	}

	if (i > n)
		cout << 0;

	return 0;
}
