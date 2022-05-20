package org.its.test.model;

public interface Task {
    default String getName() { return this.getClass().getSimpleName(); }
    String getResult(final String conditions);
    boolean checkConditions(String conditions);
}
