;=====================================================
;math.asm
;Descrição: Diversos exemplos de operações matemáticas
;Data: 03/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 math.asm
;
;Para gerar o executável (testado com o MingW):
;gcc -o math.exe math.obj asm_io.obj driver.c
;
;Dependências: asm_io.inc, asm_io.obj, driver.c
;====================================================================

%include "asm_io.inc"

segment .data
prompt		db	"Insira um numero: ", 0
square_msg	db	"Quadrado da entrada e' ", 0
cube_msg	db	"Cubo da entrada e' ", 0
cube25_msg	db	"Cubo da entrada vezes 25 e' ", 0
quot_msg	db	"Quociente de cubo/100 e' ", 0
rem_msg		db	"Resto de cubo/100 e' ", 0
neg_msg		db	"O negativo do resto e' ", 0

segment .bss
input		resd	1

segment .text
	global _asm_main
_asm_main:
	enter 0,0
	pusha
	
	mov	eax, prompt	; Exibe o prompr para o usuário
	call	print_string
	
	call	read_int	; Recebe um número inteiro
	mov	[input], eax
	
	imul	eax		; edx:eax = eax * eax
	mov	ebx, eax	; salva resultado em ebx
	mov	eax, square_msg
	call	print_string
	mov	eax, ebx
	call	print_int
	call	print_nl
	
	mov	ebx, eax	; contém o quadrado
	imul	ebx, [input]	; ebx *= [input] (cubo)
	mov	eax, cube_msg
	call	print_string
	mov	eax, ebx
	call	print_int
	call	print_nl
	
	imul	ecx, ebx, 25	; operando imediato (ecx = cubo * 25)
	mov	eax, cube25_msg
	call	print_string
	mov	eax, ecx
	call	print_int
	call	print_nl
	
	mov	eax, ebx	; eax = cubo
	cdq			; estende o sinal para DX
	mov	ecx, 100
	idiv	ecx		; (edx:eax)cubo / 100
	
	mov	ebx, eax	; salva quociente
	mov	eax, quot_msg
	call	print_string
	mov	eax, ebx	; restaura quociente
	call	print_int
	call	print_nl
	
	mov	eax, rem_msg
	call	print_string
	mov	eax, edx	; resto da divisão
	call	print_int
	call	print_nl
	
	neg	edx		; resto = -resto
	mov	eax, neg_msg
	call	print_string
	mov	eax, edx
	call	print_int
	call	print_nl
	
	
	popa
	mov	eax, 0
	leave
	ret
	
	