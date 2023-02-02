package beans;


public class Coach extends User {

	private TrainingSession[] sessions;

	public Coach() {
		super();
	}

	public Coach(String username, String password, String name, String surname, String birthday, String gender) {
		super(username, password, name, surname, birthday, gender);
	}

	public TrainingSession[] getSessions() {
		return sessions;
	}

	public void setSessions(TrainingSession[] sessions) {
		this.sessions = sessions;
	}
	

	
}
