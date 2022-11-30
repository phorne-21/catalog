import ru.catalog.City;

import java.util.List;

import static ru.catalog.CityUtils.*;

public class Main {
    public static void main(String[] args) {
        List<City> cities = parse();
        sortByName(cities);
        print(cities);
        sortByDistrictAndName(cities);
        print(cities);
    }
}
