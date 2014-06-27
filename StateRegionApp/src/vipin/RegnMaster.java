package vipin;

public class RegnMaster {
	private Integer RegionId;
	private String Name;
	private Integer StateId;

	public Integer getRegionId() {
		return this.RegionId;
	}

	public void setRegionId(Integer regionId) {
		this.RegionId = regionId;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String regionName) {
		this.Name = regionName;
	}

	public Integer getStateId() {
		return this.StateId;
	}

	public void setStateId(Integer stateId) {
		this.StateId = stateId;
	}

	public RegnMaster(Integer stateId) {
		this.StateId = stateId;
	}

	public RegnMaster(Integer regionId, String name, Integer stateId) {
		this.RegionId = regionId;
		this.Name = name;
		this.StateId = stateId;
	}
}
