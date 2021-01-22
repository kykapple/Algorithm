#include <bits/stdc++.h>
using namespace std;

int party[51][51];
int truth[51];
int n, m, p;
bool visited[51];

void Brute() {

	for(int i=0; i<m; i++) {
		if (!visited[i]) {
			for (int j = 0; j < n; j++) {
				if (party[i][j] && truth[party[i][j]]) {
					visited[i] = true;
				}
			}

			if (visited[i]) {
				for (int j = 0; j < n; j++) {
					if (!truth[party[i][j]])
						truth[party[i][j]] = 1;
				}
			}
		}
	}
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> n >> m;

	cin >> p; // 진실을 아는 사람의 수

	for (int i = 0; i < p; i++) {
		int val;
		cin >> val;

		truth[val] = 1;
	}

	for (int i = 0; i < m; i++) {
		int num;
		cin >> num;

		for (int j = 0; j < num; j++) {
			int temp;
			cin >> temp;
			party[i][j] = temp;
		}
	}
	
	for(int i=0; i<m; i++) 
		Brute();

	int ans = 0;
	for (int i = 0; i < m; i++)
		if (!visited[i])
			ans++;

	cout << ans;

	return 0;
}