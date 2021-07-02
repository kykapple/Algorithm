## 시계 방향으로 배열 돌리기
```
public int[][] rotate(int[][] arr, int n) {
  int[][] new_arr = new int[n][n];

  for(int i=0; i<n; i++) {
    for(int j=0; j<n; j++) {
      new_arr[j][n-i-1] = arr[i][j];
    }
  }

  return new_arr;
}
```

## 반시계 방향으로 배열 돌리기
```
public int[][] rotate(int[][] arr, int n) {
  int[][] new_arr = new int[n][n];

  for(int i=0; i<n; i++) {
    for(int j=0; j<n; j++) {
      new_arr[n-j-1][i] = arr[i][j];  
    }
  }

  return new_arr;
}
```
