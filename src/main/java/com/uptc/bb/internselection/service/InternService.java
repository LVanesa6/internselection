package com.uptc.bb.internselection.service;

import com.uptc.bb.internselection.dto.InternDTO;
import com.uptc.bb.internselection.entity.Intern;
import com.uptc.bb.internselection.entity.Intern.State;
import com.uptc.bb.internselection.mapper.InternMapper;
import com.uptc.bb.internselection.repo.InternRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternService {

    private final InternRepo internRepo;
    private final InternMapper internMapper = InternMapper.INSTANCE;

    public InternDTO saveIntern(InternDTO internDTO) {
        Intern intern = internMapper.mapInternDTOToIntern(internDTO);
        intern.setStatev(State.PENDIENTE); // Estado por defecto
        return internMapper.mapInternToInternDTO(internRepo.save(intern));
    }

    public List<InternDTO> getAllInterns() {
        return internRepo.findAll().stream()
                .map(internMapper::mapInternToInternDTO)
                .collect(Collectors.toList());
    }

    public Optional<InternDTO> getInternById(Integer id) {
        return internRepo.findById(id)
                .map(internMapper::mapInternToInternDTO);
    }

    public boolean updateInternState(Integer id, State newState) {
        Optional<Intern> optionalIntern = internRepo.findById(id);
        if (optionalIntern.isPresent()) {
            Intern intern = optionalIntern.get();
            intern.setStatev(newState);
            internRepo.save(intern);
            return true;
        }
        return false;
    }
}
