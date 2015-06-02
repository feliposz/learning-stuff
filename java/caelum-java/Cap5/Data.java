/*
_______________________
|        Data         |
|_____________________|
| +dia: int           |
| +mes: int           |
| +ano: int           |
|_____________________|
| +toString(): String |
|_____________________|

*/

class Data {
    int dia;
    int mes;
    int ano;
    
    public String toString() { // Exercício 9
        return dia + "/" + mes + "/" + ano;
    }
}
