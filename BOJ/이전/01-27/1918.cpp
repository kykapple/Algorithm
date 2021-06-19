#include <bits/stdc++.h>
using namespace std;

int getSize(char ch) {
	switch (ch) {
	case '+' : case '-' :
		return 1;
	case '*' : case '/' :
		return 2;
	case '(' :
		return 0;
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	string str;
	cin >> str;

	stack<char> s;
	string result = "";
	for (int i = 0; i < str.length(); i++) {
		char ch = str[i];

		if ('A' <= ch && ch <= 'Z')
			result += ch;
		else {
			switch (ch) {
			case '+': case '-':
			case '*': case '/':
				while (!s.empty() && getSize(s.top()) >= getSize(ch)) {
					result += s.top();
					s.pop();
				}
				s.push(ch);
				break;
			case '(':
				s.push(ch);
				break;
			case ')' :
				while (!s.empty() && s.top() != '(') {
					result += s.top();
					s.pop();
				}
				s.pop();
				break;
			}
		}
	}

	while (!s.empty()) {
		result += s.top();
		s.pop();
	}

	cout << result;

	return 0;
}