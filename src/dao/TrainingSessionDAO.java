package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import beans.Coach;
import beans.Customer;
import beans.SessionType;
import beans.TrainingSession;
import beans.User;

public class TrainingSessionDAO {

	private Map<String, TrainingSession> sessions = new HashMap<>();
	
	private String contextPath;
	
	public TrainingSessionDAO() {
		
	}
	

	public TrainingSessionDAO(String contextPath) {
		loadSessions(contextPath);
		this.contextPath = contextPath;
	}
	
	public void loadSessions(String contextPath)
	{
		
	}
	
	public TrainingSession addSession(String name, User coach, int duration, String description, String type)
	{
		TrainingSession session = new TrainingSession(name, coach, duration, description, SessionType.valueOf(type));
		sessions.put(session.getUniqueID(), session);
		writeSession();
		return session;
	}
	
	public ArrayList<TrainingSession> getByUser(User user)
	{
		ArrayList<TrainingSession> sessions = new ArrayList<TrainingSession>(this.sessions.values()); 
		
		for(TrainingSession t : sessions)
		{
			if(t.getAttendees().contains(user))
			{
				sessions.add(t);
			}
		}
		return sessions;
	}
	
	public TrainingSession changeAttendance(String id, User user)
	{
		TrainingSession session = sessions.get(id);
		ArrayList<User> attendees = session.getAttendees();
		if(attendees.contains(user))
		{
			attendees.remove(user);
		}
		else
		{
			attendees.add(user);
		}
		
		session.setAttendees(attendees);
		this.sessions.put(id, session);
		writeSession();
		return  session;
	}
	
	public ArrayList<TrainingSession> getByCoach(User coach)
	{
		ArrayList<TrainingSession> sessions = new ArrayList<TrainingSession>(this.sessions.values()); 
		
		for(TrainingSession t : sessions)
		{
			if(t.getCoach().equals(coach))
			{
				sessions.add(t);
			}
		}
		return sessions;
	}
	
	
	public void writeSession()
	{
		File file = new File(this.contextPath + "/sessions.txt");
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}
	}
	
}
