#include <bits/stdc++.h>
using namespace std;

bool compare(pair<int, int> p1, pair<int, int>p2) {
	if (p1.first < p2.first)
		return true;
	else if (p1.first == p2.first) {
		if (p1.second < p2.second)
			return true;
		else return false;
	}
	else return false;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	vector<pair<int, int>> vec;
	for (int i = 0; i < n; i++) {
		int x, y;
		cin >> x >> y;

		vec.push_back(make_pair(x, y));
	}

	sort(vec.begin(), vec.end(), compare);

	for (pair<int, int> p : vec)
		cout << p.first << " " << p.second << '\n';

	return 0;
} 