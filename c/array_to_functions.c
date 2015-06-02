#include <stdio.h>

#define ARY_SZ 10
#define ARY_TP int
#define ARY_TP_FMT "%i "

/*
TODO: Learn how to properly do this... if at all possible
typedef int my_array[10];
const size_t my_array_sz = sizeof(my_array);
*/

void set_ones(ARY_TP arr[ARY_SZ])
{
    int i;

    for (i = 0; i < ARY_SZ; i++) {
        arr[i] = 1;
    }
}

void set_sequence(ARY_TP arr[ARY_SZ])
{
    int i;

    for (i = 0; i < ARY_SZ; i++) {
        arr[i] = (ARY_TP)i;
    }
}

void multiply_all(ARY_TP arr[ARY_SZ], ARY_TP num)
{
    int i;

    for (i = 0; i < ARY_SZ; i++) {
        arr[i] *= num;
    }
}

void print_array(const ARY_TP arr[ARY_SZ])
{
    int i;

    for (i = 0; i < ARY_SZ; i++) {
        printf(ARY_TP_FMT, arr[i]);
    }
    printf("\n");
}

int main()
{
    ARY_TP arr[ARY_SZ]; /* = { 0 }; */
    /* print before initialization */
    print_array(arr);

    /* set all to 1 and print */
    set_ones(arr);
    print_array(arr);

    multiply_all(arr, 2);
    print_array(arr);

    /* set all to a sequence and print */
    set_sequence(arr);
    print_array(arr);

    multiply_all(arr, 10);
    print_array(arr);

    return 0;
}
