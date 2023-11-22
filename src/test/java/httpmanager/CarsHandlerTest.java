package httpmanager;

import org.junit.Test;
import ru.andrey.caraccidentreport.htttpmanager.CarData;
import ru.andrey.caraccidentreport.htttpmanager.CarDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarsHandlerTest {

    @Test
    public void testDBRequest() throws SQLException, ClassNotFoundException {

//        Как тестить заспро к БД? Создавать отдельно небольшую тестовую БД?

        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" +
                "andrey", "andrey", "andrey");
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("select carplate, brand, model from car_accident_report.cars");


        List<CarData> allCars = new ArrayList<>();
        while (resultSet.next()) {
            CarData car = new CarData(resultSet.getString("brand"), resultSet.getString("model"),
                    resultSet.getString("carplate"));
            allCars.add(car);
        }

        CarData car0 = new CarData("KIA", "Rio", "E123HM77");
        CarData car1 = new CarData("VW", "Polo", "A234TB40");
        CarData car2 = new CarData("BMW", "X5", "H743MO50");
        CarData car3 = new CarData("Lada", "Priora", "X093EA14");

        assertEquals(car0, allCars.get(0));
        assertEquals(car1, allCars.get(1));
        assertEquals(car2, allCars.get(2));
        assertEquals(car3, allCars.get(3));


        resultSet.close();
        stmt.close();
        connection.close();
    }

    @Test
    public void testDTOConversion() {
        List<CarData> allCars = new ArrayList<>();
        CarData car0 = new CarData("KIA", "Rio", "E123HM77");
        CarData car1 = new CarData("VW", "Polo", "A234TB40");
        CarData car2 = new CarData("BMW", "X5", "H743MO50");
        CarData car3 = new CarData("Lada", "Priora", "X093EA14");

        allCars.add(car0);
        allCars.add(car1);
        allCars.add(car2);
        allCars.add(car3);

        List<CarDTO> allCarDTOs = new ArrayList<>();
        for (CarData car: allCars) {
            CarDTO carDTO = new CarDTO();
            carDTO.setCarBrand(car.getCarBrand());
            carDTO.setCarModel(car.getCarModel());
            carDTO.setCarPlate(car.getCarPlate());
            allCarDTOs.add(carDTO);
        }

        assertEquals(allCarDTOs.get(0).getCarBrand(), allCars.get(0).getCarBrand());
        assertEquals(allCarDTOs.get(0).getCarModel(), allCars.get(0).getCarModel());
        assertEquals(allCarDTOs.get(0).getCarPlate(), allCars.get(0).getCarPlate());

        assertEquals(allCarDTOs.get(1).getCarBrand(), allCars.get(1).getCarBrand());
        assertEquals(allCarDTOs.get(1).getCarModel(), allCars.get(1).getCarModel());
        assertEquals(allCarDTOs.get(1).getCarPlate(), allCars.get(1).getCarPlate());

        assertEquals(allCarDTOs.get(2).getCarBrand(), allCars.get(2).getCarBrand());
        assertEquals(allCarDTOs.get(2).getCarModel(), allCars.get(2).getCarModel());
        assertEquals(allCarDTOs.get(2).getCarPlate(), allCars.get(2).getCarPlate());

        assertEquals(allCarDTOs.get(3).getCarBrand(), allCars.get(3).getCarBrand());
        assertEquals(allCarDTOs.get(3).getCarModel(), allCars.get(3).getCarModel());
        assertEquals(allCarDTOs.get(3).getCarPlate(), allCars.get(3).getCarPlate());

    }

}
