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
				if (party[i][j] && truth[party[i][j]]) { // 해당 파티에 참여한 사람이 진실을 아는 경우
					visited[i] = true; // 해당 파티는 과장된 이야기를 할 수 없는 파티
				}
			}

			if (visited[i]) {
				for (int j = 0; j < n; j++) {
					if (!truth[party[i][j]]) // 그 파티에 있는 사람 모두 진실을 알게 됨
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
