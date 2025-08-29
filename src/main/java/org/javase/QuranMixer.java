package org.javase;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QuranMixer {
    public static void main(String[] args) {
        String engQuran = "D:\\Quran\\EngQuran.json";
        String arbQuran = "D:\\Quran\\ArbQuran.json";
        String quran = "D:\\Quran\\quran.json";

        ObjectMapper mapper = new ObjectMapper();

        try {
            byte[] engBytes = Files.readAllBytes(Paths.get(engQuran));
            byte[] arbBytes = Files.readAllBytes(Paths.get(arbQuran));

            ArrayNode engSurahs = (ArrayNode) mapper.readTree(engBytes);
            ArrayNode arbSurahs = (ArrayNode) mapper.readTree(arbBytes);

            for (int i = 0; i < arbSurahs.size(); i++) {
                ObjectNode arbSurah = (ObjectNode) arbSurahs.get(i);
                ObjectNode engSurah = (ObjectNode) engSurahs.get(i);

                ArrayNode arbAyahs = (ArrayNode) arbSurah.get("ayahs");
                ArrayNode engAyahs = (ArrayNode) engSurah.get("ayahs");

                for (int j = 0; j < arbAyahs.size(); j++) {
                    ObjectNode arbAyah = (ObjectNode) arbAyahs.get(j);
                    ObjectNode engAyah = (ObjectNode) engAyahs.get(j);
                    arbAyah.put("engText", engAyah.get("text").asText());
                }

                ObjectNode ayahMap = mapper.createObjectNode();
                for (int z = 1; z <= arbAyahs.size(); z++) {
                    var arbAyah = arbAyahs.get(z - 1);
                    ayahMap.put(z + "", arbAyah);
                }

                arbSurah.remove("ayahs");
                arbSurah.put("ayahs", ayahMap);
            }


            ObjectNode surahMap = mapper.createObjectNode();
            for (JsonNode arbSurah : arbSurahs) {
                surahMap.put(arbSurah.get("number").asText(), arbSurah);
            }
            writeToFile(Paths.get(quran), surahMap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeToFile(Path filePath, JsonNode quran) {
        try {

            String text = quran.toString();
            Files.writeString(filePath, text);
        } catch (Exception ex) {
            System.err.println("Failed to write quran to file.");
        }
    }
}
