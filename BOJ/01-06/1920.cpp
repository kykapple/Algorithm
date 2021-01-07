#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m, i, j;
	int* A, * input;
	vector<int> vec;

	cin >> n;
	A = new int[n];

	for (i = 0; i < n; i++)
		cin >> A[i];

	sort(A, A + n);

	cin >> m;
	input = new int[m];

	for (i = 0; i < m; i++) {
		cin >> input[i];

		int low = 0, high = n - 1;
		bool flag = 0;
		while (low <= high) {
			int mid = (high + low) / 2;

			if (input[i] == A[mid]) {
				vec.push_back(1);
				flag = 1;
				break;
			}
			else if (input[i] < A[mid])
				high = mid - 1;
			else
				low = mid + 1;
		}
		if (!flag)
			vec.push_back(0);
	}

	for (int t : vec)
		cout << t << '\n';

	return 0;
}
