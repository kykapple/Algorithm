#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	vector<string> vec;
	while (true) {
		string str;
		getline(cin, str);
		if (str == ".") break;

		stack<char> s;

		bool flag = true;
		for (int i = 0; i < str.length(); i++) {
			if (str[i] == '(' || str[i] == '[')
				s.push(str[i]);
			else if (str[i] == ')') {
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
			else if (str[i] == ']') {
				if (!s.empty()) {
					if (s.top() != '[') {
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
			while (!s.empty()) s.pop();
		}
		
		if (flag)
			vec.push_back("yes");
		else
			vec.push_back("no");
	}

	for (string str : vec)
		cout << str << '\n';

	return 0;
}