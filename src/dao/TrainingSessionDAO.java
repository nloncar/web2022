package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Coach;
import beans.Customer;
import beans.Membership;
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
		
		File file = new File(contextPath + "/sessions.json");
		try {
			ObjectMapper mapper = new ObjectMapper();
			sessions = mapper.readValue(file, new TypeReference<Map<String, TrainingSession>>(){});
			System.out.println("Loaded sessions");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public TrainingSession addSession(String name, User coach, int duration, String description, String type, LocalDateTime startime)
	{
		TrainingSession session = new TrainingSession(name, coach, duration, description, SessionType.valueOf(type),startime);
		sessions.put(session.getUniqueID(), session);
		writeSession();
		return session;
	}
	
	public TrainingSession addSContent(String name, User coach, int duration, String description, String type)
	{
		TrainingSession session = new TrainingSession(name, null, duration, description, SessionType.valueOf(type), null);
		sessions.put(session.getUniqueID(), session);
		writeSession();
		return session;
	}
	
	public Collection<TrainingSession> getContent()
	{
		ArrayList<TrainingSession> ret = new ArrayList<TrainingSession>();
		ArrayList<TrainingSession> sessions = new ArrayList<TrainingSession>(this.sessions.values()); 
		for(TrainingSession session: sessions)
		{
			if(session.getStartime() == null)
			{
				ret.add(session);
						}
		}
		return ret;
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
	
	public TrainingSession changeAttendance(TrainingSession session, User user)
	{
		
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
		this.sessions.put(session.getUniqueID(), session);
		writeSession();
		return session;
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
		
		File file = new File(this.contextPath + "/sessions.json");
		System.out.println(this.contextPath);
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(file, sessions);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		/*File file = new File(this.contextPath + "/sessions.txt");
		
		if(!file.exists()){
	     	   try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     	}*/
	}
	
	
	
}
