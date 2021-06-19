#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, m;
	cin >> n >> m;

	map<int, string> for_num;
	map<string, int> for_str;
	for (int i = 1; i <= n; i++) {
		string name;
		cin >> name;

		for_num.insert(make_pair(i, name));
		for_str.insert(make_pair(name, i));
	}

	vector<string> result;
	for (int i = 0; i < m; i++) {
		string str;
		cin >> str;

		bool flag = true;
		for (int j = 0; j < str.length(); j++) {
			if (!isdigit(str[j])) {
				flag = false;
				break;
			}
		}
		if (flag) {
			result.push_back(for_num.find(stoi(str))->second);
		}
		else {
			result.push_back(to_string(for_str.find(str)->second));
		}
	}

	for (string str : result)
		cout << str << '\n';

	return 0;
}