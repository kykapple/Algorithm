#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m;
	int* cards;

	cin >> n >> m;
	cards = new int[n];

	for (int i = 0; i < n; i++)
		cin >> cards[i];

	int sum = 0, result = 0;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			for (int k = j + 1; k < n; k++) {
				sum += cards[i] + cards[j] + cards[k];
				if (sum <= m && result < sum)
					result = sum;
				sum = 0;
			}
		}
	}

	cout << result;

	return 0;
}
