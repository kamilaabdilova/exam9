package com.example.moneyTransferApp.service.impl;

import com.example.moneyTransferApp.entity.*;
import com.example.moneyTransferApp.exception.CustomError;
import com.example.moneyTransferApp.exception.CustomException;
import com.example.moneyTransferApp.mapper.TransactionMapper;
import com.example.moneyTransferApp.model.request.ValueRequest;
import com.example.moneyTransferApp.model.response.TransactionResponse;
import com.example.moneyTransferApp.repository.*;
import com.example.moneyTransferApp.service.TransactionService;
import com.example.moneyTransferApp.specifications.TransactionSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TransactionServiceImpl implements TransactionService {
    TransactionMapper transactionMapper;
    ProviderRepository providerRepository;
    TransactionTypeRepository transactionTypeRepository;
    ServicesRepository servicesRepository;
    UsersRepository usersRepository;
    CurrentUserServiceImpl currentUserService;
    TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public Page<TransactionResponse> my(Integer pageN, Integer sizeN, LocalDate from, LocalDate to, UUID typeId) {
        UUID myId = currentUserService.getCurrentUser().getId();

        int page = pageN == null ? 0 : pageN;
        int size = sizeN == null ? 100 : sizeN;

        Specification<Transaction> specification = Specification.where(TransactionSpecification.findByType(typeId)
                .and(TransactionSpecification.findByFrom(from))
                .and(TransactionSpecification.findByTo(to))
                .and((TransactionSpecification.findByMyFrom(myId).or(TransactionSpecification.findByMyTo(myId))))
        );

        return transactionRepository.findAll(specification, PageRequest.of(page, size))
                .map(transactionMapper::toResponse);
    }

    @Transactional
    public void transfer(String number, ValueRequest value){
        UUID fromId = currentUserService.getCurrentUser().getId();
        Users from = usersRepository.findById(fromId)
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));

        if(from.getAccount().compareTo(value.getValue()) == -1)
            return;

        Users to = usersRepository.findByNumber(number)
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));
        TransactionType type = transactionTypeRepository.findById(UUID.fromString("ce737a47-0155-4e72-b9f1-aacc6c862a90"))
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));

        Transaction transaction = Transaction.builder()
                .dateTime(LocalDate.now())
                .from(from)
                .to(to)
                .value(value.getValue())
                .type(type)
                .build();

        from.setAccount(from.getAccount().subtract(value.getValue()));
        usersRepository.save(from);

        to.setAccount(to.getAccount().add(value.getValue()));
        usersRepository.save(to);

        transactionRepository.save(transaction);

        log.info("Совершен перевод " + value.getValue() + " от " +from.getNumber()+ " к " + to.getNumber());
    }

    @Transactional
    public void withdraw (ValueRequest value){
        UUID fromId = currentUserService.getCurrentUser().getId();
        Users from = usersRepository.findById(fromId)
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));
        TransactionType type = transactionTypeRepository.findById(UUID.fromString("ce737a47-0155-4e72-b9f1-aacc6c862a97"))
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));

        if(from.getAccount().compareTo(value.getValue()) == -1)
            return;

        Transaction transaction = Transaction.builder()
                .dateTime(LocalDate.now())
                .from(from)
                .value(value.getValue())
                .type(type)
                .build();

        BigDecimal fromAccount = from.getAccount();
        fromAccount =fromAccount.subtract(value.getValue());
        from.setAccount(fromAccount);
        usersRepository.save(from);

        transactionRepository.save(transaction);

        log.info("Совершен вывод денег " + value.getValue() + " от " +from.getNumber());
    }

    @Transactional
    public void services (UUID serviceId, ValueRequest value){
        UUID fromId = currentUserService.getCurrentUser().getId();
        Users from = usersRepository.findById(fromId)
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));
        TransactionType type = transactionTypeRepository.findById(UUID.fromString("ce737a47-0155-4e72-b9f1-aacc6c86269d"))
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));
        Services service = servicesRepository.findById(serviceId)
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));

        if(from.getAccount().compareTo(value.getValue()) == -1)
            return;

        Transaction transaction = Transaction.builder()
                .dateTime(LocalDate.now())
                .from(from)
                .service(service)
                .value(value.getValue())
                .type(type)
                .build();

        from.setAccount(from.getAccount().subtract(value.getValue()));
        usersRepository.save(from);

        Provider provider = service.getProvider();
        provider.setAccount(provider.getAccount().add(value.getValue()));
        providerRepository.save(provider);

        transactionRepository.save(transaction);

        log.info("Оплачена услуга " + service.getId().toString() + " стоимостью " + value.getValue() + " от " +from.getNumber());
    }
}
