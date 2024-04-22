package ogrg.zerock;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GsonTest {

    public List<TagoDTO> tagoParserUrl(String urlString) {
        List<TagoDTO> tagoDTOList = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // 받은 JSON 데이터를 콘솔에 출력
            System.out.println("Received JSON data:");
            System.out.println(sb.toString());

            // JSON 데이터 파싱
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(sb.toString()).getAsJsonObject();
            JsonObject response = jsonObject.getAsJsonObject("response");
            JsonObject body = response.getAsJsonObject("body");
            JsonObject items = body.getAsJsonObject("items");
            JsonArray itemArray = items.getAsJsonArray("item");

            // itemArray를 순회하면서 TagoDTO 객체로 변환하여 리스트에 추가
            for (JsonElement element : itemArray) {
                JsonObject item = element.getAsJsonObject();
                String trainNo = item.get("trainno").getAsString();
                String depPlace = item.get("depplacename").getAsString();
                String arrPlace = item.get("arrplacename").getAsString();
                String depTime = item.get("depplandtime").getAsString();
                String arrTime = item.get("arrplandtime").getAsString();

                TagoDTO tagoDTO = new TagoDTO();
                tagoDTO.setTrainNo(trainNo);
                tagoDTO.setDepPlace(depPlace);
                tagoDTO.setArrPlace(arrPlace);
                tagoDTO.setDepTime(depTime);
                tagoDTO.setArrTime(arrTime);

                tagoDTOList.add(tagoDTO);
            }
        } catch (Exception e) {
            System.out.println("API 호출 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return tagoDTOList;
    }

}
