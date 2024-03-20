package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
import softuni.exam.util.OutputMessages;
import softuni.exam.util.PathsJSON;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstellationServiceImpl implements ConstellationService {

    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository,
                                    Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(PathsJSON.READ_CONSTELLATIONS_PATH);
    }

    @Override
    public String importConstellations() throws IOException {
        String json = readConstellationsFromFile();
        ConstellationImportDto[] constellationDtos = this.gson.fromJson(json, ConstellationImportDto[].class);

        return Arrays.stream(constellationDtos)
                .map(this::importConstellation)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String importConstellation(ConstellationImportDto importDto) {
        boolean isValid = validationUtil.isValid(importDto);
        Optional<Constellation> optConstellation = this.constellationRepository.findByName(importDto.getName());

        if (!isValid || optConstellation.isPresent()) {
            return OutputMessages.INVALID_CONSTELLATION;
        }

        Constellation constellation = this.modelMapper.map(importDto, Constellation.class);
        this.constellationRepository.save(constellation);

        return String.format(
                OutputMessages.SUCCESSFULLY_ADDED_CONSTELLATION, importDto.getName(), importDto.getDescription());
    }
}
