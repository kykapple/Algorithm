#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	queue<int> q;

	for (int i = 1; i <= n; i++)
		q.push(i);

	while (true) {
		int temp = q.front();
		q.pop();
		if (q.empty()) {
			cout << temp;
			break;
		}
		temp = q.front();
		q.pop();
		q.push(temp);
	}

	return 0;
}