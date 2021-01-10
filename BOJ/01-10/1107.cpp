#include <bits/stdc++.h>
using namespace std;

int* bust;
int n, m;

bool check(int num) {
	if (num) {
		while (num) {
			int val = num % 10;
			for (int i = 0; i < m; i++)
				if (val == bust[i]) return 0;
			num /= 10;
		}
	}
	else {
		for (int i = 0; i < m; i++)
			if (num == bust[i]) return 0;
	}
	return 1;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int channel = 100;

	cin >> n >> m;

	bust = new int[m];
	for (int i = 0; i < m; i++) {
		cin >> bust[i];
	}

	int gap = 500000, result = 0;
	bool flag = false;
	for (int i = 0; i <= 999999; i++) {
		if (check(i)) {
			if (gap > abs(n - i)) {
				gap = abs(n - i);
				result = i;
				flag = true;
			}
		}
	}

	if (flag) {
		string tmp = to_string(result);
		int bnt_cnt = 0;

		for (int i = 0; i < tmp.length(); i++)
			bnt_cnt++;

		while (result != n) {
			if (result < n)
				result++;
			else
				result--;
			bnt_cnt++;
		}

		int bnt_cnt_second = abs(n - channel);

		if (bnt_cnt < bnt_cnt_second)
			cout << bnt_cnt;
		else
			cout << bnt_cnt_second;
	}
	else {
		int bnt_cnt_second = abs(n - channel);
		cout << bnt_cnt_second;
	}

	return 0;
}