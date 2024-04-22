package ogrg.zerock;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            String apiUrl = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20240405";

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject boxOfficeResult = jsonObject.getAsJsonObject("boxOfficeResult");
            String showRange = boxOfficeResult.get("showRange").getAsString();
            JsonArray weeklyBoxOfficeList = boxOfficeResult.getAsJsonArray("weeklyBoxOfficeList");

            System.out.println("기간: " + showRange);
            System.out.println("===========================================");

            for (JsonElement element : weeklyBoxOfficeList) {
                JsonObject movieData = element.getAsJsonObject();
                String rank = movieData.get("rank").getAsString();
                String movieNm = movieData.get("movieNm").getAsString();
                String audiAcc = movieData.get("audiAcc").getAsString();
                System.out.println("랭킹: " + rank + "등   제목: " + movieNm + "   관객수: " + audiAcc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}