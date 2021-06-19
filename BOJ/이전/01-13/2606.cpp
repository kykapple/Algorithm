#include <bits/stdc++.h>
using namespace std;

class Graph;
class Node {
	friend class Graph;
private:
	int data;
	Node* next;
};

typedef Node* NodePointer;

class Graph {
private :
	NodePointer* HeadNodes;
	bool* visited;
public :
	int cnt;

	Graph(int n) {
		cnt = 0;
		HeadNodes = new NodePointer[n + 1];
		visited = new bool[n + 1];

		for (int i = 0; i <= n; i++) {
			HeadNodes[i] = NULL;
			visited[i] = false;
		}
	}

	void Insert(int u, int v) {
		Node* newNode = new Node();
		newNode->data = v;
		newNode->next = HeadNodes[u];
		HeadNodes[u] = newNode;

		newNode = new Node();
		newNode->data = u;
		newNode->next = HeadNodes[v];
		HeadNodes[v] = newNode;
	}

	void DFS(int start) {
		visited[start] = true;

		for (Node* p = HeadNodes[start]; p; p = p->next) {
			if (!visited[p->data]) {
				DFS(p->data);
				cnt++;
			}
		}
	}
};

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	Graph g(n);

	int num;
	cin >> num;
	for (int i = 0; i < num; i++) {
		int u, v;
		cin >> u >> v;

		g.Insert(u, v);
	}

	g.DFS(1);

	cout << g.cnt;

	return 0;
}