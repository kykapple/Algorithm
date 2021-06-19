#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int num;
	int* list;
	stack<int> s;
	queue<char> q;

	cin >> num;
	list = new int[num];

	for (int i = 0; i < num; i++) 
		cin >> list[i];
	

	int idx = 0;
	bool flag = 0;
	for (int i = 1; i <= num; i++) {
		s.push(i);
		q.push('+');
		
		while (true) {
			if (!s.empty() && list[idx] == s.top()) {
				int temp = s.top();
				s.pop();
				q.push('-');
				idx++;
			}
			else
				break;
		}
	}

	if(s.empty()) {
		while (!q.empty()) {
			char ch = q.front();
			q.pop();
			cout << ch << '\n';
		}
	}
	else
		cout << "NO";
		

	return 0;
}
