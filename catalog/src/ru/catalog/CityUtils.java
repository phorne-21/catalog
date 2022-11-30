package ru.catalog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CityUtils{

    public static String findCityWithMaxPopulation(List<City> cities) {
        City[] array = cities.toArray(new City[0]);
        City max = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (max.getPopulation() < array[i].getPopulation()) {
                max = array[i];
                index = i;
            }
        }
        return String.format("[%d] = %d", index, max.getPopulation());
    }

    // comparator or lambda
    public static void sortByName(List<City> cities) {
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
//        cities.sort(new Comparator<City>() {
//            @Override
//            public int compare(City o1, City o2) {
//                return o1.getName().compareToIgnoreCase(o2.getName());
//            }
//        });
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
