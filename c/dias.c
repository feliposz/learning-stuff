#include <stdio.h>

int main()
{
    int i;
    char *dias[7];

    dias[0] = "Domingo";
    dias[1] = "Segunda";
    dias[2] = "Terca";
    dias[3] = "Quarta";
    dias[4] = "Quinta";
    dias[5] = "Sexta";
    dias[6] = "Sabado";

    for (i = 0; i < 7; i++) {
        printf("%s\n", dias[i]);
    }

    int *p = &i;

    printf("%d\n", *p);
}
