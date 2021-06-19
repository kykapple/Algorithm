#include <bits/stdc++.h>
using namespace std;

int paper[128][128];
int n;
int result[2];

int conquer(int x, int y, int n) {
	int val = paper[x][y];
	for (int i = x; i < x + n; i++) {
		for (int j = y; j < y + n; j++) {
			if (val != paper[i][j])
				return -1;
		}
	}

	return val;
}

void division(int x, int y, int n) {
	int val = conquer(x, y, n);
	if (val != -1) {
		result[val]++;
	}
	else {
		int div = n / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
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
		for (int j = 0; j < n; j++)
			cin >> paper[i][j];
	}

	division(0, 0, n);

	cout << result[0] << '\n';
	cout << result[1] << '\n';

	return 0;
}