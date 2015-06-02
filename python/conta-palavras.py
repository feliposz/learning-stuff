# conta numero de palavras total e em que linha atinge um certo número mínimo de palavras

# abre o arquivo
f = open("c:\\tmp\\lista.txt", "r")

# le todas as linhas na lista flines
flines = f.readlines()

words = 0
x = 0

# conta quantas palavras há em cada linha e acumula no contador
for line in flines:
  words += len(line.split())
  # se atingiu certo número de palavras, imprime o número de linha e reinicia contador
  if words > 2000: 
    print(x)
    words = 0
  x += 1
