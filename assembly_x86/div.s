	.file	"div.c"
	.intel_syntax noprefix
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
.globl _main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
	push	ebp
	mov	ebp, esp
	and	esp, -16
	sub	esp, 16
	call	___main
	mov	WORD PTR [esp+10], -1
	mov	WORD PTR [esp+12], 12345
	mov	ax, WORD PTR [esp+10]
	mov	edx, 0
	div	WORD PTR [esp+12]
	mov	WORD PTR [esp+14], ax
	mov	ax, WORD PTR [esp+10]
	mov	edx, 0
	div	WORD PTR [esp+12]
	mov	WORD PTR [esp+14], dx
	movzx	eax, WORD PTR [esp+14]
	leave
	ret
