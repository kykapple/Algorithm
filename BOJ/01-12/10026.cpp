#include <bits/stdc++.h>
using namespace std;

char graph_normal[100][100];
char graph_special[100][100];
bool visited_normal[100][100];
bool visited_special[100][100];
int n;

void DFS(char ch, int i, int j, bool flag) {
	if (i < 0 || i >= n || j < 0 || j >= n)
		return;

	if (flag) {
		if (graph_normal[i][j] == ch) {
			visited_normal[i][j] = true;
			
			graph_normal[i][j] = 'X';

			DFS(ch, i + 1, j, true);
			DFS(ch, i - 1, j, true);
			DFS(ch, i, j + 1, true);
			DFS(ch, i, j - 1, true);

		}
	}
	else {
		if (graph_special[i][j] == ch) {
			visited_special[i][j] = true;
			
			graph_special[i][j] = 'X';

			DFS(ch, i + 1, j, false);
			DFS(ch, i - 1, j, false);
			DFS(ch, i, j + 1, false);
			DFS(ch, i, j - 1, false);
		}
	}
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> graph_normal[i][j];
			if (graph_normal[i][j] == 'G')
				graph_special[i][j] = 'R';
			else
				graph_special[i][j] = graph_normal[i][j];
		}
	}

	int normal = 0, special = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!visited_normal[i][j]) {
				DFS('R', i, j, true);
				DFS('G', i, j, true);
				DFS('B', i, j, true);
				normal++;
			}
		}
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!visited_special[i][j]) {
				DFS('R', i, j, false);
				DFS('B', i, j, false);
				special++;
			}
		}
	}

	cout << normal << ' ' << special;

	return 0;
}