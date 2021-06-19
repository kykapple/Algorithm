#include <bits/stdc++.h>
using namespace std;

bool compare(pair<int, string> p1, pair<int, string> p2) {
	return p1.first < p2.first;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;
	
	vector <pair<int, string>> vec;
	for (int i = 0; i < n; i++) {
		int age;
		string name;
		cin >> age >> name;

		vec.push_back(make_pair(age, name));
	}

	stable_sort(vec.begin(), vec.end(), compare);

	for (pair<int, string> p : vec) {
		cout << p.first << " " << p.second << '\n';
	}

	return 0;
}