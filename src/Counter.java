import java.io.BufferedWriter; //для записи в выходной поток
import java.io.IOException; //для обработки ввода-вывода

//класс, реализующий подсчет символов
public class Counter {
    private int[] amount_of_letter; //массив для количества символов

    public Counter(){ //конструктор по умолчанию
        amount_of_letter = new int[52];
    }

    //метод подсчета символов из переданной строки
    public void letter_counter(String symbols){
        //проходим по каждому символу
        for (char symbol : symbols.toCharArray()){
            //подсчет заглавных букв
            if (symbol >= 'A' && symbol <= 'Z') {
                amount_of_letter[symbol - 'A']++;
            }
            //подсчет строчных букв
            else if(symbol >= 'a' && symbol <= 'z') {
                amount_of_letter[symbol - 'a' + 26]++;
            }
        }
    }

    //метод вывода результатов в BufferedWriter
    public void write_counter(BufferedWriter writer) throws IOException {
        writer.write("Letter counter:\n");
        //форматируем строку для записи заглавных букв
        for (int i = 0; i < 26; i++) {
            writer.write(String.format("%c: %d\n", (char) ('A' + i), amount_of_letter[i]));
        }
        //форматируем строку для записи строчных букв
        for (int i = 0; i < 26; i++) {
            writer.write(String.format("%c: %d\n", (char) ('a' + i), amount_of_letter[i + 26]));
        }
    }
}
