package ravo.jean.aime.boot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ravo.jean.aime.boot.domain.Car;
import ravo.jean.aime.boot.domain.CarDto;
import ravo.jean.aime.boot.mapper.CarMapper;
import ravo.jean.aime.boot.mapper.MapperService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car("x01", "Ford Type 5");

        //when
        CarDto carDto = CarMapper.INSTANCE.mapToDto(car);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getId()).isEqualTo("x01");
        assertThat(carDto.getName()).isEqualTo("Ford Type 5");

    }

}
