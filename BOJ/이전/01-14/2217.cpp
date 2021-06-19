#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	vector<int> ropes;
	vector<int> result;
	for (int i = 0; i < n; i++) {
		int val;
		cin >> val;
		ropes.push_back(val);
		result.push_back(1);
	}

	sort(ropes.begin(), ropes.end());

	for (int i = 0, j = n; i < n; i++, j--) {
		result[i] = ropes[i] * j;
	}

	sort(result.begin(), result.end());

	cout << result[n - 1];

	return 0;
}