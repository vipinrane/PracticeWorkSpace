package vipin;

public class StateMaster {
	
	private Integer stateId;
	private String Name;

    public Integer getStateId() {
        return this.stateId;
    }
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
    
    public String getStateName()
    {
    	return this.Name;
    }
    
    public void setStateName(String Name)
    {
    	this.Name=Name;
    }
    
    public StateMaster(Integer stateId,String name)
    {
    	this.stateId = stateId;
		this.Name = name;
    }
}
