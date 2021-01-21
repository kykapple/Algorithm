#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n, m;
	cin >> n >> m;

	map<string, string> info;
	for (int i = 0; i < n; i++) {
		string str1, str2;
		cin >> str1 >> str2;

		info.insert({ str1,str2 });
	}

	for (int i = 0; i < m; i++) {
		string str;
		cin >> str;

		cout << info[str] << '\n';
	}
	return 0;
}