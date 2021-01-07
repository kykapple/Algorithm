#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int t;
	cin >> t;

	vector<string> vec;
	for (int i = 0; i < t; i++) {
		int height, width, n;
		cin >> height >> width >> n;

		int result, h = 0, w = 0;
		for (int j = 0; j < n; j++) {
			if (j == 0) {
				h = 1;
				w = 1;
			}
			else if (h < height) {
				h++;
			}
			else {
				h = 1;
				w++;
			}
		}
		
		if (w / 10 == 0)
			vec.push_back(to_string(h) + "0" + to_string(w));
		else
			vec.push_back(to_string(h) + to_string(w));
	}

	for (string str : vec)
		cout << str << '\n';

	return 0;
}