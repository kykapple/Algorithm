#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	int i = 1, val = 1;
	while (true) {
		if (n <= val) {
			cout << i;
			break;
		}
		val += 6 * i;
		i++;
	}

	return 0;
}
