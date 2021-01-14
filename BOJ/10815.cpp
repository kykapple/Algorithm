#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	vector<int> cards;
	for (int i = 0; i < n; i++) {
		int val;
		cin >> val;
		cards.push_back(val);
	}

	sort(cards.begin(), cards.end());

	int m;
	cin >> m;

	vector<int> result;
	for (int i = 0; i < m; i++) {
		int val;
		cin >> val;
		
		int start = 0, end = n - 1;
		bool flag = false;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (cards[mid] == val) {
				result.push_back(1);
				flag = true;
				break;
			}
			else if (cards[mid] > val)
				end = mid - 1;
			else
				start = mid + 1;
		}
		if (!flag)
			result.push_back(0);
	}

	for (int i : result)
		cout << i << " ";

	return 0;
}