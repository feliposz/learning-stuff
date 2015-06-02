;====================================================================
;inteiros3.asm 
;Descrição: Multiplicação e divisão com sinal!
;Data: 03/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 inteiros2.asm
;
;Para gerar o executável (testado com o MingW):
;gcc -o inteiros2.exe inteiros2.obj asm_io.obj driver.c
;
;Dependências: asm_io.inc, asm_io.obj, driver.c
;====================================================================

%include "asm_io.inc"

segment .data

segment .bss
DIVISOR	resw	1

segment .text
        global  _asm_main
_asm_main:
        enter   0,0
        pusha
	;---------INICIO---------
	
	; byte * byte = word (signed)
	mov	al, -2
	mov	bl, 4
	imul	bl		; ax = al * bl = -8
	movsx	eax, ax		; word -> double word
	call	print_int
	call	print_nl
	dump_regs 1
	
	; word * word = double word (signed)
	mov	ax, -1234
	mov	bx, -4321
	dump_regs 2
	imul	bx		; dx:ax = ax * bx = 5.332.114
	dump_regs 3
	movzx   eax, ax		; zera os 16 bits maiores de EAX
	shl	edx, 16		; combina o resultado dx:ax em eax
	or	eax, edx
	call	print_int
	call	print_nl
	dump_regs 4
	
	; word * word = word (signed)
	mov	eax, 0 		; zera tudo para verificar resultado
	mov	bx, 1234
	mov	cx, 4321
	mov	ax, bx
	imul	ax, cx		; ax = bx * cx = 23698 ??? (ocorre overflow, resultado não cabem em 16-bits)
	call	print_int
	call	print_nl
	dump_regs 5

	; dword * dword = dword (signed)
	mov	ebx, 1234
	mov	ecx, 4321
	mov	eax, ebx
	imul	eax, ecx	; eax = ebx * ecx = 5332114 (resultado coube em 32-bits)
	call	print_int
	call	print_nl
	dump_regs 6
	
	; double word * double dword = quad word (signed)
	mov	eax, 1234567890
	mov	ebx, 1234567890
	imul	ebx		; resultado em edx:eax = (1526 E583 121F F444) = 1.524.157.875.019.052.100
	dump_regs 7
	mov	ebx, 1000000000	; pequeno truque para imprimir o número completo
	idiv	ebx		
	call	print_int	; imprime 1524157875
	call	print_nl
	mov	eax, edx
	call	print_int	; imprime (0)19052100
	call	print_nl

	; double word * double dword = quad word (signed)
	mov	eax, 1234567890
	mov	ebx, -1234567890
	imul	ebx		; resultado em edx:eax = (EAD9 1A7C EDE0 0BBC) = -1.524.157.875.019.052.100
	dump_regs 7
	mov	ebx, 1000000000	; pequeno truque para imprimir o número completo
	idiv	ebx
	call	print_int	; imprime -1524157875
	call	print_nl
	mov	eax, edx
	neg	eax		; eax = eax * -1
	call	print_int	; imprime -19052100
	call	print_nl	
	
		
	;---------FIM---------
        popa
        mov     eax, 0            ;Retorna 0 para a rotina que chamou 
        leave                     ;-> main() em driver.c
        ret
