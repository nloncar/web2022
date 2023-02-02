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
	
	public TrainingSession addSession(String name, String coach, int duration, String description, String type)
	{
		TrainingSession session = new TrainingSession(name, coach, duration, description, SessionType.valueOf(type));
		sessions.put(session.getUniqueID(), session);
		writeSession();
		return session;
	}
	
	public ArrayList<TrainingSession> getByUser(String username)
	{
		ArrayList<TrainingSession> sessions = new ArrayList<TrainingSession>(this.sessions.values()); 
		
		for(TrainingSession t : sessions)
		{
			if(t.getAttendees().contains(username))
			{
				sessions.add(t);
			}
		}
		return sessions;
	}
	
	public ArrayList<TrainingSession> getByCoach(String username)
	{
		ArrayList<TrainingSession> sessions = new ArrayList<TrainingSession>(this.sessions.values()); 
		
		for(TrainingSession t : sessions)
		{
			if(t.getCoach().equals(username))
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
