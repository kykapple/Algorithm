#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	int cnt = 0;

	int two_cnt = 0, five_cnt = 0;
	for (int i = 1; i <= n; i++) {
		int temp = i;
		if (temp % 2 == 0) {
			while (temp / 2) {
				two_cnt++;
				temp /= 2;
				if (temp % 2 != 0) break;
			}
		}
		
		if (temp % 5 == 0) {
			while (temp / 5) {
				five_cnt++;
				temp /= 5;
				if (temp % 5 != 0) break;
			}
		}
	}

	if (two_cnt && five_cnt) {
		cnt = two_cnt > five_cnt ? five_cnt : two_cnt;
	}

	cout << cnt;

	return 0;
}
