package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkMapIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .message()
                .isEqualTo("collection contains no data");
    }

    @Test
    void checkMapIsEmptyWeHaveMessage() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    void checkArgsIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("Names array is empty");
    }

    @Test
    void checkNoEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("name: key:value does not contain the symbol '='");
    }

    @Test
    void checkNoKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("name: =value does not contain a key");
    }

    @Test
    void checkNoValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "key=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("name: key= does not contain a value");
    }
}