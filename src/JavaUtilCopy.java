import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class JavaUtilCopy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем пути к исходной и целевой папкам у пользователя
        System.out.println("Введите путь к исходной папке:");
        String sourcePath = scanner.nextLine();

        System.out.println("Введите путь к целевой папке:");
        String targetPath = scanner.nextLine();

        try {
            // Вызываем метод copyFolder() для копирования папки
            copyFolder(sourcePath, targetPath);
            System.out.println("Папка успешно скопирована.");
        } catch (IOException e) {
            System.out.println("Ошибка при копировании папки:");
            e.printStackTrace();
        }
    }

    /**
     * Копирует папку из исходного пути в целевой путь.
     *
     * @param sourcePath путь к исходной папке
     * @param targetPath путь к целевой папке
     * @throws IOException при возникновении ошибок чтения файлов или папок
     */
    public static void copyFolder(String sourcePath, String targetPath) throws IOException {
        // Создаем объекты File для исходной и целевой папок
        File sourceFolder = new File(sourcePath);
        File targetFolder = new File(targetPath);

        // Проверяем, существуют ли исходная и целевая папки, и являются ли они директориями
        if (!sourceFolder.exists()  || !targetFolder.exists() ||
        !sourceFolder.isDirectory() || !targetFolder.isDirectory()) {
            throw new IllegalArgumentException("Некорректные пути к папкам");
        }

        // Создаем целевую папку, если она не существует
        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        // Получаем список файлов и подпапок в исходной папке
        File[] files = sourceFolder.listFiles();

        // Рекурсивно копируем файлы и подпапки
        for (File file : files) {
            String fileName = file.getName();
            File target = new File(targetFolder, fileName);

            if (file.isDirectory()) {
                // Рекурсивно копируем подпапку
                copyFolder(file.getAbsolutePath(), target.getAbsolutePath());
            } else {
                // Копируем файл
                Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}
