#include <bits/stdc++.h>
using namespace std;

class Deque;
class Node {
	friend class Deque;
private :
	int data;
	Node* next;
};

class Deque {
private :
	Node* head;
	Node* tail;
public :
	Deque() {
		head = NULL;
		tail = NULL;
	}

	void push_front(int data) {
		Node* newNode = new Node();
		newNode->data = data;
		newNode->next = NULL;

		if (!head) {
			head = newNode;
			tail = newNode;
		}
		else {
			newNode->next = head;
			head = newNode;
		}
	}

	void push_back(int data) {
		Node* newNode = new Node();
		newNode->data = data;
		newNode->next = NULL;

		if (!head) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail->next = newNode;
			tail = newNode;
		}
	}

	int empty() {
		if (head) return 0;
		else return 1;
	}

	int pop_front() {
		if (head) {
			int val = head->data;

			if (head == tail) {
				head = NULL;
				tail = NULL;
			}
			else
				head = head->next;

			return val;
		}
		else
			return -1;
	}

	int pop_back() {
		if (tail) {
			int val = tail->data;

			if (head == tail) {
				head = NULL;
				tail = NULL;
			}
			else {
				Node* p = head;
				Node* q = NULL;

				while (p != tail) {
					q = p;
					p = p->next;
				}


				tail = q;
				tail->next = NULL;
			}

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

	int front() {
		if (head) return head->data;
		else return -1;
	}

	int back() {
		if (tail) return tail->data;
		else return -1;
	}
};

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	string n;	
	getline(cin, n);

	Deque s;
	vector<int> result;
	for (int i = 0; i < stoi(n); i++) {
		string str;
		getline(cin, str);

		stringstream ss(str);

		string v;
		ss >> v;

		if (v == "push_back") {
			ss >> v;
			s.push_back(stoi(v));
		}
		else if (v == "push_front") {
			ss >> v;
			s.push_front(stoi(v));
		}
		else if (v == "pop_front")
			result.push_back(s.pop_front());
		else if (v == "pop_back")
			result.push_back(s.pop_back());
		else if (v == "empty")
			result.push_back(s.empty());
		else if (v == "front")
			result.push_back(s.front());
		else if (v == "size")
			result.push_back(s.size());
		else if (v == "back")
			result.push_back(s.back());
	}
	
	for (int i : result)
		cout << i << '\n';

	return 0;
} 