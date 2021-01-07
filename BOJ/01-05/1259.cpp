#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	vector<string> is;

	while (true) {
		string str;
		cin >> str;
		if (str == "0") break;

		bool test = true;
		for (int i = 0; i < str.length() / 2; i++) {
			if (str[i] != str[str.length() - 1 - i]) {
				test = false;
				break;
			}
		}
		if (test) {
			is.push_back("yes");
		}
		else
			is.push_back("no");
	}

	for (string str : is)
		cout << str << '\n';

	return 0;
}
