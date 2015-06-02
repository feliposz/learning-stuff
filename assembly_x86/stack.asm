;====================================================================
;stack.asm 
;Descrição: Exemplo demonstrando o funcionamento da pilha
;Fonte: Extraído do livro PC Assembly Language de Paul Carter
;Data: 11/04/2012
;====================================================================

%include "asm_io.inc"

segment .text
	global _asm_main
_asm_main:
	enter	0,0
	; salva os registradores EAX, EBX, ECX, EDX, ESI, EDI, EBP
	; na pilha para serem restaurados com popa
	pusha		
	
			; pilha = ?
	push 	dword 1	; pilha = 1 ?
	push	dword 2 ; pilha = 2 1 ?
	push	dword 3 ; pilha = 3 2 1 ?
	push	dword 4 ; pilha = 4 3 2 1 ?
	pop	eax	; eax = 4, pilha = 3 2 1 ?
	pop	ebx	; ebx = 3, pilha = 2 1 ?
	pop 	ecx	; ecx = 2, pilha = 1 ?
	pop	edx	; ecx = 1, pilha = ?
	dump_regs 1
	
	mov	ebx, 123
	mov	ecx, 456
	mov	edx, 789
	call	soma3numeros
	call	print_int
	call	print_nl
	
	;dump_regs 2
	
	; EAX = soma3numeros_c(123, 456, 789);
	push	dword 123	; primeiro parametro
	push	dword 456	; segundo parametro
	push	dword 789	; terceiro parametro
	call	_soma3numeros_c
	add	esp, 12		; remove os parametros da pilha
	call	print_int
	call	print_nl
	
	;dump_regs 3
	
	; restaura os registradores EAX, EBX, ECX, EDX, ESI, EDI, EBP
	; dos valores salvos na pilha por pusha
	popa		
	mov	eax, 0
	leave
	ret

; Subprograma: soma3numeros
; Descrição: Soma 3 números nos registradores
; Parâmetros:
;   ebx - primeiro número a adicionar
;   ecx - segundo número a adicionar
;   edx - terceiro número a adicionar
; Resultado:
;   eax - o resultado da soma
soma3numeros:
	mov	eax, ebx
	add	eax, ecx
	add	eax, edx
	ret

; Subprograma: _soma3numeros_c 
; Equivalente: int soma3numeros_c(int n1, int n2, int n3)
; Descrição: Soma 3 números da pilha, usando a convenção de chamada de C
; Parâmetros:
;   [EBP+16] - primeiro número a adicionar
;   [EBP+12] - segundo número a adicionar
;   [EBP+8] - terceiro número a adicionar
; Resultado:
;   eax - o resultado da soma

; Notas:
;   Usando esta convenção com EBP, a pilha conterá
;   [EBP] - O valor antigo de EBP que foi salvo
;   [EBP+4] - O endereço de retorno
;   [EBP+8] - Ultimo parametro
;   [EBP+12] - O penultimo paramtro...
;   [EBP+8+...] - Os demais parâmetros (múltiplos de 4, para dwords na pilha)

_soma3numeros_c:
	push	ebp		; Salvar EBP na pilha
	mov	ebp, esp	; EBP = ESP
				; Será usado para fazer referência aos parâmetros,
				; que estão na pilha, sem precisar usar pop.
	
	mov	eax, [EBP+16]	; EAX = n1
	add 	eax, [EBP+12]	; EAX += n2
	add	eax, [EBP+8]	; EAX += n3
	
	pop	ebp		; Restaura valor de EBP
	ret
