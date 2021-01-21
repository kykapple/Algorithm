#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	vector<int> vec;
	vector<pair<int,int>> temp;
	for (int i = 0; i < n; i++) {
		int v;
		cin >> v;
		vec.push_back(v);
		temp.push_back({ v, 0 });
	}

	sort(temp.begin(), temp.end());

	int value = 0;
	for (int i = 1; i < vec.size(); i++) {
		if (temp[i].first == temp[i - 1].first)
			temp[i].second = temp[i - 1].second;
		else
			temp[i].second = ++value;
	}

	for (int i = 0; i < n; i++) {
		int start = 0, end = n - 1;
		while (start <= end) {
			int mid = (end + start) / 2;

			if (temp[mid].first == vec[i]) {
				cout << temp[mid].second << " ";
				break;
			}

			if (temp[mid].first > vec[i])
				end = mid - 1;
			else
				start = mid + 1;
		}
	}

	return 0;
}
