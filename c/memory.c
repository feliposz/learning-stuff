#include <stdio.h>
#include <stdlib.h>

int main()
{
    int *p1 = malloc(sizeof(int));
    int *p2 = malloc(sizeof(int));
    int *p3 = malloc(sizeof(int));

    *p1 = 10;
    *p2 = 20;
    *p3 = *p1 + *p2;

    printf("address of p1 = %p, p2 = %p, p3 = %p\n", &p1, &p2, &p3);
    printf("address pointed by p1 = %p, p2 = %p, p3 = %p\n", p1, p2, p3);
    printf("value at address pointed by p1 = %d, p2 = %d, p3 = %d\n", *p1, *p2, *p3);

    free(p1);
    free(p2);
    free(p3);

    return 0;
}
