/*
 * 
 MovieGraph.java
 COSC 102, Colgate University
 
 */

import java.io.*;
import java.util.*;



public class MovieGraph
{

	HashMap<String, HashSet<String>> sortActor = new HashMap<String, HashSet<String>>();
 	HashMap<String, HashSet<String>> sortMovie = new HashMap<String, HashSet<String>>();
 	HashMap<String, HashSet<String>> MovieActorCombo = new HashMap<String, HashSet<String>>();
 	
  public MovieGraph(ArrayList<String[]> data)
  {
  	  
 	HashSet<String> tempMovieForActor;
 
 	for (int i = 0; i < data.size(); i++){ //runs through file
 		String[] movie = data.get(i); //gets data for each movie
 		HashSet<String> actorHash = new HashSet<String>();
 		for (int x = 1; x < movie.length; x++){
 			actorHash.add(movie[x]); //adds actors from each movie into the Hashet
 			if (MovieActorCombo.containsKey(movie[x])){
 				tempMovieForActor = MovieActorCombo.get(movie[x]);
 			}
 			else{
 				tempMovieForActor = new HashSet<String>();
 			}
 			tempMovieForActor.add(movie[0]);
 			MovieActorCombo.put(movie[x], tempMovieForActor);
 		}
 		MovieActorCombo.put(movie[0], actorHash); // movie: actor
 	}
 	
  	}
  
  //Returns an ArrayList of Strings which is the shortest path of movies/actors between
  //target1 and target2.
  //If no path can be found, can return either null or an empty ArrayList
  public ArrayList<String> findShortestLink(String target1, String target2){

  	  if (!MovieActorCombo.containsKey(target1) || !MovieActorCombo.containsKey(target2)){ //if one of the actors isn't in the list
  	  	  throw new IllegalArgumentException();
  	  }
  	  
  	  if (target1.equals(target2)){ //if they are the same person
  	  	  return null;
  	  }
  	  
  	  Queue<String> itemsToCheck = new LinkedList<String>(); //stores the actors you're going through
  	  HashMap<String, String> visited = new HashMap<String, String>();
  	  HashSet<String> tempSet = new HashSet<String>();
  	  ArrayList<String> pathway = new ArrayList<String>();
  	  
  	  itemsToCheck.add(target1); //target 1 will be the first actor
  	  visited.put(target1, null);
  	  
  	  String tempValue;

  	  while (!itemsToCheck.isEmpty()){
  	  	  
  	  	  //head of the queue
  	  	  tempValue = itemsToCheck.peek();
  	  	  
  	  	  //remove head of queue, get the value of that head
  	  	  tempSet = MovieActorCombo.get(itemsToCheck.remove());
  	  	  
  	  	  //add to the visited map
  	  	  for (String y: tempSet){
  	  	  	  if(!visited.containsKey(y)){
  	  	  	  	  itemsToCheck.add(y);
  	  	  	  	  visited.put(y, tempValue);
  	  	  	  }
  	  	  }

  	  	  //check if target 2 has been reached yet
  	  	  if (visited.containsKey(target2))
  	  	  	  break;
  	  	  }
  
  	  	  String backwardsTarget = visited.get(target2);
  	  	  while (backwardsTarget != null){
  	  	  	  pathway.add(0, backwardsTarget);
  	  	  	  backwardsTarget = visited.get(backwardsTarget);
  	  	  }
  	  	  
  	  	  if (pathway.size() > 0){
  	  	  	  pathway.add(pathway.size(), target2); //add the person we're looking for to the end of the list
  	  	  }
  	  	  
  	  	  //System.out.println(pathway);
  	  	  //System.out.println(visited);
  	  	  //System.out.println(itemsToCheck);
  	  	  //System.out.println(itemsToCheck);
  	  	  //itemsToCheck.add(MovieActorCombo.get(itemsToCheck.remove()));
  	  	  //System.out.println(itemsToCheck);
  	  	  //tempSet = movieActorCombo.get(target1);
  	  	  //System.out.println(itemsToCheck);
  	  	  //System.out.println(MovieActorCombo.get("Haynes, Anthony"));

  	  return pathway;	  	  
    }
  }
    

    
    
    
  
