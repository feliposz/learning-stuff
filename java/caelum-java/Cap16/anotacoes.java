import java.util.*;

// ==========
// LISTAS
// ==========

// Utiliza internamente um array, � mais r�pida para acesso a itens aleat�rios
ArrayList al = new ArrayList();

// �til para o caso de um array que deve permitir acesso simult�neo
Vector v = new Vector();

// Utiliza internamente uma lista encadeada. � mais r�pida para inserir nas pontas
LinkedList ll = new LinkedList();

// Cria uma lista de algum tipo mas que � acessa pela interface gen�rica List
// � prefer�vel sempre usar a interface List no lugar de uma implementa��o espec�fica
// pois se for necess�rio trocar a implementa��o, a interface permanece sempre a mesma.
List l = new ...();

// Adiciona um objeto ao fim da lista
l.add(obj);

// Adiciona em uma posi��o espec�fica da lista (0 = in�cio)
l.add(pos, obj);

// Retorna a qtde de elementos na lista
int n = l.size();

// Para acessar um elemento � necess�rio fazer um cast
Tipo obj = (Tipo) l.get(i);

// Remove o objeto referenciado da lista
l.remove(obj);

// Retorna se o elemento est� ou n�o na lista
boolean b = l.contains(obj);

// Remove todos os elementos da lista
l.clear();

// Outros m�todos �teis: indexOf, lastIndexOf, isEmpty, toArray

// M�todos exclusivos da LinkedList:
ll.addFist(obj);
ll.addLast(obj);
ll.getFirst();
ll.getLast();
ll.removeFirst();
ll.removeLast();

// M�todos exclusivos de Vetor:
v.addElement(obj);
v.elementAt(i);
v.firstElement();
v.insterElementAt(obj, i);
v.lastElement();

// Ao percorrer uma lista, usar sempre um iterator (ver a seguir)
...

// Utilizando generics para criar uma lista tipada (que s� recebe um tipo de objet)
List<Tipo> lt = new ArrayList<Tipo>;

// Em Java 7, n�o � necess�rio repetir o tipo (operador diamente <>)
List<Tipo> lt = new LinkedList<>;

// N�o � necess�rio cast na hora de obter um valor
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

//Outros m�todos �teis da classe Collections
int pos = Collections.binarySearch(lista, obj);
T obj = Collections.max(colecao);
T obj = Collections.min(colecao);
Collections.reverse(lista);
// copy, shuffle, rotate, etc.


// Existe uma classe similar para arrays: java.util.Arrays

// ==========
// CONJUNTOS
// ==========

// Conjuntos n�o permitem elementos repetidos e a ordem de armazenamento n�o � definida
// A maior vantagem em rela��o �s listas � que a velocidade para pesquisa (contains) �
// bem maior

// Conjunto armazenado internamente como uma tabela Hash
Set hs = new HashSet();

// Mant�m os itens ordenados na ordem de inser��o
Set lhs = new LinkedHashSet();

// Conjunto armazenado internamente como uma �rvore
// mant�m os itens ordenados conforme a interface Comparable
// ou conforme o objeto Comparator passado no construtor
Set ts = new TreeSet();

// A interface Set se aplica a todas as implementa��es e seu uso � prefer�vel ao se referir a algum conjunto

// Os m�todos s�o an�logos � lista
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

// Atrav�s de for-each
for (Tipo obj : colecao) {
    ...
}

// A interface Iterator possui alguns usos, como remover elementos com remove()
// Tamb�m h� uma interface ListIterator que tem m�todos adicionais que podem ser �teis
// ao tratar listas.


// ==========
// MAPAS
// ==========

// Tamb�m conhecidos como dicion�rios ou matrizes/arrays associativos

// Implementa��es: 
// HashMap - Non-thread safe
// TreeMap - Non-thread safe, keeps elements ordered ?
// Hashtable - Thread-safe
// ConcurrentHashMap - Threaf-safe, high concurrent

Map<TipoChave, TipoValor> mapa = new HashMap<>();

// Acrescenta um valor ao mapa
mapa.put(chave, valor);

// Obt�m um valor do mapa
mapa.get(chave);

// Verifica se um item est� no mapa
boolean b = mapa.contains(chave);

// Retorna o conjunto das chaves de um mapa
Set chaves = mapa.keySet();

// Retorna uma cole��o dos valores de um mapa
Collection valores = mapa.values();

// ==========
// DICAS
// ==========

/*
1) Algumas cole��es permitem indicar uma capacidade inicial. (evitar redimensionamento)
2) N�o use cole��es ordenadas se n�o precisar, pois h� um custo de performance.
    Por exemplo: TreeSet tem custo O(log(n)) enquanto HashSet tem custo O(1)
3) Use iterators sempre que poss�vel, no lugar de um for(int i = 0; i < lista.size(); i++) { }


*/