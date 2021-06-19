#include <bits/stdc++.h>
using namespace std;

set<int> set_temp = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 };

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	set<int> s;

	int m;
	cin >> m;

	for (int i = 0; i < m; i++) {
		string str;
		cin >> str;
		
		int n;
		if (str == "add") {
			cin >> n;
			if(s.find(n) == s.end())
				s.insert(n);
		}
		else if (str == "remove") {
			cin >> n;
			s.erase(n);
		}
		else if (str == "check") {
			cin >> n;
			if (s.find(n) != s.end())
				cout << 1 << '\n';
			else
				cout << 0 << '\n';
		}
		else if (str == "toggle") {
			cin >> n;
			if (s.find(n) != s.end())
				s.erase(n);
			else
				s.insert(n);
		}
		else if (str == "all") {
			s = set_temp;
		}
		else if (str == "empty") {
			s.clear();
		}
	}

	return 0;
}