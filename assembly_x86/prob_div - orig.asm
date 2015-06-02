%include "asm_io.inc"

segment .data

segment .bss

segment .text
        global  _asm_main
_asm_main:
        enter   0,0
        pusha
	
	; Divide EAX / BX... ?
	mov	eax, 1234567890	; EAX = 499602D2h
	mov	bx, 12345	; EBX = ????3039h
	call	print_int	; output => 1234567890
	call	print_nl
	dump_regs 1
	; Convert EAX to DX:AX
	mov	edx, eax
	shr	edx, 16		; EDX = 00004996h
	movzx	eax, ax		; EAX = 000002D2h
	dump_regs 2
	; Now, divide DX:AX by BX
	div	bx
	dump_regs 3
	movzx	eax, ax		; show quotient (AX)
	call	print_int
	call	print_nl
	movzx	eax, dx		; show remainder (DX)
	call	print_int
	call	print_nl
	
        popa
        mov     eax, 0
        leave
        ret
