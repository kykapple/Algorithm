#define _CRT_SECURE_NO_WARNINGS

#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	string str;
	cin >> str;

	queue<int> nums;
	queue<char> opers;

	string temp = "";
	for (int i = 0; i < str.length(); i++) {
		
		if (str[i] == '-' || str[i] == '+') {
			if (i == 0)
				opers.push(str[i]);
			else {
				nums.push(stoi(temp));
				opers.push(str[i]);
				temp = "";
			}
		}
		else {
			temp += str[i];
		}
	}
	nums.push(stoi(temp));

	int sum = 0;
	if (nums.size() != opers.size()) {
		sum = nums.front();
		nums.pop();
	}

	int val = 0; bool flag = true;
	while (!nums.empty()) {
		char ch = opers.front();
		opers.pop();

		if (ch == '-') {
			sum += val;
			val = 0;
			val -= nums.front();
			nums.pop();
			flag = false;
		}
		else {
			if (!flag) {
				val -= nums.front();
				nums.pop();
			}
			else {
				val += nums.front();
				nums.pop();
			}
		}
	}

	sum += val;

	cout << sum;

	return 0;
}