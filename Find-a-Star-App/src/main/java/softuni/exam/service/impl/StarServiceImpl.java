package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.enums.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.OutputMessages;
import softuni.exam.util.PathsJSON;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarServiceImpl implements StarService {

    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository, Gson gson,
                           ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(PathsJSON.READ_STARS_PATH);
    }

    @Override
    public String importStars() throws IOException {
        String json = readStarsFileContent();
        StarImportDto[] starDtos = this.gson.fromJson(json, StarImportDto[].class);

        return Arrays.stream(starDtos)
                .map(this::importStar)
                .collect(Collectors.joining(System.lineSeparator()));
    }


    @Override
    public String exportStars() {
        List<Star> stars =
                this.starRepository.findAllByStarTypeAndObserversIsNullOrderByLightYearsAsc(StarType.RED_GIANT);

        StringBuilder builder = new StringBuilder();

        stars.forEach(star -> {
            builder.append(String.format(
                            "Star: %s\n" +
                            "   *Distance: %.2f light years\n" +
                            "   **Description: %s\n" +
                            "   ***Constellation: %s",
                    star.getName(),
                    star.getLightYears(),
                    star.getDescription(),
                    star.getConstellation().getName()))
                    .append(System.lineSeparator());
        });
        return builder.toString().trim();
    }

    private String importStar(StarImportDto importDto) {
        boolean isValid = validationUtil.isValid(importDto);
        Optional<Star> optStar = this.starRepository.findByName(importDto.getName());
        Optional<Constellation> optConstellation = this.constellationRepository.findById(importDto.getConstellation());

        if (!isValid || optStar.isPresent() || optConstellation.isEmpty()) {
            return OutputMessages.INVALID_STAR;
        }

        Star star = this.modelMapper.map(importDto, Star.class);
        star.setConstellation(optConstellation.get());
        this.starRepository.save(star);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_STAR, importDto.getName(), importDto.getLightYears());
    }
}
