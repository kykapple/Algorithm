#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	int cnt = 0;
	int temp = n;
	
	while (temp % 5 != 0) {
		temp -= 3;
		if (temp < 0) {
			cout << -1;
			return 0;
		}
		cnt++;
	}

	cnt += temp / 5;
	cout << cnt;

	return 0;
}