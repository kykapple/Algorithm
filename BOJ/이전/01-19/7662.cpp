#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int t;
	cin >> t;

	multiset<int> s;	// multiset - 중복 가능, 자동 정렬(오름차순)
	for (int i = 0; i < t; i++) {
		int k;
		cin >> k;

		for (int j = 0; j < k; j++) {
			char ch;
			int n;

			cin >> ch >> n;


			switch (ch) {
			case 'I':
				s.insert(n);
				break;
			case 'D':
				if (s.empty()) continue;

				if (n == 1) {	// 최대값 삭제
					auto iter = s.end();
					iter--;
					s.erase(iter);
				}
				else if (n == -1) {		// 최소값 삭제
					auto iter = s.begin();
					s.erase(iter);
				}
			}
		}

		if (s.empty())
			cout << "EMPTY" << '\n';
		else {
			auto max = s.end();
			max--;
			auto min = s.begin();
			cout << *max << " " << *min << '\n';
		}
		
		s.clear();
	}

	return 0;
}