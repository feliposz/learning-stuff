;==============================================================================
;shift_arit.asm
;Descri��o: Opera��es aritm�ticas de multiplica��o e divis�o 2^n usando 
;opera��es de deslocamento aritm�tico: SAL, SAR.
;Tamb�m conta os bits em um n�mero. Al�m de demonstrar TEST e XOR para zerar
;Fonte: Extra�do do livro PC Assembly Language de Paul Carter
;Data: 05/04/2012
;==============================================================================

%include "asm_io.inc"

segment .data
msg_entrada	db	"Operacoes com deslocamento", 0
prompt_num	db	"Digite um numero inteiro: ", 0
msg_dobro	db	"O dobro deste numero e' ", 0
msg_metade	db	"A metade deste numero e' ", 0
msg_quadruplo	db	"O quadruplo deste numero e' ", 0
msg_quarto	db	"Um quarto deste numero e' ", 0
msg_ligados1	db	"No numero ", 0
msg_ligados2	db	", o numero de bits ligados e' ", 0
msg_par		db	"O numero e' par.", 0
msg_impar	db	"O numero e' impar.", 0

segment .bss
num		resd	1

segment .text
	global	_asm_main
_asm_main:
	enter	0,0
	pusha

	mov	eax, msg_entrada
	call	print_string
	call	print_nl
	call	print_nl

	mov	eax, prompt_num
	call	print_string
	call	read_int
	mov	[num], eax
	
	mov	ebx, [num]
	sal	ebx, 1
	mov	eax, msg_dobro
	call	print_string
	mov	eax, ebx
	call	print_int
	call	print_nl
	
	mov	ebx, [num]
	sar	ebx, 1
	mov	eax, msg_metade
	call	print_string
	mov	eax, ebx
	call	print_int
	call	print_nl	

	mov	ebx, [num]
	sal	ebx, 2
	mov	eax, msg_quadruplo
	call	print_string
	mov	eax, ebx
	call	print_int
	call	print_nl
	
	mov	ebx, [num]
	sar	ebx, 2
	mov	eax, msg_quarto
	call	print_string
	mov	eax, ebx
	call	print_int
	call	print_nl
	
	mov	eax, msg_ligados1
	call	print_string
	mov	eax, [num]
	call	print_int
	mov	eax, msg_ligados2
	call	print_string
	
	; conta quantos bits h� ligado em NUM
	mov	eax, [num]
	xor	bl, bl		; vai conter o n�mero de bits ligados (bl = 0)
	mov	ecx, 32		; 32 = n�mero de bits, ecx � o contador do la�o
laco_contador:
	shl	eax, 1		; desloca o primeiro bit, que ficar� em CF
	adc	bl, 0		; pequeno truque para incrementar baseado 
	loop	laco_contador	; no Carry Flag!

	movzx	eax, bl		; byte -> dword, converte o valor para impress�o
	call	print_int
	call	print_nl

	; se (num & 1)
	mov	eax, [num]
	test	eax, 1		; testa se o bit zero est� ligado
	jnz	impar
	mov 	eax, msg_par
	jmp	fim_se
impar:
	mov	eax, msg_impar
fim_se:
	call	print_string
	call	print_nl
	
	popa
	mov	eax, 0
	leave
	ret
