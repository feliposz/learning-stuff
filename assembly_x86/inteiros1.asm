;====================================================================
;inteiros1.asm 
;Descrição: Operações básicas com inteiros
;Data: 03/04/2012
;
;Este programa pode ser montado com:
;nasm -f win32 inteiros1.asm
;
;Para gerar o executável (testado com o MingW):
;gcc -o inteiros1.exe inteiros1.obj asm_io.obj driver.c
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
	;---------INICIO---------
	
	; ========= Exemplos de Complemento de 2 =========
	
	; 0 - 56 = -56
	mov	ebx, 56
	mov	eax, 0	
	sub	eax, ebx
	call	print_int
	call	print_nl
	dump_regs 1
	
	; 56 + (-56) = 0
	mov	ebx, 56
	mov	eax, -56
	add	eax, ebx
	call	print_int
	call	print_nl
	dump_regs 2

	; ========= Faixa de valores =========
	
	; byte mais alto +127 (signed char)
	mov	eax, 0
	mov	al, 7Fh
	call	print_int
	call	print_nl
	dump_regs 3

	; byte mais baixo -128 (signed char)
	mov	eax, 0FFFFFFFFh
	mov	al, 80h
	call	print_int
	call	print_nl
	dump_regs 4	
	
	; word mais alto +32,767 (signed short int)
	mov	eax, 0
	mov	ax, 7FFFh
	call	print_int
	call	print_nl
	dump_regs 5

	; word mais baixo -32,768 (signed short int)
	mov	eax, 0FFFFFFFFh
	mov	ax, 8000h
	call	print_int
	call	print_nl
	dump_regs 6

	; double word mais alto +2.147.483.647 (signed long int)
	mov	eax, 7FFFFFFFh
	call	print_int
	call	print_nl
	dump_regs 7

	; double word mais baixo -2.147.483.648 (signed long int)
	mov	eax, 80000000h
	call	print_int
	call	print_nl
	dump_regs 8
	
	; double word mais baixo -2.147.483.648 (signed long int)
	mov	eax, 0FFFFFFFFh
	call	print_int
	call	print_nl
	dump_regs 9
	
	; ========= Conversão de tipos de inteiro (c/ e sem sinal) =========
	
	; reduzindo tamanho de 32 para 16 bits (número positivo)
	mov	ecx, 00000034h	; ecx = 52
	mov	eax, 0
	mov	ax, cx		; transfere os 16-bits mais baixos de ecx
	call	print_int
	call	print_nl
	dump_regs 10

	; reduzindo tamanho de 32/16 para 8 bits (número positivo)
	mov	eax, 0
	mov	al, cl		; transfere os 8-bits mais baixos de ecx/cx
	call	print_int
	call	print_nl
	dump_regs 11
	
	; reduzindo tamanho de 32 para 16 bits (número negativo)
	mov	ecx, 0FFFFFFCCh	; ecx = -52
	mov	eax, 0FFFFFFFFh ; bits mais altos devem ser todos 1 !!!!!		
	mov	ax, cx		; transfere os 16-bits mais baixos de ecx
	call	print_int
	call	print_nl
	dump_regs 12

	; reduzindo tamanho de 32/16 para 8 bits (número negativo)
	mov	eax, 0FFFFFFFFh ; bits mais altos devem ser todos 1 !!!!!
	mov	al, cl		; transfere os 8-bits mais baixos de ecx/cx
	call	print_int
	call	print_nl
	dump_regs 13
	
	; aumentando tamanho (extensão de sinal para números unsigned), 8 para 32 bits (unsigned char -> unsigned long)
	mov	eax, 087654321h	; preenche bits com "lixo" para mostrar efeito indesejado
	mov	al, 0FFh	; byte = 255 (unsigned)
	call	print_int	; será impresso como -2023406593
	call	print_nl
	dump_regs 14
	movzx	eax, al		; byte -> double word zerando os bits superiores
	call	print_int	; será impresso corretamente como 255
	call	print_nl
	dump_regs 15

	; aumentando tamanho (extensão de sinal para números unsigned), 16 para 32 bits (unsigned short -> unsigned long)
	mov	eax, 087654321h	; preenche bits com "lixo" para mostrar efeito indesejado
	mov	ax, 0FFFFh	; word = 65535 (unsigned)
	call	print_int	; será impresso como -2023358465
	call	print_nl
	dump_regs 16
	movzx	eax, ax		; word -> double word zerando os bits superiores
	call	print_int	; será impresso corretamente como 65535
	call	print_nl
	dump_regs 17
	
	; aumentando tamanho (extensão de sinal para números signed), 8 para 32 bits (signed char -> signed long)
	mov	eax, 087654321h	; preenche bits com "lixo" para mostrar efeito indesejado
	mov	al, 0FFh	; byte = -1 (signed)
	call	print_int	; será impresso como -2023406593
	call	print_nl
	dump_regs 18
	movsx	eax, al		; byte -> double word zerando os bits superiores
	call	print_int	; será impresso corretamente como -1
	call	print_nl
	dump_regs 19

	; aumentando tamanho (extensão de sinal para números signed), 16 para 32 bits (signed short -> signed long)
	mov	eax, 087654321h	; preenche bits com "lixo" para mostrar efeito indesejado
	mov	ax, 0FFFFh	; word = -1 (signed)
	call	print_int	; será impresso como -2023358465
	call	print_nl
	dump_regs 20
	movsx	eax, ax		; word -> double word zerando os bits superiores
	call	print_int	; será impresso corretamente como -1
	call	print_nl
	dump_regs 21	

	;---------FIM---------
        popa
        mov     eax, 0            ;Retorna 0 para a rotina que chamou 
        leave                     ;-> main() em driver.c
        ret

	;TODO: Coloque aqui as demais rotinas
