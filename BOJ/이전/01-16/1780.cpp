#include <bits/stdc++.h>
using namespace std;

int paper[2187][2187];
int n;
int result[3];

int conquer(int x, int y, int n) {
	int val = paper[x][y];
	for (int i = x; i < x + n; i++) {
		for (int j = y; j < y + n; j++) {
			if (val != paper[i][j])
				return -2;
		}
	}

	return val;
}

void division(int x, int y, int n) {
	int val = conquer(x, y, n);
	if (val != -2) {
		result[val + 1]++;
	}
	else {
		int div = n / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				division(div * i + x, div * j + y, div);
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> paper[i][j];
		}
	}

	division(0, 0, n);

	for (int i = 0; i < 3; i++)
		cout << result[i] << '\n';

	return 0;
}