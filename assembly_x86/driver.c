/*
driver.c
Descri��o: Este programa executa uma chamada a uma rotina em assembly
Fonte: Extra�do do livro PC Assembly Language de Paul Carter
Data: 02/04/2012

Este programa pode ser compilado com (testado com MingW)
gcc -c driver.c

Para gerar um execut�vel por�m, � necess�rio um arquivo assembly
que implemente a rotina _asm_main!!! Tal arquivo pode ser montado com:
nasm -f win32 NOME.asm

Ent�o � poss�vel gerar um execut�vel com:
gcc -o NOME.exe NOME.obj asm_io.obj driver.c

Funcionamento:
Chama a rotina/funl�o _asm_main que � implementada em outro arquivo.
Isto � feito para facilitar a inicializa��o e disponibilizar toda
a biblioteca padr�o de C para ser usada a partir do c�digo assembly.
*/

/* A rotina _asm_main � implementada em Assembly em outro arquivo! */
extern int asm_main( void );

int main() 
{
	int ret_status;

	ret_status = asm_main();

	return ret_status;
}
