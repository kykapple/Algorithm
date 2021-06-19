#include <bits/stdc++.h>
using namespace std;

string W_cnt[8] = {
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW"
};

string B_cnt[8] = {
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB",
	"BWBWBWBW",
	"WBWBWBWB"
};

string* chess;

int W_start(int a, int b) {
	int cnt = 0;
	for (int i = a, t = 0; i < a + 8; i++, t++) {
		for (int j = b, k = 0; j < b + 8; j++, k++) {
			if (chess[i][j] != W_cnt[t][k])
				cnt++;
		}
	}
	return cnt;
}

int B_start(int a, int b) {
	int cnt = 0;
	for (int i = a, t = 0; i < a + 8; i++, t++) {
		for (int j = b, k = 0; j < b + 8; j++, k++) {
			if (chess[i][j] != B_cnt[t][k])
				cnt++;
		}
	}
	return cnt;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m, min;
	cin >> n >> m;
	chess = new string[n];
	min = n * m;
	for (int i = 0; i < n; i++) {
		cin >> chess[i];
	}

	for (int i = 0; i + 8 <= n; i++) {
		for (int j = 0; j + 8 <= m; j++) {
			int tmp = (W_start(i, j) > B_start(i, j)) ? B_start(i, j) : W_start(i, j);
			min = (min < tmp) ? min : tmp;
		}
	}

	cout << min;

	return 0;
}
