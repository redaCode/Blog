package com.reda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*Intermediate:一个流后面可以跟一个或多个Intermediate操作,它会对流进行处理然后返回一个新的流.
常见的Intermediate操作有
    map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、
    sequential、 unordered
Terminal:terminal 操作是流的最后一个操作.
Terminal操作有
    forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、
    allMatch、 noneMatch、 findFirst、 findAny、 iterator
short-circuiting有2种类型:
1.对于一个 intermediate 操作，如果它接受的是一个无限大（infinite/unbounded）的 Stream，但返回一个有限的新 Stream。
2.对于一个 terminal 操作，如果它接受的是一个无限大的 Stream，但能在有限的时间计算出结果。
常见的short-circuiting操作有
    anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit*/
public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(6,43,67,76,12,4,5,235,7,6,7,null);
        List<Integer> result = nums.stream()
                .filter(i -> i!= null && i > 4)         //filter过滤
                .distinct()                             //distinct去重
                .sorted((i1,i2) -> i1.compareTo(i2))    //sorted排序
                .skip(1)                                //skip跳过前一个元素
                .limit(5)                               //limit取前5个元素
                .map(i -> i+1)                          //map转化为另一个对象,map生成的是1:1的映射
                .peek(i ->System.out.print(i + " "))    //peek为每个元素包装Consumer
                .collect(Collectors.toList());          //转为List,和toArray类型
        result.parallelStream().forEach(System.out::println);   //foreach和parallelStream并发处理不保证顺序
        result.stream().max((i1,i2) -> i1.compareTo(i2)).get(); //max

        Stream<List<Integer>> stream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        //[[1], [2, 3], [4, 5, 6]]
        stream.collect(Collectors.toList()).toString();
        //[1, 2, 3, 4, 5, 6]
        stream.flatMap(list -> list.stream()).collect(Collectors.toList()).toString(); //flatMap生成的是1:n的映射

        //reduce是一个聚合的方法,有2个参数,第一个参数是初始值,第二个参数是一个运算规则,reduce通过运算规则将初始值和
        //所有的Stream元素聚合,当没有初始值时返回Optional对象如sumValue
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1,2,3).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

    }
}
