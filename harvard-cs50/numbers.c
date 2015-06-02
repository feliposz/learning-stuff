#include <stdio.h>

int
myfloor(float x)
{
	return (int)x;
}

int
myceil(float x)
{
	int tmp = myfloor(x + 1);
	if (tmp == x + 1)
		return (int)x;
	else
		return tmp;
}

double
steal(double interest)
{
	double rounded = (int)(interest * 100) / 100.0;
	return interest - rounded;
}


int main(void)
{
	float array[5] = { 49, 49.001, 49.5, 49.9, 50.0 };

	for (int i = 0; i < 5; i++) {
		printf("%6.3f ceil=%2d floor=%2d\n",
			array[i], myceil(array[i]), myfloor(array[i]));
	}

	float value = 1.23456789;
	printf("value=%.8f steal=%.8f\n", value, steal(value));
}
