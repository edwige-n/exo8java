package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.AdresseDto;
import com.exo2.Exercice2.dto.EtudiantDto;
import com.exo2.Exercice2.entity.Adresse;
import com.exo2.Exercice2.mapper.AdresseMapper;
import com.exo2.Exercice2.repository.AdresseRepository;
import jakarta.persistence.Cacheable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Cacheable
@AllArgsConstructor
public class AdresseService {
    private AdresseRepository adresseRepository;
    private AdresseMapper adresseMapper;

    public List<AdresseDto> findAll(Pageable pageable)
    {
        var response = adresseRepository.findAll(pageable).getContent();
        return adresseMapper.toDtos(response);
    }
    public AdresseDto findById(Long id)
    {
        return adresseMapper.toDto(adresseRepository.findById(id).get());
    }

    public List<AdresseDto> findByVille(String ville) {
        return adresseMapper.toDtos(adresseRepository.findAdresseByVille(ville));
    }
}
