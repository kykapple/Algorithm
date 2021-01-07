#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	int* nums = new int[n];
	int sum = 0;
	map<int, int> m;

	int max = 0;
	for (int i = 0; i < n; i++) {
		cin >> nums[i];
		sum += nums[i];

		if (m.count(nums[i])) {
			m.find(nums[i])->second++;
			if(max < m.find(nums[i])->second)
				max = m.find(nums[i])->second;
		}
		else {
			m.insert(make_pair(nums[i], 1));
		}
	}

	sort(nums, nums + n);

	int avg = round((double)sum / n);
	int mid = nums[n / 2];
	int mode;

	vector<int> modes;
	for (auto it = m.begin(); it != m.end(); it++) {
		if (max <= it->second) {
			modes.push_back(it->first);
			max = it->second;
		}
	}

	sort(modes.begin(), modes.end());
	if (modes.size() > 1)
		mode = modes[1];
	else
		mode = modes[0];

	int scope = nums[n - 1] - nums[0];

	cout << avg << '\n';
	cout << mid << '\n';
	cout << mode << '\n';
	cout << scope;

	return 0;
}
