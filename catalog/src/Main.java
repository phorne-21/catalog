import static ru.catalog.CityUtils.*;

public class Main {
    public static void main(String[] args) {
        print(parse());
        print(sortByName(parse()));
        print(sortByDistrictAndName(parse()));
    }
}
