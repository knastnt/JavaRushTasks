package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipAddCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Добавление файла в архив.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите полный путь файла:");
        Path toAddPath = Paths.get(ConsoleHelper.readString());
        zipFileManager.addFile(toAddPath);

        ConsoleHelper.writeMessage("Добавление файла завершено.");
    }
}
