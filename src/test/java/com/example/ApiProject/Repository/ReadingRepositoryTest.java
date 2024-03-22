package com.example.ApiProject.Repository;

import com.example.ApiProject.BitcoinReading.Reading;
import com.example.ApiProject.BitcoinReading.ReadingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.xml.parsers.SAXParser;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReadingRepositoryTest {

    @Autowired
    private ReadingRepository readingRepository;

    @Test
    public void ReadingRepository_Save_ReturnSaved() {

        // Arrange
        Reading reading = new Reading("May","01","2002","12:00:00","Bitcoin",10,10,10);

        // Act
        Reading savedReading = readingRepository.save(reading);

        // Assert
        Assertions.assertThat(savedReading).isNotNull();
        Assertions.assertThat(savedReading.getId()).isGreaterThan(0);
    }

    @Test
    public void ReadingRepository_FindFirstByIdDesc_ThenReturnLatestReading() {

        // Arrange
        Reading reading1 = new Reading("1","01","1111","12:00:00","1",10,10,10);
        Reading reading2 = new Reading("2","02","2222","12:00:00","2",10,10,10);

        // Act
        readingRepository.save(reading1);
        readingRepository.save(reading2);

        Reading RepositoryRetrievalResult = readingRepository.findFirstByOrderByIdDesc();

        // Assert
        Assertions.assertThat(RepositoryRetrievalResult).isEqualTo(reading2);
        Assertions.assertThat(RepositoryRetrievalResult.getId()).isEqualTo(2);
    }

    @Test
    public void ReadingRepository_FindAllByOrderByIdDesc_ReturnMoreThanOneReading() {

        // Arrange
        Reading reading1 = new Reading("1","01","1111","12:00:00","1",10,10,10);
        Reading reading2 = new Reading("2","02","2222","12:00:00","2",10,10,10);
        Reading reading3 = new Reading("3","03","3333","12:00:00","3",10,10,10);

        int pageNum = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);

        // Act
        readingRepository.save(reading1);
        readingRepository.save(reading2);
        readingRepository.save(reading3);

        List<Reading> RepositoryListRetrievalResult = readingRepository.findAllByOrderByIdDesc(pageRequest).getContent();

        // Assert
        Assertions.assertThat(RepositoryListRetrievalResult).isNotNull();
        Assertions.assertThat(RepositoryListRetrievalResult).isNotEmpty();

        Assertions.assertThat(RepositoryListRetrievalResult.get(0)).isEqualTo(reading3);
        Assertions.assertThat(RepositoryListRetrievalResult).containsSequence(reading3,reading2,reading1);
    }
}
