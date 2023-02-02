package beans;

import java.sql.Time;
import java.util.ArrayList;
import java.util.UUID;

public class TrainingSession {
	
	private String uniqueID;
	private String name;
	private String coach; //username
	private int duration; //minutes
	private String description;
	private ArrayList <String> attendees; //usernames
	private SessionType type;
	
	public TrainingSession(String name, String coach, int duration, String description, SessionType type) {
		super();
		uniqueID = UUID.randomUUID().toString();
		this.name = name;
		this.coach = coach;
		this.duration = duration;
		this.description = description;
		this.attendees = null;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<String> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<String> attendees) {
		this.attendees = attendees;
	}

	public SessionType getType() {
		return type;
	}

	public void setType(SessionType type) {
		this.type = type;
	}

	public String getUniqueID() {
		return uniqueID;
	}
	
	
	

	
	
}
