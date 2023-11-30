package edu.java.project3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
	
    public static void main(String[] args) {
        String folderName = "exampleFolder";

        // 폴더가 존재하는지 확인
        if (isFolderExists(folderName)) {
            System.out.println("폴더가 이미 존재합니다.");
            System.out.println(Paths.get(folderName));
        } else {
            // 폴더가 존재하지 않으면 생성
            createFolder(folderName);
            System.out.println("폴더를 생성했습니다.");
        }
    }

    // 폴더가 존재하는지 여부를 확인하는 메서드
    private static boolean isFolderExists(String folderName) {
        Path folderPath = Paths.get(folderName);
        return Files.exists(folderPath) && Files.isDirectory(folderPath);
    }

    // 폴더를 생성하는 메서드
    private static void createFolder(String folderName) {
        try {
            Files.createDirectory(Paths.get(folderName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}