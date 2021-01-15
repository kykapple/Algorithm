#include <bits/stdc++.h>
using namespace std;

long long dp[91];

long long fibonacci(int x) {
	if (x == 0)
		return dp[x];
	else if (dp[x])
		return dp[x];
	dp[x] = fibonacci(x - 2) + fibonacci(x - 1);
	return dp[x];
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	dp[0] = 0;
	dp[1] = 1;
	
	cout << fibonacci(n);

	return 0;
}