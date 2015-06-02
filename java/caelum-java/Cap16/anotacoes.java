import java.util.*;

// ==========
// LISTAS
// ==========

// Utiliza internamente um array, é mais rápida para acesso a itens aleatórios
ArrayList al = new ArrayList();

// Útil para o caso de um array que deve permitir acesso simultâneo
Vector v = new Vector();

// Utiliza internamente uma lista encadeada. É mais rápida para inserir nas pontas
LinkedList ll = new LinkedList();

// Cria uma lista de algum tipo mas que é acessa pela interface genérica List
// É preferível sempre usar a interface List no lugar de uma implementação específica
// pois se for necessário trocar a implementação, a interface permanece sempre a mesma.
List l = new ...();

// Adiciona um objeto ao fim da lista
l.add(obj);

// Adiciona em uma posição específica da lista (0 = início)
l.add(pos, obj);

// Retorna a qtde de elementos na lista
int n = l.size();

// Para acessar um elemento é necessário fazer um cast
Tipo obj = (Tipo) l.get(i);

// Remove o objeto referenciado da lista
l.remove(obj);

// Retorna se o elemento está ou não na lista
boolean b = l.contains(obj);

// Remove todos os elementos da lista
l.clear();

// Outros métodos úteis: indexOf, lastIndexOf, isEmpty, toArray

// Métodos exclusivos da LinkedList:
ll.addFist(obj);
ll.addLast(obj);
ll.getFirst();
ll.getLast();
ll.removeFirst();
ll.removeLast();

// Métodos exclusivos de Vetor:
v.addElement(obj);
v.elementAt(i);
v.firstElement();
v.insterElementAt(obj, i);
v.lastElement();

// Ao percorrer uma lista, usar sempre um iterator (ver a seguir)
...

// Utilizando generics para criar uma lista tipada (que só recebe um tipo de objet)
List<Tipo> lt = new ArrayList<Tipo>;

// Em Java 7, não é necessário repetir o tipo (operador diamente <>)
List<Tipo> lt = new LinkedList<>;

// Não é necessário cast na hora de obter um valor
Tipo t = lt.get(i);

// Ordena uma lista
Collections.sort(l);

// Par isso, o tipo do objeto deve implementar a interface Comparable

class MeuTipo implements Comparable<MeuTipo> {

    public int compareTo(MeuTipo obj) {
        if (this.atributo < obj.atributo)
            return -1;
        else if (this.atributo > obj.atributo)
            return 1;
        else
            return 0;
    }

}

//Outros métodos úteis da classe Collections
int pos = Collections.binarySearch(lista, obj);
T obj = Collections.max(colecao);
T obj = Collections.min(colecao);
Collections.reverse(lista);
// copy, shuffle, rotate, etc.


// Existe uma classe similar para arrays: java.util.Arrays

// ==========
// CONJUNTOS
// ==========

// Conjuntos não permitem elementos repetidos e a ordem de armazenamento não é definida
// A maior vantagem em relação às listas é que a velocidade para pesquisa (contains) é
// bem maior

// Conjunto armazenado internamente como uma tabela Hash
Set hs = new HashSet();

// Mantém os itens ordenados na ordem de inserção
Set lhs = new LinkedHashSet();

// Conjunto armazenado internamente como uma árvore
// mantém os itens ordenados conforme a interface Comparable
// ou conforme o objeto Comparator passado no construtor
Set ts = new TreeSet();

// A interface Set se aplica a todas as implementações e seu uso é preferível ao se referir a algum conjunto

// Os métodos são análogos à lista
s.add(obj);
s.remove(obj);
boolean b = s.contains(obj);
s.toArray();

// ==========
// ITERATOR
// ==========

// Usando um iterator diretamente
Iterator iter = colecao.iterator();
while (iter.hasNext()) {
    Tipo obj = iter.next();
    ...
}

// Através de for-each
for (Tipo obj : colecao) {
    ...
}

// A interface Iterator possui alguns usos, como remover elementos com remove()
// Também há uma interface ListIterator que tem métodos adicionais que podem ser úteis
// ao tratar listas.


// ==========
// MAPAS
// ==========

// Também conhecidos como dicionários ou matrizes/arrays associativos

// Implementações: 
// HashMap - Non-thread safe
// TreeMap - Non-thread safe, keeps elements ordered ?
// Hashtable - Thread-safe
// ConcurrentHashMap - Threaf-safe, high concurrent

Map<TipoChave, TipoValor> mapa = new HashMap<>();

// Acrescenta um valor ao mapa
mapa.put(chave, valor);

// Obtém um valor do mapa
mapa.get(chave);

// Verifica se um item está no mapa
boolean b = mapa.contains(chave);

// Retorna o conjunto das chaves de um mapa
Set chaves = mapa.keySet();

// Retorna uma coleção dos valores de um mapa
Collection valores = mapa.values();

// ==========
// DICAS
// ==========

/*
1) Algumas coleções permitem indicar uma capacidade inicial. (evitar redimensionamento)
2) Não use coleções ordenadas se não precisar, pois há um custo de performance.
    Por exemplo: TreeSet tem custo O(log(n)) enquanto HashSet tem custo O(1)
3) Use iterators sempre que possível, no lugar de um for(int i = 0; i < lista.size(); i++) { }


*/