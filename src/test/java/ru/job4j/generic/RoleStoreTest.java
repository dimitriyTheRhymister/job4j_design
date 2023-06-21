package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.add(new Role("1", "user"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("admin");
    }

    @Test
    void whenReplaceThenRolenameIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("1", new Role("1", "user"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("user");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("10", new Role("10", "user"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.replace("1", new Role("1", "user"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.replace("10", new Role("10", "user"));
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.delete("1");
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean result = store.delete("2");
        assertThat(result).isFalse();
    }
}