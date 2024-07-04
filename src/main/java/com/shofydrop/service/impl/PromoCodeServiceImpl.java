package com.shofydrop.service.impl;


import com.shofydrop.entity.PromoCode;
import com.shofydrop.exception.ResourceNotFoundException;
import com.shofydrop.repository.PromoCodeRepository;
import com.shofydrop.service.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoCodeServiceImpl implements PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    /**
     * @return
     */
    @Override
    public List<PromoCode> findAll() {
        return promoCodeRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public PromoCode findById(Long id) {
        return promoCodeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "promo code  does not exist with id" + id
                )
        );
    }

    /**
     * @param promoCode
     * @return
     */
    @Override
    public PromoCode save(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    /**
     * @param promoCode
     * @param id
     * @return
     */
    @Override
    public PromoCode update(PromoCode promoCode, Long id) {
        boolean isExist = promoCodeRepository.existsById(id);
        if (isExist) {
            PromoCode isExistingPromoCode = promoCodeRepository.findById(id).get();
            isExistingPromoCode.setCode(promoCode.getCode());
            isExistingPromoCode.setCreatedAt(promoCode.getCreatedAt());
            isExistingPromoCode.setUpdatedAt(promoCode.getUpdatedAt());
            return promoCodeRepository.save(promoCode);
        }
        return promoCode;
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        promoCodeRepository.deleteById(id);
    }
}
