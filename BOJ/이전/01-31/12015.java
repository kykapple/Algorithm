#include <bits/stdc++.h>
using namespace std;

int values[1000000];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	vector<int> upper;
	for (int i = 0; i < n; i++)
		cin >> values[i];

	upper.push_back(values[0]);

	for (int i = 1; i < n; i++) {
		if (upper.back() < values[i]) {
			upper.push_back(values[i]);
		}
		else {
			vector<int>::iterator pos = lower_bound(upper.begin(), upper.end(), values[i]);
			*pos = values[i];
		}
	}
	
	cout << upper.size();

	return 0;
}