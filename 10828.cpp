#include <bits/stdc++.h>
using namespace std;

class Stack;
class Node {
	friend class Stack;
private :
	int data;
	Node* next;
};

class Stack {
private :
	Node* head;
public :
	Stack() {
		head = NULL;
	}

	void push(int data) {
		Node* newNode = new Node();
		newNode->data = data;
		newNode->next = head;

		head = newNode;
	}

	int empty() {
		if (head) return 0;
		else return 1;
	}

	int pop() {
		if (head) {
			int val = head->data;
			head = head->next;

			return val;
		}
		else
			return -1;
	}

	int size() {
		int cnt = 0;
		if (head) {
			for (Node* p = head; p; p = p->next)
				cnt++;
		}

		return cnt;
	}

	int top() {
		if (head) return head->data;
		else return -1;
	}
};

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	string n;	
	getline(cin, n);

	Stack s;
	vector<int> result;
	for (int i = 0; i < stoi(n); i++) {
		string str;
		getline(cin, str);

		stringstream ss(str);

		string v;
		ss >> v;

		if (v == "push") {
			ss >> v;
			s.push(stoi(v));
		}
		else if (v == "pop")
			result.push_back(s.pop());
		else if (v == "empty")
			result.push_back(s.empty());
		else if (v == "top")
			result.push_back(s.top());
		else if (v == "size")
			result.push_back(s.size());
	}
	
	for (int i : result)
		cout << i << '\n';

	return 0;
} 