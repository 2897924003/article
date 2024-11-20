package article;


import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {

    @Test
    public void deleteRepeatedStr(){
        //测试数据
        List<String> testData = List.of("apple", "banana", "apple", "orange", "banana", "grape", "grape");
        List<String> result = testData.stream().distinct().toList();
        System.out.println(result);

    }

    @Test
    public void getMaxAgeName(){
        Map<String, Integer> nameAgeMap = new HashMap<>();
        nameAgeMap.put("John", 35);
        nameAgeMap.put("Bob", 40);
        nameAgeMap.put("Alice", 30);
        nameAgeMap.put("Tom", 45);
        nameAgeMap.put("Jerry", 50);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> age = new ArrayList<>();
        nameAgeMap.forEach((n,a)->{name.add(n);age.add(a);});


    }
}
