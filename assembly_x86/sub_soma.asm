;====================================================================
;sub_soma.asm
;Descri��o: Exemplo de aloca��o de vari�veis locais na pilha.
;Data: 11/04/2012
;====================================================================

%include "asm_io.inc"

segment .data
msg_num		db	"Digite um numero para a funcao soma: ", 0
msg_res		db	"A soma e' ", 0

segment .bss
resultado	resd	1

segment .text
	global	_asm_main
_asm_main:
	enter 0,0
	pusha

	
	mov	eax, msg_num
	call	print_string
	
	call	read_int
	
	push	eax		; n
	push	dword resultado	; &resultado
	call	calc_soma	; calc_soma(n, &resultado)
	add	esp, 8		; libera os 2 par�metros
	
	mov	eax, msg_res
	call	print_string
		
	mov	eax, [resultado]
	call	print_int
	call	print_nl

	
	popa
	mov	eax, 0
	leave
	ret

; subprograma: soma
; descri��o: Retorna a soma dos inteiros de 1 a 'n'.
; equivalente: void soma(int n, int* presultado)
; par�metros:
;   limite m�ximo para somar [ebp+12]
;   endere�o do resultado [ebp+8]
; notas:
;   destr�i valores de EAX, EBX e ECX

calc_soma:
	push	ebp
	mov	ebp, esp
	sub	esp, 4		; aloca um dword para vari�vel local (soma)
				
				; soma � [ebp-4]
				; soma = 0
	mov	dword [ebp-4], 0

	; para (i = 1; i <= n; i++)
	mov	ecx, 1		; i = 1
para_i:
	cmp	ecx, [ebp+12]	; i <= n
	jnle	fim_para_i
	add	[ebp-4], ecx	; soma += n
	inc	ecx		; i++
	jmp	para_i
fim_para_i:

	mov	ebx, [ebp+8]	; endere�o do resultado (presult)
	mov	eax, [ebp-4]	; eax = soma
	mov	[ebx], eax	; *presult = eax
	
	mov	esp, ebp	; libera vari�veis locais
	pop	ebp
	ret

	