import java.util.*;

public class Anagrama {

    private String palavra;
    private Map<String, Map<String, Integer>> map = new HashMap<>();

    public Anagrama(String palavra) {
        this.palavra = palavra;
    }

    public long quantidadeAnagramas() {
        List<String> substrings = getSubstrings();

        for (String substring : substrings) {
            char[] caracteres = substring.toCharArray();
            Arrays.sort(caracteres);
            String stringOrdenada = new String(caracteres);

            if (map.containsKey(stringOrdenada)) {
                Map<String, Integer> mapValue = map.get(stringOrdenada);
                mapValue.put(substring,
                        Objects.requireNonNullElse(mapValue.get(substring), 1) + 1
                );
            } else {
                map.put(stringOrdenada, new HashMap<>(Map.of(substring, 1)));
            }
        }

        var qtdAnagramas = map.values().stream()
                .filter(value -> value.values().stream().count() > 1 ||
                        value.values().stream().filter(count -> count > 1).count() > 0)
                .count();

        return qtdAnagramas;
    }

    public List<String> getAnagramas() {
        List<String> anagramas = new ArrayList<>();

        for (Map<String, Integer> mapValues : map.values()) {
            if (mapValues.values().size() > 1) {
                anagramas.add(mapValues.keySet().toString());
                continue;
            }

            for (Map.Entry<String, Integer> value : mapValues.entrySet()) {
                if (value.getValue() > 1) {
                    anagramas.add(String.format("[ %s, %s ]", value.getKey(), value.getKey()));
                }
            }
        }

        return anagramas;
    }

    private List<String> getSubstrings() {
        List<String> substrings = new ArrayList<>();

        for (int i = 0; i < palavra.length(); i++) {
            for (int j = i; j < palavra.length(); j++) {
                substrings.add(palavra.substring(i, j + 1));
//                System.out.println(palavra.substring(i, j + 1));
            }
        }

        return substrings;
    }

}
