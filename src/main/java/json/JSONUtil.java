package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONUtil {

    private String[] attributi = {"HTTP_302", "HTTP_NO", "MIME_ALL", "abcde"};

    public JSONObject trasformaJson() {

        JSONParser jsonParser = new JSONParser();
        JSONObject risultato = new JSONObject();

        try (FileReader reader = new FileReader("C:\\Users\\Alessandro\\Desktop\\test be\\test-be\\allegato.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("102770207");
            JSONObject jsonObject2 = (JSONObject) jsonObject.get("213881318");
            JSONArray http_302 = new JSONArray();
            JSONArray http_no = new JSONArray();
            JSONArray mime_all = new JSONArray();

            http_302.add(this.generaJsonObj(jsonObject1, "102770207", "HTTP_302"));
            http_302.add(this.generaJsonObj(jsonObject2, "213881318", "HTTP_302"));

            http_no.add(this.generaJsonObj(jsonObject1, "102770207", "HTTP_NO"));
            http_no.add(this.generaJsonObj(jsonObject2, "213881318", "HTTP_NO"));


            mime_all.add(this.generaJsonObj(jsonObject1, "102770207", "MIME_ALL"));
            mime_all.add(this.generaJsonObj(jsonObject2, "213881318", "MIME_ALL"));


            risultato.put("HTTP_302", http_302);
            risultato.put("HTTP_NO", http_no);
            risultato.put("MIME_ALL", mime_all);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return risultato;
    }

    public JSONObject generaJsonObj(JSONObject obj, String nome, String attributo) {
        JSONObject risultato = new JSONObject();
        JSONObject elementoRisultato = (JSONObject) obj.get("ALL");

        risultato.put("crawlId", nome);
        risultato.put("total", elementoRisultato.get(attributo));
        risultato.put("totalInt", elementoRisultato.get("INT-" + attributo));
        risultato.put("totalExt", elementoRisultato.get("EXT-" + attributo));

        return risultato;
    }

   /* public String rielaboraJson(String json) {
        List<JsonValue> oggAnnidati = new LinkedList<>();
        List<String> crawlIds = new LinkedList<>();
        StringBuffer s = new StringBuffer();
        JsonParser parser;

        try {
            parser = Json.createParser(new FileReader("C:\\Users\\Alessandro\\Desktop\\test be\\test-be\\allegato.json")/*StringReader(json));
            parser.next();
            while (parser.hasNext()) {
                if (parser.next() == JsonParser.Event.KEY_NAME) {
                    crawlIds.add(parser.getValue().toString());
                    parser.next();
                    parser.getObjectStream()
                            .forEach(e -> {
                                oggAnnidati.add(e.getValue());
                            });
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return "errore";
        }
        for (JsonValue ogg : oggAnnidati) {
            parser = Json.createParser(new StringReader(ogg.toString()));
            while (parser.hasNext()) {
                if (parser.next() == JsonParser.Event.KEY_NAME) {
                    switch(parser.getString()){
                        case "":
                    }
                    if (this.filtroProprieta(parser.getString())) {
                        parser.next();
                        s.append(parser.getValue() + "\n");
                    } else {
                        parser.next();
                    }
                }
            }
        }
        parser.close();
        return s.toString();
    }*/

   /*public boolean filtroProprieta(String proprieta) {

        return proprieta.contains(this.array[0]) ||
                proprieta.contains(this.array[1]) ||
                proprieta.contains(this.array[2]) ||
                proprieta.contains(this.array[3]);
    }*/

    //public void calcola(Json risultato, String proprieta, int valore) {

}
