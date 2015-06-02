;====================================================================
;first.asm 
;Descri��o: Programa de exemplo em assembly que l� dois n�meros e soma
;Fonte: Extra�do do livro PC Assembly Language de Paul Carter
;Data: 02/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 first.asm
;
;Para gerar o execut�vel (testado com o MingW):
;gcc -o first.exe first.obj asm_io.obj driver.c
;
;Depend�ncias: asm_io.inc, asm_io.obj, driver.c
;
;Funcionamento:
;A rotina asm_main ser� chamada a partir do programa C.
;Isto � feito para facilitar a inicializa��o e disponibilizar toda
;a biblioteca padr�o de C para ser usada a partir do c�digo assembly
;====================================================================

;Inclui as rotinas b�sicas de E/S
%include "asm_io.inc"

;Segmento de dados inicializados (.data)
segment .data

;OBS: Todas as strings seguem o padr�o C, i.e., terminam com 0 ('\0')
prompt1	db	"Insira um numero: ", 0
prompt2	db	"Insira outro numero: ", 0
outmsg1 db	"Voce digitou ", 0
outmsg2 db	" e ", 0
outmsg3 db	", a soma destes valores e' ", 0

;Segmento de dados n�o-inicializados (.bss)
segment .bss

;Estes r�tulos armazenar�o dados inseridos pelo usu�rio
input1	resd	1
input2	resd	1

;Segmento de c�digo (.text)
;O nome .text � por raz�es hist�ricas
segment .text
	;Torna este r�tulo vis�vel para o c�digo em C
	;Por padr�o, os labels s�o internos de cada m�dulo!!!
	global _asm_main

;O underscore (_) inicial faz parte da conven��o de chamadas
;usada em linguagem C em ambiente DOS/WINDOWS na convers�o
;dos nomes das rotinas durante a compila��o.	
_asm_main:
	;prepara a rotina
	enter	0,0
	pusha
	
	;Imprime o primeiro prompt (eax = cont�m o endere�o da string)
	mov	eax, prompt1
	call	print_string
	
	;L� o primeiro valor (retornado em eax)
	;e armazenda no endere�o input1
	call	read_int
	mov	[input1], eax
	
	;Imprime o segundo prompt
	mov	eax, prompt2
	call	print_string
	
	;L� o segundo valor
	call	read_int
	mov	[input2], eax
	
	;Executa a soma e salva o resultado em EBX para usar depois
	mov	eax, [input1]	;eax = dword em input1
	add	eax, [input2]	;eax += dword em input2
	mov	ebx, eax	;ebx = eax
	
	;Macros de depura��o definida em asm_io.inc
	dump_regs 1		;Exibe os registrados com o r�tulo #1
	dump_mem 2, outmsg1, 1	;Exibe o conte�do da mem�ria com o r�tulo #2
				;1 * 16 bytes (1 par�grafo)
				;A partir do endere�o outmsg1
	
	;Passos para imprimir o resultado
	mov	eax, outmsg1	;"Voce digitou "
	call	print_string
	mov	eax, [input1]	;imprime o valor de input1
	call	print_int
	mov	eax, outmsg2	;" e "
	call	print_string
	mov	eax, [input2]	;imprime o valor de input1
	call	print_int
	mov	eax, outmsg3	;", a soma destes valores e' "
	call	print_string
	mov	eax, ebx	;imprime o resultado salvo em EBX
	call	print_int
	call	print_nl
	
	;Retorna para a rotina que chamou (main em driver.c)
	popa
	mov	eax, 0
	leave
	ret