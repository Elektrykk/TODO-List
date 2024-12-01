package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String fileName = "lista.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie istnieje. Zostanie utworzony przy zapisie.");
        } catch (IOException e) {
            System.out.println("Błąd podczas odczytu pliku: " + e.getMessage());
        }

        while (true) {
            System.out.println("Wybierz co chcesz zrobić: \n1-dodaj wpis 2-pokaz liste 3-usun wpis 0-wyjdz");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("dodaj wpis:");
                        String wpis = scanner.nextLine();
                        list.add(wpis);

                        System.out.println("Aby zakończyć dodawanie wpisów, wpisz 0. Aby kontynuować, naciśnij dowolny klawisz.");
                        String decision = scanner.nextLine();
                        if (decision.equals("0")) {
                            break;
                        }
                    }
                    saveListToFile(list, fileName);
                    break;

                case 2:
                    if (list.isEmpty()) {
                        System.out.println("Lista jest pusta.");
                    } else {
                        int k = 0;
                        for (String wpis : list) {
                            ++k;
                            System.out.println(k + ". " + wpis);
                        }
                    }
                    break;

                case 3:
                    if (list.isEmpty()) {
                        System.out.println("Lista jest pusta. Nie ma nic do usunięcia.");
                    } else {
                        int l = 0;
                        for (String wpis : list) {
                            ++l;
                            System.out.println(l + ". " + wpis);
                        }

                        while (true) {
                            System.out.println("Wybierz numer wpisu do usunięcia (lub wpisz 0, aby anulować): ");
                            int wpisDoUsuniecia = scanner.nextInt();
                            scanner.nextLine();

                            if (wpisDoUsuniecia == 0) {
                                break;
                            } else if (wpisDoUsuniecia > 0 && wpisDoUsuniecia <= list.size()) {
                                list.remove(wpisDoUsuniecia - 1);
                                System.out.println("Wpis został usunięty.");
                                saveListToFile(list, fileName);
                            } else {
                                System.out.println("Nieprawidłowy numer. Spróbuj ponownie.");
                            }
                        }
                    }
                    break;

                case 0:
                    System.out.println("Zakończono program.");
                    return;

                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private static void saveListToFile(ArrayList<String> list, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String wpis : list) {
                bw.write(wpis);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }
}