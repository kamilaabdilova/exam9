package com.example.moneyTransferApp.specifications;

public enum SearchOperation {
    /**
     * =
     */
    EQUALITY,
    /**
     * <>
     */
    NOT_EQUALITY,

    /**
     * >
     */
    GREATER_THAN,
    /**
     * <
     */
    LESS_THAN,
    /**
     * <=
     */
    EQUALITY_OR_LESS_THAN,
    /**
     * >=
     */
    EQUALITY_OR_GREATER_THAN,

    /**
     * like({значение})
     */
    LIKE,
    /**
     * like(%{значение})
     */
    STARTS_WITH,
    /**
     * like({значение}%)
     */
    ENDS_WITH,
    /**
     * like(%{значение}%)
     */
    HAVE,
    /**
     * in({значения})
     */
    IN,

    /**
     * true
     */
    ALL,
    /**
     * is null
     */
    IS_NULL,
    /**
     * not null
     */
    NOT_NULL,

    /**
     * ORDER BY {entity поле}
     */
    SORT,
    /**
     * ORDER BY {entity поле} DESC
     */
    SORT_DESC,

    /**
     * спецификация: вернуть все значения, которые в коллекции имеют одно значениЕ
     */
    CONTAINS,
    /**
     * спецификация: вернуть все значения, которые в коллекции имеют несколько значениЙ
     */
    IS_MEMBER,
    ;
}
