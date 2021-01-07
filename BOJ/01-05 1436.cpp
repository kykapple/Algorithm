#include <bits/stdc++.h>
using namespace std;

int arr[10000];

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int num;
	cin >> num;

	int temp = 666;

	for (int i = 0; i < num; i++) {
		while (true) {
			int val = temp, cnt = 0;
			while (val) {
				if (val % 10 == 6) cnt++;
				else cnt = 0;

				if (cnt >= 3) break;
				val /= 10;
			}
			if (cnt >= 3) break;
			else temp++;
		}
		arr[i] = temp;
		temp++;
	}

	cout << arr[num-1];

	return 0;
}