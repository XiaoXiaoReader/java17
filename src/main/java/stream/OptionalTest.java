package stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class OptionalTest {

    @Test
    public void ofNullable() {

        List<String> collect = Optional.ofNullable("1").stream().collect(Collectors.toList());
        log.info(collect.toString());
    }

    @Test
    public void ifPresentOrElse() {
        Optional.ofNullable("1").ifPresentOrElse(e -> log.info(e), () -> log.info("如果有值了怎么消费，没有值执行消费"));
        Optional.ofNullable(null).ifPresentOrElse(e -> log.info(String.valueOf(e)), () -> log.info("如果有值了怎么消费，没有值执行消费"));
    }

    @Test
    public void or() {
        //如果有值就返回有值的Optional，否则就提供能获取一个有值的Optional的渠道
        Optional<String> or1 = Optional.ofNullable("1").or(() -> Optional.of("2"));
        Optional<Object> or2 = Optional.ofNullable(null).or(() -> Optional.of("2"));
        Optional<Object> or3 = Optional.ofNullable(null).or(() -> Optional.ofNullable(null));
        log.info(or1.get());
        log.info(String.valueOf(or2.get()));
        or3.ifPresent(e -> log.info(String.valueOf(e)));
    }
}
