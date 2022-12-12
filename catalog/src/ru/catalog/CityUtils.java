package ru.catalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.*;

public class CityUtils{
    public static void findCitiesInRegions(List<City> cities) {
        Map<String, Integer> region = new HashMap<>();
        City[] arr = cities.toArray(new City[0]);
        int num = 0;
        for (int i = 0; i < arr.length-1; i++) {
            // если мапа содержит ключ arr[i].getRegion()
            // то достаём элемент с этим ключем и увеличиваем значение на 1
            // else put(arr[i].getRegion(), 1)
            if (region.containsKey(arr[i].getRegion())) {
                region.put(arr[i].getRegion(), region.get(arr[i].getRegion()) + 1);
            } else {
                region.put(arr[i].getRegion(), 1);
            }
        }
        region.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public static void findCityWithMaxPopulation(List<City> cities) {
        City[] array = cities.toArray(new City[0]);
        City max = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (max.getPopulation() < array[i].getPopulation()) {
                max = array[i];
                index = i;
            }
        }
        System.out.println(MessageFormat.format("[{0}] = {1}", index, max.getPopulation()));
    }

    // comparator or lambda
    public static void sortByName(List<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }

    public static void sortByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }
    public static List<City> parse() {
        List<City> cities = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("resources/city_ru.csv"));
            while (scanner.hasNextLine()) {
                cities.add(parse(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static City parse (String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*");
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = null;
        if (scanner.hasNext()) {
            foundation = scanner.next();
        }
        scanner.close();

        return new City(name, region, district, population, foundation);
    }

    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }
}
