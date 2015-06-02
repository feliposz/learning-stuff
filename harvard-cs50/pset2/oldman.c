#include <cs50.h>
#include <stdio.h>

int main(void)
{

	string s1[10] = {
		"one", "two", "three", "four", "five",
		"six", "seven", "eight", "nine", "ten",
	};

	string s2[10] = {
		"thumb", "shoe", "knee", "door", "hive",
		"sticks", "heaven", "gate", "spine", "again",
	};

	for (int i = 0; i < 10; i++) {
		printf("This old man, he played %s\n", s1[i]);
		printf("He played knick-knack on my %s\n", s2[i]);
		printf("Knick-knack paddywhack, give your dog a bone\n");
		printf("This old man came rolling home\n\n");
	}

}
