#include <bits/stdc++.h>
using namespace std;

class Heap {
private :
	int* heap;
	int idx;
public :
	Heap() {
		heap = new int[100001];
		idx = 0;
	}

	void Insert(int data) {
		idx++;
		int temp = idx;

		while (temp / 2) {
			if (data >= heap[temp / 2]) break;
			heap[temp] = heap[temp / 2];
			temp /= 2;
		}

		heap[temp] = data;
	}

	int remove() {
		if (idx > 0) {
			int delData = heap[1];
			int value = heap[idx];
			idx--;

			int i = 1, j = 2;
			while (j <= idx) {
				if (j < idx) if (heap[j] > heap[j + 1]) j++;
				if (value <= heap[j]) break;
				heap[i] = heap[j];
				i = j; j *= 2;
			}
			heap[i] = value;

			return delData;
		}
		else return 0;
	}
};

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n;
	cin >> n;

	Heap heap;
	vector<int> result;
	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;

		if (num == 0)
			result.push_back(heap.remove());
		else
			heap.Insert(num);
	}

	for (int i : result)
		cout << i << '\n';
		
	return 0;
}