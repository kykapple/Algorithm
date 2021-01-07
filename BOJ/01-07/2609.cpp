#include <bits/stdc++.h>
using namespace std;

int getVal(int val1, int val2) {
	while (val2) {
		int temp = val1 % val2;
		val1 = val2;
		val2 = temp;
	}
	return val1;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int val1, val2;
	cin >> val1 >> val2;

	int result1;
	if (val1 > val2)
		result1 = getVal(val1, val2);
	else
		result1 = getVal(val2, val1);

	int i = 1, temp;
	while (true) {
		temp = result1 * i;
		if (temp % val1 == 0 && temp % val2 == 0) break;
		i++;
	}

	cout << result1 << '\n';
	cout << temp;

	return 0;
}
