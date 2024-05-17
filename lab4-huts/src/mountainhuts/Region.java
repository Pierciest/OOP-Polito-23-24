package mountainhuts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Class {@code Region} represents the main facade
 * class for the mountains hut system.
 * 
 * It allows defining and retrieving information about
 * municipalities and mountain huts.
 *
 */
public class Region {
	String name;
	Map <String,Municipality> municipalities = new HashMap<>();
	Map <String,MountainHut> mountainhuts = new HashMap<>();
	Map <String,Integer[]> ranges = new HashMap<>();
	/**
	 * Create a region with the given name.
	 * 
	 * @param name
	 *            the name of the region
	 */
	public Region(String name) {
		this.name = name;
	}

	/**
	 * Return the name of the region.
	 * 
	 * @return the name of the region
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Create the ranges given their textual representation in the format
	 * "[minValue]-[maxValue]".
	 * 
	 * @param ranges
	 *            an array of textual ranges
	 */
	public void setAltitudeRanges(String... ranges) {
		Stream.of(ranges).forEach(range-> this.ranges.put(range,new Integer[]{Integer.parseInt(range.split("-")[0]),Integer.parseInt(range.split("-")[1])}));
	}

	/**
	 * Return the textual representation in the format "[minValue]-[maxValue]" of
	 * the range including the given altitude or return the default range "0-INF".
	 * 
	 * @param altitude
	 *            the geographical altitude
	 * @return a string representing the range
	 */
	public String getAltitudeRange(Integer altitude) {
		String result = ranges.keySet().stream().filter(r -> Integer.parseInt(r.split("-")[0]) < altitude &&  Integer.parseInt(r.split("-")[1]) >= altitude).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		if(result.length() == 0){
			return "0-INF";
		}
		return result;
	}

	

	/**
	 * Return all the municipalities available.
	 * 
	 * The returned collection is unmodifiable
	 * 
	 * @return a collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipalities.values();
	}

	/**
	 * Return all the mountain huts available.
	 * 
	 * The returned collection is unmodifiable
	 * 
	 * @return a collection of mountain huts
	 */
	public Collection<MountainHut> getMountainHuts() {
		return mountainhuts.values();
	}

