#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int t;
	cin >> t;

	vector<string> result;

	for (int i = 0; i < t; i++) {
		string str;
		cin >> str;

		stack<char> s;

		bool flag = true;
		for (int j = 0; j < str.length(); j++) {
			if (str[j] == '(')
				s.push(str[j]);
			else if (str[j] == ')') {
				if (!s.empty()) {
					if (s.top() != '(') {
						flag = false;
						break;
					}
					else
						s.pop();
				}
				else {
					flag = false;
					break;
				}
			}
		}

		if (!s.empty()) {
			flag = false;
			while(!s.empty())
				s.pop();
		}

		if (flag)
			result.push_back("YES");
		else
			result.push_back("NO");
	}

	for (string str : result)
		cout << str << '\n';

	return 0;
}