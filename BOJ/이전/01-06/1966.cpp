#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int num, i, j;
	cin >> num;
	vector<int> orders;

	for (i = 0; i < num; i++) {
		int n, m;
		cin >> n >> m;
		queue<pair<int, int>> q;
		priority_queue<int> prior;

		for (j = 0; j < n; j++) {
			int val;
			cin >> val;
			q.push(make_pair(j, val));
			prior.push(val);
		}	

		int order = 0;
		while (!q.empty()) {
			int index = q.front().first;
			int value = q.front().second;
			q.pop();

			if (value == prior.top()) {
				prior.pop();
				order++;

				if (index == m) {
					orders.push_back(order);
					break;
				}
			}
			else 
				q.push(make_pair(index, value));
		}
	}

	for (int val : orders)
		cout << val << '\n';

	return 0;
}
