#include <bits/stdc++.h>
using namespace std;

long long order = -1;
int n, r, c;
long long result;

void division(int x, int y, int n) {
	if (n == 2 && !result) {
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				order++;
				if (i == r && j == c) {
					result = order;
					return;
				}
			}
		}
	}
	else if (n != 2) {
		int div = n / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				division(div * j + x, div * i + y, div);
			}
		}
	} 
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> r >> c;

	int div = ((int)pow(2, n)) / 2;

	if(div == 1) 
		division(0, 0, (int)pow(2, n));
	else {
		int cnt = 0;
		bool flag = false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if ((div * i <= r && r < div * i + div) && (div * j <= c && c < div * j + div)) {
					order += (cnt * div * div);
					division(div * j, div * i, div);
					flag = true;
					break;
				}
				cnt++;
			}
			if (flag) break;
		}
	}

	cout << result;

	return 0;
}
