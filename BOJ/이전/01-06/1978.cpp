#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	int* arr = new int[n];

	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	int nums[1001];
	for (int i = 1; i <= 1000; i++) {
		nums[i] = i;
	}

	nums[1] = 0;
	
	for (int i = 2; i <= 1000; i++) {
		if (nums[i] == 0)continue;

		for (int j = 2; i * j <= 1000; j++) {
			nums[i * j] = 0;
		}
	}

	int cnt = 0;
	for (int i = 0; i < n; i++) {
		if (nums[arr[i]])
			cnt++;
	}

	cout << cnt;

	return 0;
}
