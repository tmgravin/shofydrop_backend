package com.shofydrop.service;

import com.shofydrop.entity.PromoCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromoCodeService {
    public List<PromoCode> findAll();

    public PromoCode findById(Long id);

    public PromoCode save(PromoCode promoCode);

    public PromoCode update(PromoCode promoCode, Long id);

    public void delete(Long id);
}
