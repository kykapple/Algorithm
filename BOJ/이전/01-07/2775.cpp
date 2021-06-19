#include <bits/stdc++.h>
using namespace std;


int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int t;
	cin >> t;

	vector<int> vec;
	for (int i = 0; i < t; i++) {
		int k, n;
		cin >> k >> n;

		int** apt = new int* [k];
		for (int j = 0; j < k; j++) {
			apt[j] = new int[n];
			for (int v = 0; v < n; v++)
				apt[j][v] = 0;
		}
		for (int j = 0; j < n; j++)
			apt[0][j] = j + 1;


		for (int j = 1; j < k; j++) {
			for (int v = 0; v < n; v++) {
				for(int z = 0; z<=v; z++)
					apt[j][v] += apt[j - 1][z];
			}
		}

		int result = 0;
		for (int j = 0; j < n; j++)
			result += apt[k - 1][j];

		vec.push_back(result);
	}

	for (int i : vec)
		cout << i << '\n';

	return 0;
}
