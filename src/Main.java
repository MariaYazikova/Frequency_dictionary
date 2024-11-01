import java.io.File; //для файлов и директорий
import java.io.BufferedReader; //для чтения из входного потока
import java.io.BufferedWriter; //для записи в выходной поток
import java.io.FileReader; //для чтения файла
import java.io.FileWriter; //для записи в файл
import java.util.Scanner; //для обработки ввода
import java.io.IOException; //для обработки ввода-вывода

//основной класс
public class Main {
    public static void main(String[] args) {
        //используем try-with-resources для автоматического закрытия Scanner
        try (Scanner input = new Scanner(System.in)){
            //ввод пользователем имя файла для чтения
            System.out.println("Enter an input file name: ");
            String input_file_name = input.nextLine();
            File input_file = new File(input_file_name);
            //проверка файла на существование
            if (!(input_file.exists())){
                System.out.println("File '" + input_file_name + "' doesn't exists");
                return;
            }
            //проверка, что переданное имя - файл
            if (!(input_file.isFile())){
                System.out.println("'" + input_file_name + "' is not a file");
                return;
            }

            //ввод пользователем имя файла для записи результата
            System.out.println("Enter an output file name: ");
            String output_file_name = input.nextLine();
            File output_file = new File(output_file_name);
            //проверка на существование файла
            if (!(output_file.exists())){
                if(output_file.createNewFile()){
                    System.out.println("The '" + output_file_name + "' was created because it was not found");
                }
            }
            //если файл не существует - создаём его
            else {
                if (!(output_file.isFile())){
                    System.out.println("'" + output_file_name + "' is not a file");
                    return;
                }
            }

            //инициализация объекта для подсчета символов
            Counter counter = new Counter();
            //открытие BufferedReader для чтения данных из input_file с try-with-resources
            try (BufferedReader reader = new BufferedReader(new FileReader(input_file))) {
                //построчно читаем файл пока не достигнем конца
                String str;
                while ((str = reader.readLine()) != null) {
                    counter.letter_counter(str);//строку передаем в метод Counter класса
                }
            }
            //открытие BufferedWriter для записи данных в output_file с try-with-resources
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output_file))) {
                counter.write_counter(writer);//вызов метода класса Counter
                System.out.println("Results written to '" + output_file_name + "'");
            }

        } catch (IOException error){ //обработка исключений ввода-вывода
            System.out.println("An I/O error occurred" + error.getMessage());
        }
    }
}