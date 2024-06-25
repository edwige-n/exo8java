package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.EcoleDto;
import com.exo2.Exercice2.entity.Ecole;
import com.exo2.Exercice2.entity.Etudiant;
import com.exo2.Exercice2.mapper.EcoleMapper;
import com.exo2.Exercice2.repository.EcoleRepository;
import jakarta.persistence.Cacheable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Cacheable
@AllArgsConstructor
public class EcoleService {
    private EcoleRepository ecoleRepository;
    private EcoleMapper ecoleMapper;

    public List<EcoleDto> findAll(Pageable pageable) {
        var response = ecoleRepository.findAll(pageable).getContent();
        return ecoleMapper.toDtos(response);
    }

    public EcoleDto findById(long id) {
        return ecoleMapper.toDto(ecoleRepository.findById(id).orElse(null));
    }

    public List<EcoleDto> findByNomEtudiant(String nomEtudiant) {
        return ecoleMapper.toDtos(ecoleRepository.findEcolesFromNomEtudiant(nomEtudiant));
    }

    public EcoleDto save(EcoleDto ecoleDto) {
        Ecole ecole = ecoleMapper.toEntity(ecoleDto);
        ecole.getEtudiants().stream().forEach(e -> e.setEcole(ecole));
        return ecoleMapper.toDto(ecoleRepository.save(ecole));
    }
}
