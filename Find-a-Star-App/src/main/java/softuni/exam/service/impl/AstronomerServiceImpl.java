package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AstronomerImportDto;
import softuni.exam.models.dto.AstronomersImportXMLDto;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Star;
import softuni.exam.repository.AstronomerRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.AstronomerService;
import softuni.exam.util.OutputMessages;
import softuni.exam.util.PathsXML;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AstronomerServiceImpl implements AstronomerService {

    private final AstronomerRepository astronomerRepository;
    private final StarRepository starRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;

    @Autowired
    public AstronomerServiceImpl(AstronomerRepository astronomerRepository, StarRepository starRepository,
                                 ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser) {
        this.astronomerRepository = astronomerRepository;
        this.starRepository = starRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.astronomerRepository.count() > 0;
    }

    @Override
    public String readAstronomersFromFile() throws IOException {
        return Files.readString(PathsXML.READ_ASTRONOMERS_PATH);
    }

    @Override
    public String importAstronomers() throws IOException, JAXBException {
        File filePath = PathsXML.READ_ASTRONOMERS_PATH.toFile();

        return this.xmlParser.fromFile(filePath, AstronomersImportXMLDto.class)
                .getAstronomers()
                .stream()
                .map(this::importAstronomer)
                .collect(Collectors.joining(System.lineSeparator()));
    }
    private String importAstronomer(AstronomerImportDto importDto) {
        boolean isValid = validationUtil.isValid(importDto);
        Optional<Astronomer> optAstronomer = this.astronomerRepository
                .findByFirstNameAndLastName(importDto.getFirstName(), importDto.getLastName());
        Optional<Star> optStar = this.starRepository.findById(importDto.getStarId());

        if (!isValid || optAstronomer.isPresent() || optStar.isEmpty()) {
            return OutputMessages.INVALID_ASTRONOMER;
        }

        Astronomer astronomer = this.modelMapper.map(importDto, Astronomer.class);
        astronomer.setObservingStar(optStar.get());
        this.astronomerRepository.save(astronomer);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_ASTRONOMER,
                importDto.getFirstName(), importDto.getLastName(), importDto.getAverageObservationHours());
    }
}
