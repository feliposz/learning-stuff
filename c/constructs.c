int test_condition();
int do_a();
int do_b();
int do_c();
int do_d();
int do_e();
void test_if();
void test_ifelse();
void test_ifelseifelse();
void test_switch();
void test_while();
void dest_dowhile();
void test_for();

int main()
{
    test_if();
    test_ifelse();
    test_ifelseifelse();
    test_switch();
    test_while();
    dest_dowhile();
    test_for();
}

int do_a() {return 1;}
int do_b() {return 1;}
int do_c() {return 1;}
int do_d() {return 1;}
int do_e() {return 1;}

int test_condition()
{
    int a = 1;
    int b = 2;
    int c = 3;
    do_a();
    if (a > b && b > c)
        return 1;
    do_b();
    if (a < b || b < c)
        return 2;
    do_c();
    if ((a == b) && !(a == c || b >= c))
        return 3;
    do_d();
    return 4;
}

void test_if()
{
    do_a();
    if (test_condition())
        do_b();
    do_c();
}

void test_ifelse()
{
    do_a();
    if (test_condition())
        do_b();
    else
        do_c();
    do_d();
}

void test_ifelseifelse()
{
    do_a();
    if (test_condition())
        do_b();
    else if (!test_condition())
        do_c();
    else
        do_d();
    do_e();
}

void test_switch()
{
    do_a();
    switch (test_condition()) {
        case 13: 
        case 27: do_b(); break;
        case 35: do_c(); break;
        default: do_d(); break;
    }
    do_e();
}

void test_while()
{
    do_a();
    while (test_condition())
        do_b();
    do_c();
}

void test_dowhile()
{
    do_a();
    do
        do_b();
    while (test_condition());
    do_c();
}

void test_for()
{
    int i;
    do_a();
    for (i = 14; i < 232; i *= 3)
        do_d();
    do_e();
}

