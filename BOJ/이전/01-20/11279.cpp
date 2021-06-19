#include <bits/stdc++.h>
using namespace std;

class Heap {
private:
	int* heap;
	int idx;
public :
	Heap(int n) {
		heap = new int[n];
		idx = 0;
	}

	void Insert(int data) {
		idx++;
		int temp = idx;

		while (temp / 2) {
			if (data < heap[temp / 2]) break;
			heap[temp] = heap[temp / 2];
			temp /= 2;
		}
		heap[temp] = data;
	}

	int del_max() {
		if (!idx) return 0;

		int delData = heap[1];
		int data = heap[idx];
		idx--;

		int i = 1, j = 2;
		while (j <= idx) {
			if (j < idx) if (heap[j] < heap[j + 1]) j++;
			if (data > heap[j]) break;
			heap[i] = heap[j];
			i = j; j *= 2;
		}
		heap[i] = data;

		return delData;
	}
};

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	Heap heap(n);
	vector<int> result;
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;

		switch (x) {
			case 0:
				result.push_back(heap.del_max());
				break;
			default:
				heap.Insert(x);
				break;
		}
	}

	for (int v : result)
		cout << v << '\n';

	return 0;
}