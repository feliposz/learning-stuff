#include <stdio.h>
#include <locale.h>
#include <time.h>


void test1(void)
{
	setlocale(LC_ALL, "Portuguese"); // permite o uso de acentuação e vírgula como separador decimal

	printf("Ahá! Agora meus programas podem ter todo tipo de acentuação.\n");
    printf("E os números tem vírgula (,) como separador decimal: %lf\n", 3.1415926);

	double teste;
	printf("Digite um número real: ");
	scanf("%lf", &teste);
	printf("Número digitado: %lf\n", teste);

	time_t timer = time(NULL);
	printf("A data e hora é: %s\n", asctime(localtime(&timer)));
}

void test2(void)
{
	time_t ltime;
	struct tm *thetime;
	unsigned char str[100];
	char* locale_list[] = { "German", "Portuguese", "French", "Spanish", "C" };
	int i;

	for (i = 0; i < (sizeof(locale_list) / sizeof(locale_list[0])); i++) {

		setlocale(LC_ALL, locale_list[i]);

		printf("Locale: %s\n", locale_list[i]);

		time (&ltime);
		thetime = gmtime(&ltime);
		if (!strftime((char *)str, 100, "%#x", (const struct tm *)thetime))
			printf("strftime failed!\n");
		else
			printf("strftime returns '%s'\n", str);

		printf("double = '%lf'\n", 3.1415926);

	}

}

void test3(void)
{
	struct lconv locale_structure;
	struct lconv *locale_ptr=&locale_structure;
	
	setlocale(LC_ALL, "Portuguese");
	
	locale_ptr=localeconv();
	printf("Decimal point:                   %s\n",locale_ptr->decimal_point);
	printf("Thousands Separator:             %s\n",locale_ptr->thousands_sep);
	printf("Grouping:                        %s\n",locale_ptr->grouping);
	printf("International Currency Symbol:   %s\n",locale_ptr->int_curr_symbol);
	printf("Currency Symbol:                 %s\n",locale_ptr->currency_symbol);
	printf("Monetary Decimal Point:          %s\n",locale_ptr->mon_decimal_point);
	printf("Monetary Thousands Separator:    %s\n",locale_ptr->mon_thousands_sep);
	printf("Monetary Grouping:               %s\n",locale_ptr->mon_grouping);
	printf("Monetary Positive Sign:          %s\n",locale_ptr->positive_sign);
	printf("Monetary Negative Sign:          %s\n",locale_ptr->negative_sign);
	printf("Monetary Intl Decimal Digits:    %c\n",locale_ptr->int_frac_digits);
	printf("Monetary Decimal Digits:         %c\n",locale_ptr->frac_digits);
	printf("Monetary + Precedes:             %c\n",locale_ptr->p_cs_precedes);
	printf("Monetary + Space:                %c\n",locale_ptr->p_sep_by_space);
	printf("Monetary - Precedes:             %c\n",locale_ptr->n_cs_precedes);
	printf("Monetary - Space:                %c\n",locale_ptr->n_sep_by_space);
	printf("Monetary + Sign Posn:            %c\n",locale_ptr->p_sign_posn);
	printf("Monetary - Sign Posn:            %c\n",locale_ptr->n_sign_posn);
}

int main(void)
{
	system("cls");
	test1();
	system("pause");
	return 0;
}
