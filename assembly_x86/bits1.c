#include <stdio.h>

void exemplo1(void);
void exemplo2(void);

int main(int argc, char *argv[])
{
    printf("Operacoes com bits em C\n");
    exemplo1();
    exemplo2();
    return 0;
}

void exemplo1(void)
{
    short int s;
    short unsigned u;
    
    printf("\nExemplo 1 - Operações básicas\n");
    
    s = -1;
    printf("s = %.4hX (%hd)\n", s, s);
    u = 100;
    printf("u = %.4hX (%hu)\n", u, u);
    u = u | 0x0100;
    printf("u = %.4hX (%hu)\n", u, u);
    s = s & 0xFFF0;
    printf("s = %.4hX (%hd)\n", s, s);
    s = s ^ u;
    printf("s = %.4hX (%hd)\n", s, s);
    u = u << 3;
    printf("u = %.4hX (%hu)\n", u, u);
    s = s >> 2;
    printf("s = %.4hX (%hd)\n", s, s);
}

void exemplo2(void)
{
    unsigned long dword = 0x12345678;
    unsigned char *p = (unsigned char *) &dword;
    int i;
    
    printf("\nExemplo 2 - Determina se computador e' big/little endian\n");
    
    if (p[0] == 0x12)
        printf("Computador e' big-endian.\n");
    else
        printf("Computador e' little-endian.\n");
    
    printf("Valor (hexa) = %.8X\n", dword);
    printf("Bytes (hexa) = ");
    for (i = 0; i < 4; i++)
        printf("0x%.2X ", *(p + i));
    printf("\n");
}