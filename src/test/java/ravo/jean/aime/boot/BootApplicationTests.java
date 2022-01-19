package ravo.jean.aime.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootApplicationTests {

    @Test
    void contextLoads() {
    }

   /* @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car("x01", "Ford Type 5");

        //when
        CarDto carDto = CarMapper.INSTANCE.mapToDto(car);

        //then
        assertThat(carDto).isNotNull();
        assertThat(carDto.getId()).isEqualTo("x01");
        assertThat(carDto.getName()).isEqualTo("Ford Type 5");

    }*/

}
