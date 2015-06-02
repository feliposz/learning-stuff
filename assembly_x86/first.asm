;====================================================================
;first.asm 
;Descrição: Programa de exemplo em assembly que lê dois números e soma
;Fonte: Extraído do livro PC Assembly Language de Paul Carter
;Data: 02/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 first.asm
;
;Para gerar o executável (testado com o MingW):
;gcc -o first.exe first.obj asm_io.obj driver.c
;
;Dependências: asm_io.inc, asm_io.obj, driver.c
;
;Funcionamento:
;A rotina asm_main será chamada a partir do programa C.
;Isto é feito para facilitar a inicialização e disponibilizar toda
;a biblioteca padrão de C para ser usada a partir do código assembly
;====================================================================

;Inclui as rotinas básicas de E/S
%include "asm_io.inc"

;Segmento de dados inicializados (.data)
segment .data

;OBS: Todas as strings seguem o padrão C, i.e., terminam com 0 ('\0')
prompt1	db	"Insira um numero: ", 0
prompt2	db	"Insira outro numero: ", 0
outmsg1 db	"Voce digitou ", 0
outmsg2 db	" e ", 0
outmsg3 db	", a soma destes valores e' ", 0

;Segmento de dados não-inicializados (.bss)
segment .bss

;Estes rótulos armazenarão dados inseridos pelo usuário
input1	resd	1
input2	resd	1

;Segmento de código (.text)
;O nome .text é por razões históricas
segment .text
	;Torna este rótulo visível para o código em C
	;Por padrão, os labels são internos de cada módulo!!!
	global _asm_main

;O underscore (_) inicial faz parte da convenção de chamadas
;usada em linguagem C em ambiente DOS/WINDOWS na conversão
;dos nomes das rotinas durante a compilação.	
_asm_main:
	;prepara a rotina
	enter	0,0
	pusha
	
	;Imprime o primeiro prompt (eax = contém o endereço da string)
	mov	eax, prompt1
	call	print_string
	
	;Lê o primeiro valor (retornado em eax)
	;e armazenda no endereço input1
	call	read_int
	mov	[input1], eax
	
	;Imprime o segundo prompt
	mov	eax, prompt2
	call	print_string
	
	;Lê o segundo valor
	call	read_int
	mov	[input2], eax
	
	;Executa a soma e salva o resultado em EBX para usar depois
	mov	eax, [input1]	;eax = dword em input1
	add	eax, [input2]	;eax += dword em input2
	mov	ebx, eax	;ebx = eax
	
	;Macros de depuração definida em asm_io.inc
	dump_regs 1		;Exibe os registrados com o rótulo #1
	dump_mem 2, outmsg1, 1	;Exibe o conteúdo da memória com o rótulo #2
				;1 * 16 bytes (1 parágrafo)
				;A partir do endereço outmsg1
	
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