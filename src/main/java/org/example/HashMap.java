package org.example;

public class HashMap {
    private static final int SIZE = 32;
    private Entry[] table;

    public HashMap() {
        table = new Entry[SIZE];
    }

    public void put(String key, String value) {
        Entry entry = new Entry(key, value);
        int code = key.hashCode() % SIZE;
        if (table[code] == null) {
            table[code] = entry;
        } else {
            addOrUpdateNode(table[code], entry);
        }
    }

    public String get(String key) {
        int code = key.hashCode() % SIZE;
        Entry head = table[code];
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    public void remove(String key) {
        int code = key.hashCode() % SIZE;
        Entry head = table[code];
        Entry dummy = new Entry("0", "0");
        dummy.next = head;
        Entry prev = dummy;
        while (head != null) {
            if (head.key.equals(key)) prev.next = head.next;
            prev = head;
            head = head.next;
        }
        table[code] = dummy.next;
    }

    public boolean containsKey(String key) {
        int code = key.hashCode() % SIZE;
        Entry head = table[code];
        while (head != null) {
            if (head.key.equals(key)) return true;
            head = head.next;
        }
        return false;
    }

    private void addOrUpdateNode(Entry head, Entry node) {
        Entry prev = head;
        while (head != null) {
            if (head.key.equals(node.key)) {
                head.value = node.value;
                return;
            }
            prev = head;
            head = head.next;
        }
        prev.next = node;
    }
}
