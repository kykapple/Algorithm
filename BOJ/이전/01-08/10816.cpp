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
	
	map<int, int> cards;
	for (int i = 0; i < n; i++) {
		int cnt = 1;
		int num;

		cin >> num;
		if (cards.find(num) == cards.end())
			cards.insert(make_pair(num, cnt));
		else
			cards.find(num)->second++;
	}

	int m;
	cin >> m;

	int* values = new int[m];
	for (int i = 0; i < m; i++)
		cin >> values[i];

	vector<int> result;

	for (int i = 0; i < m; i++) {
		if (cards.find(values[i]) != cards.end())
			result.push_back(cards.find(values[i])->second);
		else
			result.push_back(0);
	}

	for (int i : result)
		cout << i << " ";

	return 0;
}