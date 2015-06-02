#include<iostream>
#include <stdio.h>
using namespace std;

int main()
{
    int *p1 = new int;
    int *p2 = new int;
    int *p3 = new int;

    *p1 = 10;
    *p2 = 20;
    *p3 = *p1 + *p2;

    cout << "address of p1 = " << &p1 << ", p2 = " << &p2 << ", p3 = " << &p3 << endl;
    cout << "address pointed by p1 = " << p1 << ", p2 = " << p2 << ", p3 = " << p3 << endl;
    cout << "value at address pointed by p1 = " << *p1 << ", p2 = " << *p2 << ", p3 = " << *p3 << endl;

    delete p1;
    delete p2;
    delete p3;

    return 0;
}
