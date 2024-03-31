package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VolcanoImportJsonDto;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.Volcano;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.VolcanoRepository;
import softuni.exam.service.VolcanoService;
import softuni.exam.util.OutputMessages;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.paths.PathJSON;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolcanoServiceImpl implements VolcanoService {
    private static final int EVALUATION = 3000;
    private final VolcanoRepository volcanoRepository;
    private final CountryRepository countryRepository;
    private final ValidationUtil validation;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public VolcanoServiceImpl(VolcanoRepository volcanoRepository, CountryRepository countryRepository,
                              ValidationUtil validation, ModelMapper modelMapper, Gson gson) {
        this.volcanoRepository = volcanoRepository;
        this.countryRepository = countryRepository;
        this.validation = validation;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.volcanoRepository.count() > 0;
    }

    @Override
    public String readVolcanoesFileContent() throws IOException {
        return Files.readString(PathJSON.READ_VOLCANOES_PATH);
    }

    @Override
    public String importVolcanoes() throws IOException {
        String jsonVolcano = readVolcanoesFileContent();
        VolcanoImportJsonDto[] volcanoJsonDtos = this.gson.fromJson(jsonVolcano, VolcanoImportJsonDto[].class);
        return Arrays.stream(volcanoJsonDtos)
                .map(this::volcanoImport)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String exportVolcanoes() {
        List<Volcano> activeVolcanoes = this.volcanoRepository
                .findAllByElevationGreaterThanAndLastEruptionIsNotNullOrderByElevationDesc(EVALUATION);

        StringBuilder volcanoData = addInfoToVolcanoData(activeVolcanoes);

        return volcanoData.toString().trim();
    }

    private String volcanoImport(VolcanoImportJsonDto importDto) {
        boolean isValidVolcano = this.validation.isValid(importDto);
        Optional<Volcano> optVolcano = this.volcanoRepository.findByName(importDto.getName());
        Optional<Country> optCountry = this.countryRepository.findById(importDto.getCountry());

        if (!isValidVolcano || optVolcano.isPresent() || optCountry.isEmpty()) {
            return OutputMessages.INVALID_VOLCANO;
        }

        Volcano volcano = this.modelMapper.map(importDto, Volcano.class);
        volcano.setCountry(optCountry.get());
        this.volcanoRepository.save(volcano);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_VOLCANO, importDto.getName(), importDto.getVolcanoType());
    }

    private StringBuilder addInfoToVolcanoData(List<Volcano> activeVolcanoes) {
        StringBuilder volcanoData = new StringBuilder();

        activeVolcanoes.forEach(volcano -> {
            if (volcano.isActive()) {
                volcanoData.append(String.format(OutputMessages.VOLCANO_DATA_EXPORT,
                                volcano.getName(),
                                volcano.getCountry().getName(),
                                volcano.getElevation(),
                                volcano.getLastEruption()))
                        .append(System.lineSeparator());
            }
        });
        return volcanoData;
    }
}