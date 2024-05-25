package com.example.moneyTransferApp.model.response;

import com.example.moneyTransferApp.entity.Provider;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServicesResponce {
    UUID id;
    String name;
    BigDecimal cost;
}
