package com.example.moneyTransferApp.service.impl;

import com.example.moneyTransferApp.entity.Role;
import com.example.moneyTransferApp.entity.Transaction;
import com.example.moneyTransferApp.entity.TransactionType;
import com.example.moneyTransferApp.entity.Users;
import com.example.moneyTransferApp.exception.CustomError;
import com.example.moneyTransferApp.exception.CustomException;
import com.example.moneyTransferApp.mapper.UserMapper;
import com.example.moneyTransferApp.model.request.UsersRequest;
import com.example.moneyTransferApp.model.request.ValueRequest;
import com.example.moneyTransferApp.model.response.UsersResponse;
import com.example.moneyTransferApp.repository.TransactionRepository;
import com.example.moneyTransferApp.repository.TransactionTypeRepository;
import com.example.moneyTransferApp.repository.UsersRepository;
import com.example.moneyTransferApp.service.UsersService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsersServiceImpl implements UsersService {
    TransactionRepository transactionRepository;
    TransactionTypeRepository transactionTypeRepository;
    UsersRepository usersRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    private  static  String rNumber(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Генерируем шесть случайных цифр
        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // случайное число от 0 до 9
            sb.append(digit);
        }

        return sb.toString();
    }

    @Override
    @Transactional
    public UsersResponse create(UsersRequest usersRequest) {
        if (usersRequest.getPassword().isBlank())
            throw new CustomException(CustomError.ENTITY_BAD_DATA, log);

        Users user = userMapper.toEntity(usersRequest);
        user.setPassword(this.passwordEncoder.encode(usersRequest.getPassword()));
        user.setAccount(new BigDecimal(1000));
        user.setRole(Role.builder().id(UUID.fromString(("5d16e497-14b2-4ff8-aad0-f84d00085389"))).build());

        while (true){
            String number = rNumber();
            if(!usersRepository.existsByNumber(number)){
                user.setNumber(number);
                break;
            }
        }

        Users savedUser = this.usersRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public UsersResponse getById(UUID id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_FOUND, log));
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsersResponse> getAll() {
        return usersRepository.findAll()
                .stream().map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsersResponse> list(Pageable pageable) {
        return usersRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    @Override
    @Transactional
    public UsersResponse update(UsersRequest userRequest, UUID uuid) {
        Users user = usersRepository.findById(uuid)
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_FOUND, log));

        if (userRequest.getPassword() != null) {
            user.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        } else {
            userRequest.setPassword(user.getPassword());
        }
        userMapper.update(user, userRequest);

        Users savedUser = this.usersRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public void delete(UUID uuid) {
        usersRepository.deleteById(uuid);
    }

    @Override
    public Boolean checkNumber(String number){
        return usersRepository.existsByNumber(number);
    }

    @Override
    @Transactional
    public void increaseAccount(String number, ValueRequest value){
        Users user = usersRepository.findByNumber(number)
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));
        TransactionType type = transactionTypeRepository.findById(UUID.fromString("ce737a47-0155-4e72-b9f1-aacc6c862a9d"))
                .orElseThrow(()->new CustomException(CustomError.ENTITY_NOT_FOUND, log));
        Transaction transaction = Transaction.builder()
                .dateTime(LocalDate.now())
                .to(user)
                .value(value.getValue())
                .type(type)
                .build();

        transactionRepository.save(transaction);

        user.setAccount(user.getAccount().add(value.getValue()));
        usersRepository.save(user);

    }
}
