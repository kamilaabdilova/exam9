package com.example.moneyTransferApp.specifications;

import com.example.moneyTransferApp.entity.Transaction;
import com.example.moneyTransferApp.entity.TransactionType;
import com.example.moneyTransferApp.entity.Users;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
public class TransactionSpecification extends BaseSpecification<Transaction> {
    public TransactionSpecification (SpecSearchCriteria criteria) {
        super(criteria);
    }
    public TransactionSpecification () {
        super(SpecSearchCriteria.all());
    }

    public static TransactionSpecification findByName(String name) {
        if (name == null)
            return new TransactionSpecification();

        if (name.isBlank())
            return new TransactionSpecification();


        String key = "name";
        return new TransactionSpecification(new SpecSearchCriteria(key, SearchOperation.HAVE, name));
    }

    public static TransactionSpecification findByFrom(LocalDate from) {
        if (from == null)
            return new TransactionSpecification();

        String key = "dateTime";
        return new TransactionSpecification(new SpecSearchCriteria(key, SearchOperation.EQUALITY_OR_GREATER_THAN, from));
    }

    public static TransactionSpecification findByTo(LocalDate to) {
        if (to == null)
            return new TransactionSpecification();

        String key = "dateTime";
        return new TransactionSpecification(new SpecSearchCriteria(key, SearchOperation.EQUALITY_OR_LESS_THAN, to));
    }
    
    public static TransactionSpecification sortByDate() {
        String key = "dateTime";
        return new TransactionSpecification(new SpecSearchCriteria(key, SearchOperation.SORT_DESC, null));
    }

    public static TransactionSpecification findByType(UUID typeId) {
        if (typeId == null)
            return new TransactionSpecification();

        String key = "type";
        TransactionType value = TransactionType.builder().id(typeId).build();
        return new TransactionSpecification(SpecSearchCriteria.equal(key, value));
    }

    public static TransactionSpecification findByMyFrom(UUID myId) {
        if (myId == null)
            return new TransactionSpecification();

        String key = "from";
        Users value = Users.builder().id(myId).build();
        return new TransactionSpecification(new SpecSearchCriteria(key, SearchOperation.EQUALITY, value));

    }

    public static TransactionSpecification findByMyTo(UUID myId) {
        if (myId == null)
            return new TransactionSpecification();

        String key = "to";
        Users value = Users.builder().id(myId).build();
        return new TransactionSpecification(new SpecSearchCriteria(key, SearchOperation.EQUALITY, value));
    }
}
