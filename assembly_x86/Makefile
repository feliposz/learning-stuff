CC=gcc
AS=nasm
ASFLAGS= -f win32
RM=del
ASMBASE=driver.obj asm_io.obj
EXES=first.exe skel.exe inteiros1.exe inteiros2.exe prob_div.exe inteiros3.exe inteiros4.exe compara.exe primes.exe shiftops.exe max.exe stack.exe sub3.exe sub_soma.exe
OBJS=first.obj skel.obj inteiros1.obj inteiros2.obj prob_div.obj inteiros3.obj inteiros4.obj compara.obj primes.obj shiftops.obj max.obj stack.obj sub3.obj sub_soma.obj driver.obj

.SUFFIXES:
.SUFFIXES: .obj .asm .cpp .c

all: $(EXES)

.asm.obj:
	$(AS) $(ASFLAGS) $*.asm

clean:
	$(RM) $(OBJS) $(EXES)

driver.obj: driver.c
	$(CC) -c $^ -o $@
	
first.exe: $(ASMBASE) first.obj
	$(CC) $^ -o $@

skel.exe: $(ASMBASE) skel.obj
	$(CC) $^ -o $@

inteiros1.exe: $(ASMBASE) inteiros1.obj
	$(CC) $^ -o $@
	
inteiros2.exe: $(ASMBASE) inteiros2.obj
	$(CC) $^ -o $@
	
prob_div.exe: $(ASMBASE) prob_div.obj
	$(CC) $^ -o $@
	
inteiros3.exe: $(ASMBASE) inteiros3.obj
	$(CC) $^ -o $@
	
math.exe: $(ASMBASE) math.obj
	$(CC) $^ -o $@
	
inteiros4.exe: $(ASMBASE) inteiros4.obj
	$(CC) $^ -o $@
	
compara.exe: $(ASMBASE) compara.obj
	$(CC) $^ -o $@
	
primes.exe: $(ASMBASE) primes.obj
	$(CC) $^ -o $@

shiftops.exe: $(ASMBASE) shiftops.obj
	$(CC) $^ -o $@

shift_arit.exe: $(ASMBASE) shift_arit.obj
	$(CC) $^ -o $@

max.exe: $(ASMBASE) max.obj
	$(CC) $^ -o $@

bits1.exe: bits1.c
	$(CC) $^ -o $@

stack.exe: $(ASMBASE) stack.obj
	$(CC) $^ -o $@

sub3.exe: $(ASMBASE) sub3.obj
	$(CC) $^ -o $@

sub_soma.exe: $(ASMBASE) sub_soma.obj
	$(CC) $^ -o $@
