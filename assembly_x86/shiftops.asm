;==============================================================================
;shiftops.asm
;Descrição: Pequeno programa para demonstrar o uso dos operadores de
;deslocamento SHL, SHR, além do operador OR, AND, XOR e NOT.
;Fonte: Extraído do livro PC Assembly Language de Paul Carter
;Data: 05/04/2012
;==============================================================================

%include "asm_io.inc"

segment .text
	global	_asm_main
_asm_main:
	enter	0,0
	pusha
	
	; Algumas operações imples de deslocamento de bits
	mov	ax, 0C123h	;1100 0001 0010 0011 = C123
	dump_regs 1
	shl	ax, 1		;1000 0010 0100 0110 = 8246	CF = 1
	dump_regs 2
	shr	ax, 1		;0100 0001 0010 0011 = 4123	CF = 0
	dump_regs 3
	shr	ax, 1		;0010 0000 1001 0001 = 2091	CF = 1
	dump_regs 4
	mov	eax, 0C123h	;1100 0001 0010 0011 = C123
	dump_regs 5
	shl	ax, 2		;0000 0100 1000 1100 = 048C	CF = 1
	dump_regs 6
	mov	cl, 3		
	dump_regs 7
	shr	ax, cl		;0000 0000 1001 0001 = 0091	CF = 1
	dump_regs 8
	
	call print_nl
	
	; Uma pequena brincadeira aqui com os digitos hexadecimais
	; Lembrando que cada 4-bits equivalem a um digito hexadecimal
	mov	eax, 01234567h
	mov	ebx, 0FEDCBA98h
	dump_regs 9
	shl	eax, 4
	shr	ebx, 4
	or	eax, 00000008h
	dump_regs 10
	shl	eax, 8
	shr	ebx, 8
	or	eax, 0000009Ah
	or	ebx, 21000000h
	dump_regs 11
	shl	eax, 20
	shr	ebx, 20
	or	eax, 000BCDEFh
	or	ebx, 76543000h
	dump_regs 12
	
	call print_nl
	
	; Rotação de bits
	mov	eax, 01234567h
	dump_regs 13
	mov	ebx, 0FEDCBA98h
	rol	eax, 4
	ror	ebx, 4
	dump_regs 14
	rol	eax, 8
	ror	ebx, 8
	dump_regs 15
	rol	eax, 20
	ror	ebx, 20
	dump_regs 16
	
	call print_nl
	
	and	eax, 0000FFFFh		; desliga os 16 bits mais altos
	or	ebx, 0FFFF0000h		; liga os 16 bits mais altos
	dump_regs 17
	not	eax			; inverte os bits de EAX
	xor	ebx, 0F0F0F0F0h		; mescla os bits de EBX (criptografa)
	mov	ecx, ebx		; transfere para ECX
	xor	ecx, 0F0F0F0F0h		; mescla novamente (decriptografa)
	dump_regs 18
	
	popa
	mov	eax, 0
	leave
	ret
