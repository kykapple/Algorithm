#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int t;
	cin >> t;

	vector < pair<int, int>> vec;
	for (int i = 0; i < t; i++) {
		int test;
		cin >> test;

		pair<int, int> zero = make_pair(1, 0);
		pair<int, int> one = make_pair(0, 1);
		pair<int, int> result = make_pair(0, 0);

		if (test == 0)
			vec.push_back(zero);
		else if (test == 1)
			vec.push_back(one);
		else {
			for (int j = 2; j <= test; j++) {
				result.first = (zero.first + one.first);
				result.second = (zero.second + one.second);
				zero = one;
				one = result;
			}
			vec.push_back(result);
		}
	}

	for (pair<int, int> p : vec)
		cout << p.first << ' ' << p.second << '\n';

	return 0;
} 
