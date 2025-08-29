package org.javase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;

public class QuranDownloader {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        String urlEng = "https://api.alquran.cloud/v1/surah/%s/en.asad";
        String urlArb = "https://api.alquran.cloud/v1/surah/%s/ar.alafasy";

        File folderPath = new File("D:\\Quran");
        if (!folderPath.exists()) {
            folderPath.mkdirs();
        }

        File quran = new File(folderPath, "quran.json");
        if (quran.exists()) {
            try {
                quran.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create file!");
            }
        }

        ArrayNode surahList = mapper.createArrayNode();
        try (var taskScope = new StructuredTaskScope.ShutdownOnFailure()) {

            List<List<StructuredTaskScope.Subtask<ObjectNode>>> surahs = new ArrayList<>();
            for (int i = 1; i <= 114; i++) {
                String eng = String.format(urlEng, i);
                String arb = String.format(urlArb, i);
                StructuredTaskScope.Subtask<ObjectNode> engTask = taskScope.fork(() -> downloadSurah(eng));
                StructuredTaskScope.Subtask<ObjectNode> arbTask = taskScope.fork(() -> downloadSurah(arb));
                surahs.add(List.of(engTask, arbTask));
            }

            taskScope.join();
            taskScope.throwIfFailed();

            for (List<StructuredTaskScope.Subtask<ObjectNode>> couple : surahs) {
                ObjectNode eng = couple.get(0).get();
                ObjectNode arb = couple.get(1).get();

                ArrayNode engAyahs = (ArrayNode) eng.get("data").get("ayahs");
                ArrayNode arbAyahs = (ArrayNode) arb.get("data").get("ayahs");

                int size = engAyahs.size();
                for (int i = 0; i < size; i++) {
                    ObjectNode surah = (ObjectNode) arbAyahs.get(i);
                    surah.put("engText", engAyahs.get(i).get("text").asText());
                }

                surahList.addAll(arbAyahs);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        writeToFile(quran, surahList);
    }

    private static ObjectNode downloadSurah(String uri) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        String ayah = response.body();
        return (ObjectNode) mapper.readTree(ayah);
    }

    private static void writeToFile(File filePath, ArrayNode quran) {
        try {

            String text = quran.toString();
            Files.writeString(filePath.toPath(), text);
        } catch (Exception ex) {
            System.err.println("Failed to write quran to file.");
        }
    }
}
