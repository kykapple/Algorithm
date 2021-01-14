#include <bits/stdc++.h>
using namespace std;

int color[1001][4];

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= 3; j++) {
			cin >> color[i][j];
			
			if (i > 1) {
				int temp = 1000000;
				for (int k = 1; k <= 3; k++) {
					if (k != j) {
						temp = min(temp, color[i - 1][k]);
					}
				}
				color[i][j] = color[i][j] + temp;
			}
		}
	}

	int result = 1000000;
	for (int i = 1; i <= 3; i++) 
		result = min(result, color[n][i]);

	cout << result;

	return 0;
}