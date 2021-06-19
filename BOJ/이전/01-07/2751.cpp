#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	int* list = new int[n];
	for (int i = 0; i < n; i++)
		cin >> list[i];

	sort(list, list + n);
	for (int i = 0; i < n; i++)
		cout << list[i] << '\n';

	return 0;
}
