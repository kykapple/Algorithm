#include <bits/stdc++.h>
using namespace std;

int audio[64][64];
int n;
vector<char> result;

int conquer(int x, int y, int n) {
	int val = audio[x][y];
	for (int i = x; i < x + n; i++) {
		for (int j = y; j < y + n; j++) {
			if (val != audio[i][j])
				return -1;
		}
	}

	return val;
}

void division(int x, int y, int n) {
	int val = conquer(x, y, n);
	if (val != -1) {
		result.push_back(val+'0');
	}
	else {
		result.push_back('(');
		int div = n / 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				division(div * i + x, div * j + y, div);
			}
		}
		result.push_back(')');
	}
}

int main(void) {
	scanf_s("%d", &n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)
			scanf_s("%1d", &audio[i][j]);
	}

	division(0, 0, n);

	for (char ch : result)
		cout << ch;

	return 0;
}