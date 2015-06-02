/*
driver.c
Descrição: Este programa executa uma chamada a uma rotina em assembly
Fonte: Extraído do livro PC Assembly Language de Paul Carter
Data: 02/04/2012

Este programa pode ser compilado com (testado com MingW)
gcc -c driver.c

Para gerar um executável porém, é necessário um arquivo assembly
que implemente a rotina _asm_main!!! Tal arquivo pode ser montado com:
nasm -f win32 NOME.asm

Então é possível gerar um executável com:
gcc -o NOME.exe NOME.obj asm_io.obj driver.c

Funcionamento:
Chama a rotina/funlão _asm_main que é implementada em outro arquivo.
Isto é feito para facilitar a inicialização e disponibilizar toda
a biblioteca padrão de C para ser usada a partir do código assembly.
*/

/* A rotina _asm_main é implementada em Assembly em outro arquivo! */
extern int asm_main( void );

int main() 
{
	int ret_status;

	ret_status = asm_main();

	return ret_status;
}
