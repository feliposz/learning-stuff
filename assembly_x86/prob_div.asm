;====================================================================
;prob_div.asm 
;Descrição: Este exemplo mostrar uma situação em que ocorre um erro
; de divisão, pois o resultado da divisão não cabe em 16-bits!!!
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

segment .text
        global  _asm_main
_asm_main:
        enter   0,0
        pusha
	
	; Dividindo um double word em eax (32-bit) por um word em bx (16-bit)
	mov	eax, 1234567890
	mov	bx, 12345
	dump_regs 12
	; Repare que os 16-bits mais altos de eax devem na verdade ser transferidos para DX e zerados
	mov	edx, eax
	shr	edx, 16
	movzx	eax, ax
	dump_regs 13
	; Tenta a divisão. Esta linha dá erro, pois o resultado é 100005, um valor que não cabe em 16-bits (max 65535)
	div	bx
	dump_regs 14
	movzx	eax, ax		; mostrar o quociente em AX
	call	print_int
	call	print_nl
	movzx	eax, dx		; mostrar o resto em DX
	call	print_int
	call	print_nl
	
        popa
        mov     eax, 0
        leave
        ret
