#include <bits/stdc++.h>
using namespace std;

class Graph;
class Node {
	friend class Graph;
private:
	int data;
	int weight;
	Node* link;
};

typedef Node* NodePointer;

class Graph {
private :
	NodePointer* HeadNodes;
	int v;
	bool* visited;
	int result, idx;
public :
	Graph(int v) {
		this->v = v;
		result = 0 ,idx = 0;
		HeadNodes = new NodePointer[v+1];
		visited = new bool[v+1];

		for (int i = 0; i <= v; i++)
			HeadNodes[i] = NULL;
		for (int i = 0; i <= v; i++) visited[i] = false;
	}

	void Insert(int u, int v, int w) {
		Node* newNode = new Node();
		newNode->data = v; newNode->weight = w;
		newNode->link = HeadNodes[u];
		HeadNodes[u] = newNode;
	}

	void clear() {
		for (int i = 0; i <= v; i++) visited[i] = false;
	}

	void DFS(int start, int sum) {
		if (!visited[start]) {
			visited[start] = true;

			for (Node* cur = HeadNodes[start]; cur; cur = cur->link) {
				if (!visited[cur->data]) {
					DFS(cur->data, sum + cur->weight);
				}
			}

			if (result < sum) {
				result = sum;
				idx = start;
			}
		}
	}

	int getidx() {
		return idx;
	}

	void show() {
		cout << result;
	}
};

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int v;
	cin >> v;

	Graph graph(v);
	for (int i = 0; i < v; i++) {
		int u;
		cin >> u;

		while (true) {
			int k, w;
			cin >> k;

			if (k == -1) break;
			cin >> w;

			graph.Insert(u, k, w);
		}
	}

	graph.DFS(1, 0);
	graph.clear();
	graph.DFS(graph.getidx(), 0);

	graph.show();
	
	return 0;
}