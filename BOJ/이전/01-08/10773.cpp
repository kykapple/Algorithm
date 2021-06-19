#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int k;
	cin >> k;

	stack<int> s;
	for (int i = 0; i < k; i++) {
		int val;
		cin >> val;


		if (val != 0) 
			s.push(val);
		else {
			s.pop();
		}
	}

	int sum = 0;
	while (!s.empty()) {
		sum += s.top();
		s.pop();
	}

	cout << sum;

	return 0;
}