#include <bits/stdc++.h>
using namespace std;

class Tree;
class Node {
	friend class Tree;
private :
	char data;
	Node* left;
	Node* right;
};

class Tree {
private:
	Node* root;
public:
	Tree() {
		root = NULL;
	}

	Node* getNode(char ch) {
		Node* newNode = new Node();
		newNode->data = ch;
		newNode->left = NULL;
		newNode->right = NULL;

		return newNode;
	}

	void setRoot(Node* cur) {
		root = cur;
	}

	void setLeft(Node* cur, Node* left) {
		cur->left = left;
	}

	void setRight(Node* cur, Node* right) {
		cur->right = right;
	}

	void prefix() {
		prefix(root);
		cout << '\n';
	}

	void infix() {
		infix(root);
		cout << '\n';
	}

	void postfix() {
		postfix(root);
		cout << '\n';
	}

	void prefix(Node* cur) {
		if (!cur) return;

		cout << cur->data;
		prefix(cur->left);
		prefix(cur->right);
	}

	void infix(Node* cur) {
		if (!cur) return;

		infix(cur->left);
		cout << cur->data;
		infix(cur->right);
	}

	void postfix(Node* cur) {
		if (!cur) return;

		postfix(cur->left);
		postfix(cur->right);
		cout << cur->data;
	}
};

int main(void) {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int n;
	cin >> n;

	Tree t;

	Node** node_arr = new Node * [n];
	for (int i = 0; i < n; i++) {
		node_arr[i] = t.getNode('A' + i);
	}

	for (int i = 0; i < n; i++) {
		char c1, c2, c3;
		cin >> c1 >> c2 >> c3;

		if (c1 == 'A') {
			t.setRoot(node_arr[c1 - 65]);
		}

		if (c2 != '.') {
			t.setLeft(node_arr[c1 - 65], node_arr[c2 - 65]);
		}
		if (c3 != '.') {
			t.setRight(node_arr[c1 - 65], node_arr[c3 - 65]);
		}
	}

	t.prefix();
	t.infix();
	t.postfix();
	

	return 0;
}
