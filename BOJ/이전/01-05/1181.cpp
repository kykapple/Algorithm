#include <bits/stdc++.h>
using namespace std;

bool compare(string str1, string str2) {
	if (str1.length() < str2.length()) return true;
	else if (str1.length() == str2.length()) {
		return str1 < str2;
	}
	else return false;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	string* words;

	int num;
	cin >> num;

	words = new string[num];
	for (int i = 0; i < num; i++) {
		cin >> words[i];
	}

	sort(words, words + num, compare);

	for (int i = 0; i < num; i++) {
		if (i == 0) cout << words[i] << '\n';
		else if(i > 0 && words[i-1] != words[i])
			cout << words[i] << '\n';
	}

	return 0;
}
