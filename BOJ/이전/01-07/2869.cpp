#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	long long a,b,v;
	cin >> a >> b >> v;

	int days = 0;

	v -= a;
	days++;

	if (v / (a - b))
		days += v / (a - b);
	
	if (v % (a - b) != 0)
		days++;

	cout << days;

	return 0;
}