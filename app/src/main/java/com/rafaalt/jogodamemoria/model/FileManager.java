package com.rafaalt.jogodamemoria.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String FILE_NAME = "recordes.txt";

    // Método para salvar informações no arquivo
    public static void saveData(Context context, Recorde newRecorde) {
        List<Recorde> dataList = loadData(context);

        // Procura pelo ID no arquivo
        boolean found = false;
        for (int i = 0; i < dataList.size(); i++) {
            Recorde existingData = dataList.get(i);
            if (existingData.getTamanho() == newRecorde.getTamanho()) {
                // Atualiza os dados se o ID existir
                dataList.set(i, newRecorde);
                found = true;
                break;
            }
        }

        // Se o ID não existir, adiciona os novos dados
        if (!found) {
            dataList.add(newRecorde);
        }

        // Salva a lista atualizada no arquivo
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {

            for (Recorde x : dataList) {
                // Escreve cada linha no arquivo no formato "id:nome"
                writer.write(x.getTamanho() + ";" + x.getRecordeJogadas() + "\n");
                Log.d("FileRecorde", "Salvou! [" + x.getTamanho() + "]: " + x.getRecordeJogadas());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar informações do arquivo
    public static ArrayList<Recorde> loadData(Context context) {
        ArrayList<Recorde> dataList = new ArrayList<>();

        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    int tamanho = Integer.parseInt(parts[0]);
                    int recorde = Integer.parseInt(parts[1]);
                    dataList.add(new Recorde(tamanho, recorde));
                    Log.d("FileRecorde", "Carregou! [" + tamanho + "]: " + recorde);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
