#include <bits/stdc++.h>
using namespace std;

int inorder[100000];
int postorder[100000];
int temp[100001];

void prefix(int in_start, int in_end, int post_start, int post_end) {
	
	if (in_start > in_end || post_start > post_end) return;

	int root = postorder[post_end];
	cout << root << " ";
	
	int p = temp[root] - in_start;
	prefix(in_start, in_start + p - 1, post_start, post_start + p - 1);
	prefix(in_start + p + 1, in_end, post_start + p, post_end - 1);
}

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	for (int i = 0; i < n; i++)
		cin >> inorder[i];

	for (int i = 0; i < n; i++)
		cin >> postorder[i];

	for (int i = 0; i < n; i++)
		temp[inorder[i]] = i;

	prefix(0, n - 1, 0, n - 1);

	return 0;
}