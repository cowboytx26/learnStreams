import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.*;
import java.util.Map.Entry.*;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public final class learnStreams {

    public static void main(String[] args){
        int popularWordCount = 5;
        Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("Brian", 30);
        wordCounts.put("Craig", 40);
        wordCounts.put("Kevin", 50);
        wordCounts.put("JC", 60);
        wordCounts.put("Pat", 70);
        wordCounts.put("Sharon", 80);

        Comparator compareMyWordCounts = new WordCountComparator();
        PriorityQueue<Map.Entry<String, Integer>> sortedCounts =
                new PriorityQueue<>(wordCounts.size(), compareMyWordCounts);
        sortedCounts.addAll(wordCounts.entrySet());

        long minLimit = Math.min(popularWordCount, wordCounts.size());
        Map<String, Integer> topCounts = new LinkedHashMap<>();
        topCounts =
        sortedCounts
                .stream()
                .sorted(compareMyWordCounts)
                .limit(minLimit)
                        //.forEach(System.out::println);
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                //.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e2, LinkedHashMap::new));
        System.out.println(topCounts);
    }

    private static final class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue();
            }
            if (a.getKey().length() != b.getKey().length()) {
                return b.getKey().length() - a.getKey().length();
            }
            return a.getKey().compareTo(b.getKey());
        }
    }
}

