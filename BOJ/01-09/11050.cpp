#include <bits/stdc++.h>
using namespace std;

int factorial(int num) {
	if (num < 1)
		return 1;

	return num * factorial(num - 1);
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, k;
	cin >> n >> k;

	int result = factorial(n) / (factorial(k) * factorial(n - k));

	cout << result;

	return 0;
} 