package stream;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
public class StreamTest {

    @Test
    public void iterate() {
        //hasNext 用来判断何时结束流，这个与seed有关。如何该函数不迭代保留seed计算，返回的流可能为空。
        Stream.iterate(0, e -> e < 10, e -> e + 1).forEach(e -> log.info(String.valueOf(e)));
    }

    @Test
    public void ofNullable() {
        Optional<Object> first = Stream.ofNullable(null).findFirst();
        Assertions.assertTrue(first.isPresent(), "不能为空");
    }

    @Test
    public void takeWhile() {
        //Stream中元素会被断言Predicate，一旦元素断言为false就中断操作，
        // 忽略掉没有断言的元素（及时未断言中的元素有满足条件的），仅仅把之前满足元素返回。
        List<Integer> collect1 = Stream.of(1, 2, 3, 5, 6, 4).takeWhile(e -> e < 4).collect(Collectors.toList());//第一个就返回false，输出空
        List<Integer> collect2 = Stream.of(8, 2, 3, 1, 6, 4).takeWhile(e -> e < 4).collect(Collectors.toList());
        log.info(collect1.toString());
        log.info(collect2.toString());
    }

    @Test
    public void dropWhile() {
        //和takeWhile机制类似，也用来筛选Stream中的元素。不过符合断言的元素会被从Stream中移除。
        // 一旦元素断言为false，就会把断言为false的元素以及后面的元素统统返回。
        //和filter操作可不一样
        List<Integer> collect1 = Stream.of(1, 2, 3, 5, 6, 4).dropWhile(e -> e < 4).collect(Collectors.toList());
        List<Integer> collect2 = Stream.of(8, 2, 3, 1, 6, 4).dropWhile(e -> e < 4).collect(Collectors.toList());
        log.info(collect1.toString());
        log.info(collect2.toString());
    }


}
