#include <cs50.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[])
{
	printf("Number: ");
	long long ccNumber = GetLongLong();

	int sumOfEven = 0;
	int sumOfOdd = 0;
	int digitCount = 0;
	int digitsRequired = 16;
	bool evenDigit = false;

	string result = "INVALID";

	do {
		int lastDigit = ccNumber % 10;
		ccNumber = ccNumber / 10;
		digitCount++;
		if (ccNumber == 34 || ccNumber == 37) {
			digitsRequired = 15;
			result = "AMEX";
		}
		if (ccNumber >= 51 && ccNumber <= 55) {
			digitsRequired = 16;
			result = "MASTER";
		}
		if (ccNumber == 4) {
			result = "VISA";
		}
		if (evenDigit) {
			int tmp = lastDigit * 2;
			if (tmp < 10)
				sumOfEven += tmp;
			else
				sumOfEven += tmp/10 + tmp%10;
		} else {
			sumOfOdd += lastDigit;
		}
		evenDigit = !evenDigit;
	} while (ccNumber > 0);

	if (!strcmp(result, "VISA")) {
		if (digitCount != 13 && digitCount != 16) {
			result = "INVALID";
		}
	} else {
		if (digitCount != digitsRequired) {
			result = "INVALID";
		}
	}

	int checkSum = (sumOfEven + sumOfOdd) % 10;
	if (checkSum != 0)
		result = "INVALID";

	printf("%s\n", result);
}
