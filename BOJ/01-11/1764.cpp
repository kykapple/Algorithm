#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m;
	cin >> n >> m;

	map<string, int> list1;
	for (int i = 0; i < n; i++) {
		string name;
		cin >> name;

		list1.insert(make_pair(name, i));
	}

	int cnt = 0;
	vector<string> result;

	for (int i = 0; i < m; i++) {
		string name;
		cin >> name;

		if (list1.find(name) != list1.end()) {
			result.push_back(name);
			cnt++;
		}
	}

	cout << cnt << '\n';

	sort(result.begin(), result.end());
	for (string name : result)
		cout << name << '\n';

	return 0;
}