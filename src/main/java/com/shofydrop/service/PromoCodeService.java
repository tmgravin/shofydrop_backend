package com.shofydrop.service;

import com.shofydrop.entity.PromoCode;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PromoCodeService {
    List<PromoCode> findAll();

    PromoCode findById(Long id);

    PromoCode save(PromoCode promoCode);

    PromoCode update(PromoCode promoCode, Long id);

    void delete(Long id);
}
