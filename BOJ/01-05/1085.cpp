#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int x, y, w, h;
	cin >> x >> y >> w >> h;

	int x_min = (x > w - x) ? w - x : x;
	int y_min = (y > h - y) ? h - y : y;

	int min = (x_min > y_min) ? y_min : x_min;
	cout << min;

	return 0;
}
