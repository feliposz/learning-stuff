;====================================================================
;inteiros2.asm 
;Descrição: Operações aritméticas com inteiros s/ sinal
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
	
	; ======== Somando inteiros c/ sinal ========
	
	; 44 + (-1) = 43
	mov	eax, 44		; 0000002C
	mov	ebx, -1		; FFFFFFFF
	add	eax, ebx	; Carry é gerado!
	call	print_int
	call	print_nl
	dump_regs 1

	; ======== Subtraindo inteiros c/ sinal ========
	
	; -5 - (-3) = -2
	mov	eax, -5		;   FFFFFFFB  (-5)
	mov	ebx, -3		; - FFFFFFFD -(-3)
	sub	eax, ebx	; = FFFFFFFE =(-2)
	call	print_int
	call	print_nl
	dump_regs 2
	
	; OBS: A lógica das operações de adição e subtração c/ e s/ sinal é a mesma.
	;      A interpretação do resultado depende do programador!

	; ======== Multiplicando inteiros UNSIGNED ========
	
	; 15 * 23 = 345   (operandos 8-bits * 8-bits = resultado 16-bits !!!!)
	mov	eax, 0		; zera bits superiores
	mov	bl, 15		; bl = 15
	mov	al, 23		; al = 23
	mul	bl		; ax = al * bl
	call	print_int
	call	print_nl
	dump_regs 3
	
	; 12345 * 54321 = 670592745   (operandos 16-bits * 16-bits = resultado 32-bits !!!!)
	mov	eax, 0		; zera bits superiores
	mov	edx, 0		; zera bits superiores
	mov	bx, 12345	; bx = 12345
	mov	ax, 54321	; ax = 54321
	mul	bx		; dx:ax = ax * bx
	call	print_int	; 28393 ?!?!?  sim, pois o resultado correto está em DX e em AX
	call	print_nl
	dump_regs 4
	
	; para obter o resultado correto, é necessário converter o resultado separado DX:AX para EAX
	shl	edx, 16		; desliza os 16-bits mais baixos para os 16-bits mais altos de edx
	or	eax, edx	; "concatena" os 16-bits mais altos de edx com os 16-bits mais baixos de ax
	call	print_int	; 670592745 = Agora sim!
	call	print_nl
	dump_regs 5
	
	; 40000 * 50000 = 2 bilhões   (operandos 32-bits * 32-bits = resultado 64-bits !!!!)
	mov	eax, 40000
	mov	ebx, 50000
	mul	ebx		; edx:eax = eax * ebx
	call	print_int	; 2 bilhões (resultado ainda cabe nos 32 bits)
	call	print_nl
	dump_regs 6

	; 4.294.967.295 * 2 = 8.589.934.590	(operandos 32-bits * 32-bits = resultado 64-bits !!!!)
	mov	eax, 0FFFFFFFFh	; 4.294.967.295
	mov	ebx, 2		; 8.589.934.590
	mul	ebx		; edx:eax = eax * ebx
	call	print_int	; ??? (resultado NÃO cabe nos 32 bits) EDX:EAX
	call	print_nl
	dump_regs 7
	; Resultado é 0000 0001 FFFF FFFE
	mov	ecx, eax	; salva o valor de EAX, para manter o valor do par EDX:EAX
	mov	eax, edx	; ^ isto é feito para poder imprimir o valor, pois o parâmetro para print_int fica em EAX
	call	print_int	; mostra o "bit" armazanado em EDX
	call	print_nl
	dump_regs 8
	mov	eax, ecx	; restaura o valor de EAX, i.e. o valor anterior de EDX:EAX
	
	; ======== Dividindo inteiros UNSIGNED ========
	
	; Este exemplo abaixo mostra a divisão de edx:eax (64-bit quad word) por ebx (32-bit double word)
	; Estamos aproveitando o resultado anterior (8.589.934.590)
	
	; vamos dividir este resultado em edx:eax por 1000
	mov	ebx, 1000	; ebx = 1000
	div	ebx		; edx:eax / ebx => quociente em eax, resto em edx
	call	print_int	; = 8.589.934
	call	print_nl
	dump_regs 9

	; mostra o resto da divisão
	mov	eax, edx
	call	print_int	; = 590
	call	print_nl
	dump_regs 10
	
	; Agora um "truque" para exibir o resultado de uma multiplicação grande em dois passos
	; BUG: Na verdade, não imprime o resultado correto em alguns casos, devido a um zero no meio do caminho =/
	; Exemplo: 123456789 * 123456789 = 15241578750190521
	mov	eax, 123456789
	mov	ebx, 123456789
	mul	ebx		;edx:eax conterá um resultado grande!
	mov	ebx, 1000000000 ;divide por 1 bilhão
	div	ebx
	call	print_int	; eax (quociente) =  15241578 (15241578750190521 / 1000000000)
	mov	eax, edx	; edx (resto)     = 750190521 (15241578750190521 % 1000000000)
	call	print_int
	call	print_nl
	
	; Dividindo um word em ax (16-bit unsigned short) por um byte em bl (8-bit unsigned char)
	; Ocorrerá erro se tentar dividir por zero!
	mov	ax, 315
	mov	bl, 17
	div	bl		; ax (16-bits) / bl (8-bits) = quociente em al (8-bits), resto em ah (8-bits)
	dump_regs 11		; EAX = ????0912 (AH = 09h = 9   e   AL = 12h = 18)
	mov	cl, ah		; salva o resto (ah será sobreposto por movzx)
	movzx	eax, al		; al byte -> eax double word
	call	print_int	; exibe o quociente = 18
	call	print_nl
	movzx	eax, cl		; cl byte -> eax double word
	call	print_int	; exibe o resto = 9
	call	print_nl
	
	; Dividindo um double word em eax (32-bit) por um word em bx (16-bit)
	; ATENÇÃO: se o resultado da divisão não couber em 16-bits ocorrerá um erro de operação aritmética
	mov	eax, 2500000000	; EAX = 9502F900h
	mov	bx, 49999	; EBX = ????C34Fh
	dump_regs 12
	; Repare que os 16-bits mais altos de eax devem na verdade ser transfeirdos para DX e zerados
	mov	edx, eax
	shr	edx, 16		; EDX = 00009502h
	movzx	eax, ax		; EAX = 0000F900h
	dump_regs 13
	; Agora sim, divide DX:AX (9502 F900h) por BX (C24Fh)
	div	bx		; Resultado é 50001 (C351h) que cabe em 16-bits
	dump_regs 14
	movzx	eax, ax		; mostrar o quociente em AX
	call	print_int
	call	print_nl
	movzx	eax, dx		; mostrar o resto em DX
	call	print_int
	call	print_nl
	
	; Dividindo um quad word em edx:eax (64-bit - unsigned long long int) por um double word em ebx (32-bit - unsigned long int)
	
	; ATENÇÃO: se o resultado da divisão não couber em 32-bits ocorrerá um erro de operação aritmética	
	mov	edx, 0		; EDX = 0h (fazendo a divisão só de EAX na prática)	
	mov	eax, 1234567890	; EAX = 4996 02D2h
	mov	ebx, 12345	; EBX = 0000 3039h
	dump_regs 15
	; Divide EDX:EAX (0000 0000 4996 02D2) por BX (0000 3039)
	div	ebx
	dump_regs 16
	call	print_int	; mostrar o quociente em EAX
	call	print_nl
	mov	eax, edx	; mostrar o resto em DX
	call	print_int
	call	print_nl

	; Agora si, dividindo um número unsigned realmente grande de 64-bits por um de 32-bits
	mov	edx, 3FFFFFFFh	; EDX:EAX = 3FFF FFFF 0000 0001 = 4.611.686.014.132.420.609
	mov	eax, 00000001h
	mov	ebx, 7FFFFFFFh	; EBX = 2.147.483.647
	dump_regs 17
	; Divide EDX:EAX (3FFF FFFF 0000 0001) por BX (7FFFFFFFh)
	div	ebx		; O resultado (7FFFFFFFh = 2.147.483.647) cabe em 32-bits
	dump_regs 18
	call	print_int	; mostrar o quociente em EAX
	call	print_nl
	mov	eax, edx	; mostrar o resto em DX
	call	print_int
	call	print_nl
	
	;---------FIM---------
        popa
        mov     eax, 0            ;Retorna 0 para a rotina que chamou 
        leave                     ;-> main() em driver.c
        ret
