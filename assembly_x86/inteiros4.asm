;=====================================================
;inteiros4.asm
;Descrição: Soma e subtração de inteiros grandes
;Data: 03/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 math.asm
;
;Para gerar o executável (testado com o MingW):
;gcc -o inteiros4.exe inteiros4.obj asm_io.obj driver.c
;
;Dependências: asm_io.inc, asm_io.obj, driver.c
;=====================================================

%include "asm_io.inc"

segment .data
num1	db	0FFh,0EEh,0DDh,0CCh,0BBh,0AAh,99h,88h,77h,66h,55h,44h,33h,22h,11h,00h
num2	db	00h,11h,22h,33h,44h,55h,66h,77h,88h,99h,0AAh,0BBh,0CCh,0DDh,0EEh,0FFh

;	times 	4 db  0	;this is just so that numbers appear nice in memory dump
;num1	db	0FEh, 0DCh, 0BAh, 98h, 76h, 54h, 32h, 10h
;	times 	24 db  0
;num2	db	01h, 23h, 45h, 67h, 89h, 0ABh, 0CDh, 0EFh
;	times 	32 db  0

segment .bss

segment .text
	global _asm_main
_asm_main:
	enter	0,0
	pusha
	
	; Soma de dois números de 64-bits
	mov	edx, 0FEDCBA98h		;EDX:EAX = FEDC BA98 7654 3210
	mov	eax,  76543210h
	mov	ebx, 01234567h		;EBX:ECX = 0123 4567 89AB CDEF
	mov	ecx, 89ABCDEFh
	dump_regs 1
	add	eax, ecx		;EDX:EAX += EBX:ECX
	adc	edx, ebx
	dump_regs 2
	
	; Subtração de dois números de 64-bits
	
	; Subtrai o mesmo número somado, deve produzir o resultado original!
	sub	eax, ecx		;EDX:EAX -= EBX:ECX
	sbb	edx, ebx
	dump_regs 3
	
	dump_mem 1, num1, 1
	dump_mem 2, num2, 1
	
	; Adiciona dois números de 128-bits na memória 1 byte de cada vez
	mov	ecx, 16
	clc				;Limpa o carry para que o primeiro ADC seja igual a ADD no loop
	mov	edi, num1
	mov	esi, num2
add_nums:
	mov	al, [esi]
	adc	[edi], al
	inc	edi
	inc	esi
	loop	add_nums

	dump_mem 3, num1, 1
	dump_mem 4, num2, 1
	; resultado deve ser FFFFFFFF...
	
	; Subtrai dois números de 128-bits na memória (subtrai os mesmos números para veriricar)
	mov	ecx, 16
	clc				;Limpa o carry para que o primeiro SBB seja igual a SUB no loop
	mov	edi, num1
	mov	esi, num2
sub_nums:
	mov	al, [esi]
	sbb	[edi], al
	inc	edi
	inc	esi
	loop	sub_nums	

	dump_mem 5, num1, 1
	dump_mem 6, num2, 1
	
	popa
	mov	eax, 0
	leave
	ret
	
	