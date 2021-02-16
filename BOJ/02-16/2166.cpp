#include <bits/stdc++.h>
using namespace std;

double x[10001];
double y[10001];

int main(void) {
    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
    }

    x[n] = x[0];
    y[n] = y[0];

    double sum = 0;
    for (int i = 0; i < n; i++) {
        sum += (x[i] * y[i + 1]) - (x[i + 1] * y[i]);
    }

    printf("%.1lf", fabs(sum) / 2);

    return 0;
}