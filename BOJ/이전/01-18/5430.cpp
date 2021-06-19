#include <bits/stdc++.h>
using namespace std;

vector<string> result;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		string str;
		cin >> str;

		queue<char> func;
		for (int j = 0; j < str.length(); j++)
			func.push(str[j]);

		int num;
		cin >> num;

		string value;
		cin >> value;

		deque<string> numbers;
		string temp = "";
		for (int j = 0; j < value.length(); j++) {
			if (value[j] != '[' && value[j] != ']' && value[j] != ',' && value[j] != '\n')
				temp += value[j];
			else {
				if (temp != "") {
					numbers.push_back(temp);
					temp = "";
				}
			}
		}

		bool rev = true, flag = false;
		while (!func.empty()) {
			char ch = func.front();
			func.pop();

			if (ch == 'R') {
				rev = !rev;
			}
			else if (ch == 'D') {
				if (!numbers.empty()) {
					if (rev)
						numbers.pop_front();
					else
						numbers.pop_back();
				}
				else {
					result.push_back("error");
					flag = true;
					break;
				}
			}
		}

		if (flag)
			continue;

		temp = "[";
		string ch = "";
		while (!numbers.empty()) {
			if (rev) {
				ch = numbers.front();
				numbers.pop_front();
			}
			else {
				ch = numbers.back();
				numbers.pop_back();
			}
			temp += ch;
			temp += ',';
		}

		if (ch == "")
			temp += "]";
		else
			temp[temp.length() - 1] = ']';

		result.push_back(temp);
	}

	for (string str : result)
		cout << str << "\n";

	return 0;
}
