package beans;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class TrainingSession {
	
	private String uniqueID;
	private String name;
	private User coach;
	private int duration; //minutes
	private String description;
	private ArrayList <User> attendees;
	private LocalDateTime startime;
	private SessionType type;
	
	public TrainingSession(String name, User coach, int duration, String description, SessionType type, LocalDateTime startime) {
		super();
		uniqueID = UUID.randomUUID().toString();
		this.name = name;
		this.coach = coach;
		this.duration = duration;
		this.description = description;
		this.attendees = null;
		this.type = type;
		this.startime = startime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getCoach() {
		return coach;
	}

	public void setCoach(User coach) {
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

	public ArrayList<User> getAttendees() {
		return attendees;
	}

	public void setAttendees(ArrayList<User> attendees) {
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

	public LocalDateTime getStartime() {
		return startime;
	}

	public void setStartime(LocalDateTime startime) {
		this.startime = startime;
	}
	
	
	

	
	
}
