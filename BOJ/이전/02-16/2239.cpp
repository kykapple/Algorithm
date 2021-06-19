#include <bits/stdc++.h>
using namespace std;

int puzzle[9][9];

void tracking(int cnt) {
    if (cnt == 81) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                printf("%d", puzzle[i][j]);
            }
            printf("\n");
        }

        exit(0);
    }

    int x = cnt / 9;    // 행 구하기
    int y = cnt % 9;    // 열 구하기

    if (puzzle[x][y]) tracking(cnt + 1);
    else {
        for (int k = 1; k <= 9; k++) {
            bool flag = true;

            for (int i = 0; i < 9; i++) {
                if (puzzle[x][i] == k || puzzle[i][y] == k) {
                    flag = false;
                    break;
                }
            }

            int nx = (x / 3) * 3;
            int ny = (y / 3) * 3;

            for (int i = nx; i < nx + 3; i++) {
                for (int j = ny; j < ny + 3; j++) {
                    if (puzzle[i][j] == k) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                puzzle[x][y] = k;
                tracking(cnt + 1);
                puzzle[x][y] = 0;
            }
        }
    }
}

int main(void) {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            scanf_s("%1d", &puzzle[i][j]);
        }
    }

    tracking(0);

    return 0;
}