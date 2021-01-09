#include <bits/stdc++.h>
using namespace std;

class Circle;
class Node {
	friend class Circle;
private :
	int data;
	Node* next;
};

class Circle {
private :
	Node* head;
	Node* tail;
	Node* cur;
public :
	Circle() {
		head = NULL;
		tail = NULL;
		cur = NULL;
	}

	void push(int data) {
		Node* newNode = new Node();
		newNode->data = data;
		
		if (!head) {
			head = newNode;
			tail = newNode;
			cur = head;
		}
		else {
			tail->next = newNode;
			tail = newNode;
		}
		tail->next = head;
	}

	bool empty() {
		if (head) return false;
		else return true;
	}

	void pop(int target) {
		if (head) {
			Node* p = head;
			Node* q = NULL;

			while (p) {
				if (p->data == target) break;
				q = p;
				p = p->next;
			}

			if (p == head) {
				if (head == tail) {
					head = NULL;
					tail = NULL;
					cur = NULL;
				}
				else {
					head = head->next;
					tail->next = head;
					cur = tail->next;
				}
			}
			else if (p == tail) {
				tail = q;
				tail->next = head;

				if (tail == head)
					cur = q;
				 else
					cur = q->next;
			}
			else {
				q->next = p->next;
				cur = q->next;
			}
		}
	}

	int purpose(int cnt) {
		if (cur) {
			for (int i = 0; i < cnt - 1; i++) {
				cur = cur->next;
			}
			int val = cur->data;

			pop(cur->data);
			
			return val;
		}

		return -1;
	}
};

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int n, k;
	cin >> n >> k;

	Circle c;

	for (int i = 1; i <= n; i++) {
		c.push(i);
	}

	cout << '<';
	while (true) {
		int val = c.purpose(k);
		if (c.empty()) {
			cout << val;
			break;
		}
		else 
			cout << val<< ", ";
	}
	cout << '>';

	return 0;
} 