	/**
	 * Create a new municipality if it is not already available or find it.
	 * Duplicates must be detected by comparing the municipality names.
	 * 
	 * @param name
	 *            the municipality name
	 * @param province
	 *            the municipality province
	 * @param altitude
	 *            the municipality altitude
	 * @return the municipality
	 */
	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		municipalities.putIfAbsent(name, new Municipality(name,province,altitude));
		return municipalities.get(name);
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 *
	 * @param name
	 *            the mountain hut name
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return the mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,Municipality municipality) {
		mountainhuts.putIfAbsent(name, new MountainHut(name, bedsNumber, category, bedsNumber, municipality));
		return mountainhuts.get(name);
	}

	/**
	 * Create a new mountain hut if it is not already available or find it.
	 * Duplicates must be detected by comparing the mountain hut names.
	 * 
	 * @param name
	 *            the mountain hut name
	 * @param altitude
	 *            the mountain hut altitude
	 * @param category
	 *            the mountain hut category
	 * @param bedsNumber
	 *            the number of beds in the mountain hut
	 * @param municipality
	 *            the municipality in which the mountain hut is located
	 * @return a mountain hut
	 */
	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {
				mountainhuts.putIfAbsent(name, new MountainHut(name, altitude, category, bedsNumber, municipality));
				return mountainhuts.get(name);
	}

	/**
	 * Creates a new region and loads its data from a file.
	 * 
	 * The file must be a CSV file and it must contain the following fields:
	 * <ul>
	 * <li>{@code "Province"},
	 * <li>{@code "Municipality"},
	 * <li>{@code "MunicipalityAltitude"},
	 * <li>{@code "Name"},
	 * <li>{@code "Altitude"},
	 * <li>{@code "Category"},
	 * <li>{@code "BedsNumber"}
	 * </ul>
	 * 
	 * The fields are separated by a semicolon (';'). The field {@code "Altitude"}
	 * may be empty.
	 * 
	 * @param name
	 *            the name of the region
	 * @param file
	 *            the path of the file
	 */
	public static Region fromFile(String name, String file) {
		Region curRegion = new Region(name);
		readData(file).stream().skip(1).map(x->x.split(";")).forEach(data -> 
							{
								Municipality curMunicipality = curRegion.createOrGetMunicipality(data[1], data[0], Integer.parseInt(data[2]));
								if(data[4] == ""){
									curRegion.createOrGetMountainHut(data[3],null,data[5],Integer.parseInt(data[6]),curMunicipality);
								}
								else{
									curRegion.createOrGetMountainHut(data[3],Integer.parseInt(data[4]) ,data[5], Integer.parseInt(data[6]),curMunicipality); 
								}
							});
		return curRegion;
	}

	/**
	 * Reads the lines of a text file.
	 *
	 * @param file path of the file
	 * @return a list with one element per line
	 */
	public static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * Count the number of municipalities with at least a mountain hut per each
	 * province.
	 * 
	 * @return a map with the province as key and the number of municipalities as
	 *         value
	 */
	public Map<String, Long> countMunicipalitiesPerProvince() {
		Map<String, Long> result = municipalities.values()
				.stream()
				.collect(Collectors.groupingBy(Municipality::getProvince, Collectors.counting()));
		return result;
	}

	/**
	 * Count the number of mountain huts per each municipality within each province.
	 * 
	 * @return a map with the province as key and, as value, a map with the
	 *         municipality as key and the number of mountain huts as value
	 */
	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		Map<String, Map<String, Long>> result = mountainhuts.values()
				.stream()
				.collect(Collectors.groupingBy(hut -> hut.getMunicipality().getProvince(),
						Collectors.groupingBy(hut -> hut.getMunicipality().getName(), Collectors.counting())));
		return result;
	}

	/**
	 * Count the number of mountain huts per altitude range. If the altitude of the
	 * mountain hut is not available, use the altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the number of mountain huts
	 *         as value
	 */
	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		Map<String, Long> result = mountainhuts.values()
			.stream()
			.collect(Collectors.groupingBy(hut -> getAltitudeRange(hut.getAltitude().orElse(hut.getMunicipality().getAltitude())), Collectors.counting()));
		return result;
	}


	/**
	 * Compute the total number of beds available in the mountain huts per each
	 * province.
	 * 
	 * @return a map with the province as key and the total number of beds as value
	 */
	public Map<String, Integer> totalBedsNumberPerProvince() {
	    Map<String, Integer> result = mountainhuts.values()
		    .stream()
		    .collect(Collectors.groupingBy(
			    hut -> hut.getMunicipality().getProvince(),
			    Collectors.reducing(0, MountainHut::getBedsNumber, Integer::sum)
		    ));
	    return result;
	}

	/**
	 * Compute the maximum number of beds available in a single mountain hut per
	 * altitude range. If the altitude of the mountain hut is not available, use the
	 * altitude of its municipality.
	 * 
	 * @return a map with the altitude range as key and the maximum number of beds
	 *         as value
	 */
	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		Map<String, Optional<Integer>> result = mountainhuts.values()
		.stream()
		.collect(Collectors.groupingBy(
			hut -> getAltitudeRange(hut.getAltitude().orElse(hut.getMunicipality().getAltitude())),
			Collectors.mapping(hut -> hut.getBedsNumber(), Collectors.maxBy(Comparator.naturalOrder()))
		));
	return result;
	}

	/**
	 * Compute the municipality names per number of mountain huts in a municipality.
	 * The lists of municipality names must be in alphabetical order.
	 * 
	 * @return a map with the number of mountain huts in a municipality as key and a
	 *         list of municipality names as value
	 */
	public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
	    Map<Long, List<String>> result = mountainhuts.values()
		    .stream()
		    .collect(Collectors.groupingBy(
			    hut -> mountainhuts.values().stream().filter(h -> h.getMunicipality().equals(hut.getMunicipality())).count(),
			    Collectors.mapping(hut -> hut.getMunicipality().getName(), Collectors.toList())
		    ));
	    return result;
	}

}
