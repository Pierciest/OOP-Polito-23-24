package mountainhuts;

/**
 * Class representing a municipality that hosts a mountain hut.
 * It is a data class with getters for name, province, and altitude
 * 
 */
public class Municipality {
	String name;
	String province;
	Integer altitude;
	public Municipality(String name, String province, Integer altitude) {
		this.name = name;
		this.province = province;
		this.altitude = altitude;
	}

	public String getName() {
		return this.name;
	}

	public String getProvince() {
		return this.province;
	}

	public Integer getAltitude() {
		return this.altitude;
	}

}
